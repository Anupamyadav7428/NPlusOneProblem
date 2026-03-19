package com.innostax.transaction.repository;
import com.innostax.transaction.dto.userExpanseDto.UserExpanseDTO;
import com.innostax.transaction.entity.User;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.expenses")
    List<User> finalAllWithExpenses();

    @EntityGraph(attributePaths = "expenses")
    @Query("SELECT u FROM User u")
    List<User> findAllUsingEntityGraph();

    @Query("SELECT new com.innostax.transaction.dto.userExpanseDto.UserExpanseDTO(u.name, e.description, e.amount) " +
            "FROM User u JOIN u.expenses e")
    List<UserExpanseDTO> fetchUserExpanseDTO();
}
