package com.ledzer.api.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallbacks")
public class GatewayFallback {

    @GetMapping("/product")
    public ResponseEntity<String> productFallback(){
        return new ResponseEntity<>("Something went wrong for this operation. Please try later...", HttpStatus.SERVICE_UNAVAILABLE);
    }
    @GetMapping("/customer")
    public ResponseEntity<String> customerFallback(){
        return new ResponseEntity<>("Something went wrong for this operation. Please try later...", HttpStatus.SERVICE_UNAVAILABLE);
    }
    @GetMapping("/master")
    public ResponseEntity<String> masterFallback(){
        return new ResponseEntity<>("Something went wrong for this operation. Please try later...", HttpStatus.SERVICE_UNAVAILABLE);
    }
}
