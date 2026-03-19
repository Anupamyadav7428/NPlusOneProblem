package com.innostax.transaction.controller;

import com.innostax.transaction.dto.generalDto.InsertBulkDataDTO;
import com.innostax.transaction.service.DataInitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class DataInitController {

    private final DataInitService dataInitService;

    public DataInitController(DataInitService dataInitService) {
        this.dataInitService = dataInitService;
    }

    @PostMapping("/insert-bulk")
    public String insertBulkData(@RequestBody InsertBulkDataDTO dto) {
        dataInitService.insertBulkData(dto.getNumberOfUsers(), dto.getExpensesPerUser());
        return "Inserted " + dto.getNumberOfUsers() + " users with " +
                dto.getExpensesPerUser() + " expenses each successfully!";
    }
}