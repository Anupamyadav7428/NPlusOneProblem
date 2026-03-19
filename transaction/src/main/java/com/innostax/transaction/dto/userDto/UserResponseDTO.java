package com.innostax.transaction.dto.userDto;

import com.innostax.transaction.entity.Expense;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import java.util.List;

@Data
public class UserResponseDTO {
    private String name;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenses;
}
