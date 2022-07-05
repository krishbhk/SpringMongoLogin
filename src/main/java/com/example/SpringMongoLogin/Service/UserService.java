package com.example.SpringMongoLogin.Service;

import com.example.SpringMongoLogin.Model.UserModel;
import com.example.SpringMongoLogin.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel u = userRepo.findByUsername(username);
        if (u == null) {
            return  null;
        }
            String uname = u.getUsername();
            String pwsd = u.getPassword();

            return new User(uname,pwsd,new ArrayList<>());
    }
}
