package com.innostax.transaction.controller;
import com.innostax.transaction.entity.User;
import com.innostax.transaction.service.EntityGraphService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/entityGraph")
public class EntityGraphController {
    private final EntityGraphService findAllUsingEntityGraph;

    public EntityGraphController(EntityGraphService findAllUsingEntityGraph){
        this.findAllUsingEntityGraph=findAllUsingEntityGraph;
    }

    @GetMapping
    public List<User> run(){
        return findAllUsingEntityGraph.run();
    }
}
