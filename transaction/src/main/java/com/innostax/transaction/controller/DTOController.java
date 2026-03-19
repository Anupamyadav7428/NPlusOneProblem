package com.innostax.transaction.controller;

import com.innostax.transaction.dto.userExpanseDto.UserExpanseDTO;
import com.innostax.transaction.service.DTOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dto")
public class DTOController {
    private final DTOService dtoService;

    public DTOController(DTOService dtoService){
        this.dtoService=dtoService;
    }

    @GetMapping
    public List<UserExpanseDTO> run(){
        return dtoService.run();
    }

}
