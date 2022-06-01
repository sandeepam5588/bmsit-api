package com.bmsit.bmsitapi.controller;

import com.bmsit.bmsitapi.model.AuthenticationRequest;
import com.bmsit.bmsitapi.model.AuthenticationResponse;
import com.bmsit.bmsitapi.service.MyUserDetailsService;
import com.bmsit.bmsitapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
       try {
           authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),authenticationRequest.getPassword())
           );
       } catch (BadCredentialsException e){
           throw new Exception("Incorrect username and password", e);
       }
       final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

       final String jwt = jwtTokenUtil.generateToken(userDetails);

       return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
