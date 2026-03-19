package com.innostax.transaction.controller;

import com.innostax.transaction.entity.User;
import com.innostax.transaction.service.JoinFetchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/joinFetch")
public class JoinFetchController {

    private final JoinFetchService joinFetchService;

    public JoinFetchController(JoinFetchService joinFetchService){
        this.joinFetchService=joinFetchService;
    }

    @GetMapping
    private List<User> run(){
        return joinFetchService.run();
    }

}
