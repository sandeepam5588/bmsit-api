package com.bmsit.bmsitapi.service;

import com.bmsit.bmsitapi.model.MyUserDetails;
import com.bmsit.bmsitapi.model.User;
import com.bmsit.bmsitapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("user name "+username+" not found"));
        return user.map(MyUserDetails::new).get();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User addApiUser(User user) {
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllApiUsers() {
        return new ArrayList<>(userRepository.findAll());
    }
}
