package edu.ntnu.idatt2105.calculator.repositories;

import edu.ntnu.idatt2105.calculator.models.Calculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalculationRepository extends JpaRepository<Calculation, Long> {
}
