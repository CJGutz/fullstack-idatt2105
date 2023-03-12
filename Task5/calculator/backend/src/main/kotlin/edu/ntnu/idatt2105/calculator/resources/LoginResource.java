package edu.ntnu.idatt2105.calculator.resources;

import edu.ntnu.idatt2105.calculator.dto.UserDTO;
import edu.ntnu.idatt2105.calculator.models.LoginRequest;
import edu.ntnu.idatt2105.calculator.models.User;
import edu.ntnu.idatt2105.calculator.services.LoginService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin("*")
public class LoginResource {

    private final LoginService loginService;

    @Autowired
    private ModelMapper modelMapper;

    public LoginResource(LoginService loginService) {
        super();
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody User user) {
        Optional<User> optionalUser = loginService.login(user.getUsername(), user.getPassword());
        if (optionalUser.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        UserDTO userDTO = modelMapper.map(optionalUser.get(), UserDTO.class);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody LoginRequest request) {
        User user;
        try {
            user = loginService.register(request.getUsername(), request.getPassword());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return ResponseEntity.ok(userDTO);

    }

}
