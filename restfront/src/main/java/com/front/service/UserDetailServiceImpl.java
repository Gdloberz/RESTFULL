package com.front.service;

import com.front.model.User;
import com.front.restclient.RestClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private RestClient restClient;

    public UserDetailServiceImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = restClient.getUserToAutorithe(s);
        if (user != null) {
            return user;
        } else throw new IllegalArgumentException("User not found");
    }
}
