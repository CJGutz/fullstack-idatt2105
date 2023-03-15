package edu.ntnu.idatt2105.calculator.resources;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import edu.ntnu.idatt2105.calculator.models.LoginRequest;
import edu.ntnu.idatt2105.calculator.models.User;
import edu.ntnu.idatt2105.calculator.services.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/token")
@EnableAutoConfiguration
@CrossOrigin("*")
public class TokenResource {

    public static String keyStr = "7nb47n54v986n459nb7e564n9e564yme564issejtgbhsegseohue98536gefew857";
    private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenResource.class);

    private final LoginService loginService;

    public TokenResource(LoginService loginService) {
        super();
        this.loginService = loginService;
    }
    @PostMapping(value = "")
    @ResponseStatus(value = HttpStatus.CREATED)
    public String generateToken(final @RequestBody LoginRequest loginRequest) throws Exception {
        LOGGER.info("Creating token for user: " + loginRequest.getUsername());
        Optional<User> user = loginService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (user.isPresent()) {
            LOGGER.info("User was found, creating token...");
            return generateToken(loginRequest.getUsername());
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access denied, wrong credentials....");
    }

    public String generateToken(final String userId) {
        final Instant now = Instant.now();
        final Algorithm hmac512 = Algorithm.HMAC512(keyStr);;
        final JWTVerifier verifier = JWT.require(hmac512).build();
        return JWT.create()
                .withSubject(userId)
                .withIssuer("idatt2105_token_issuer_app")
                .withIssuedAt(now)
                .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
                .sign(hmac512);
    }

}
