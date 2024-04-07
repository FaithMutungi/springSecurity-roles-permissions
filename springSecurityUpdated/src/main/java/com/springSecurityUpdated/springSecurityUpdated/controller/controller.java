package com.springSecurityUpdated.springSecurityUpdated.controller;

import com.springSecurityUpdated.springSecurityUpdated.model.OurUser;
import com.springSecurityUpdated.springSecurityUpdated.repository.OurUserRepository;
import com.springSecurityUpdated.springSecurityUpdated.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class controller {
    @Autowired
    private OurUserRepository ourUserRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @GetMapping("/")
    public String goHome(){
        return "this is publicly available without needing authentication";
    }
    @PostMapping("/user/save")

    public ResponseEntity<Object> saveUser(@RequestBody OurUser ourUser){
        ourUser.setPassword(passwordEncoder.encode(ourUser.getPassword()));
        OurUser result= ourUserRepository.save(ourUser);
        if(result.getId()>0){
            return ResponseEntity.ok("user was saved");
        }
        return ResponseEntity.status(404).body("error, user not saved");

    }
    @GetMapping("/product/all")
    public ResponseEntity<Object> getAllProducts(){
        return ResponseEntity.ok(productRepository.findAll());
    }
    @GetMapping("/users/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> getAllUsers(){
        return ResponseEntity.ok(ourUserRepository.findAll());

    }
    @GetMapping("/users/single")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
    public  ResponseEntity <Object> getMyDetails(){

        return ResponseEntity.ok(ourUserRepository.findByEmail(getLoggedInUserDetails().getUsername()));
    }

    public UserDetails getLoggedInUserDetails(){

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (authentication!= null && authentication.getPrincipal() instanceof UserDetails){
            return (UserDetails) authentication.getPrincipal();
        }
        return  null;
    }

}
