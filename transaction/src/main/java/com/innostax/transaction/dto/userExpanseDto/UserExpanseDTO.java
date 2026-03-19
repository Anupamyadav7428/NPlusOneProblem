package com.innostax.transaction.dto.userExpanseDto;

import lombok.Data;

@Data
public class UserExpanseDTO {
    private String name;
    private String description;
    private double amount;

    public UserExpanseDTO(String name, String description, double amount) {
        this.name = name;
        this.description = description;
        this.amount = amount;
    }
}
