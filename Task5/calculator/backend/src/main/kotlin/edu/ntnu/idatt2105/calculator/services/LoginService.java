package edu.ntnu.idatt2105.calculator.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import edu.ntnu.idatt2105.calculator.models.User;
import edu.ntnu.idatt2105.calculator.repositories.UserRepository;
import edu.ntnu.idatt2105.calculator.resources.LoginResource;
import edu.ntnu.idatt2105.calculator.resources.TokenResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class LoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginResource.class);

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public Optional<User> login(String username, String password) {
        Optional<User> user = userRepository.findById(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }

    public User register(String username, String password) {
        User newUser = new User(username, password, new ArrayList<>());
        LOGGER.info("Saving user...");
        return userRepository.save(newUser);
    }

    public Optional<User> getUserByToken(String token) {
        final Algorithm hmac512 = Algorithm.HMAC512(TokenResource.keyStr);
        final JWTVerifier verifier = JWT.require(hmac512).build();
        String username = verifier.verify(token).getSubject();
        return userRepository.findById(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

}
