package com.innostax.transaction.controller;

import com.innostax.transaction.entity.User;
import com.innostax.transaction.service.BatchFetchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/batch")
public class BatchController {
    private BatchFetchService batchFetchService;

    public BatchController(BatchFetchService batchFetchService){
        this.batchFetchService=batchFetchService;
    }
    @GetMapping
    public List<User> run(){
       return batchFetchService.run();
    }


}
