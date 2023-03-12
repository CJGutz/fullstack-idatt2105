package edu.ntnu.idatt2105.calculator.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String username;
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="username", referencedColumnName = "username")
    private List<Calculation> calculations;

}
