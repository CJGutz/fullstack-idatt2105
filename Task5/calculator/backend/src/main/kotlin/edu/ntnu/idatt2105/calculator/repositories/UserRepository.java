package edu.ntnu.idatt2105.calculator.repositories;

import edu.ntnu.idatt2105.calculator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
