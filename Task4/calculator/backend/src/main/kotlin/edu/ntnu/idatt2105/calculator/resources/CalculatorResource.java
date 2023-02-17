package edu.ntnu.idatt2105.calculator.resources;

import edu.ntnu.idatt2105.calculator.models.Expression;
import edu.ntnu.idatt2105.calculator.services.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class CalculatorResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorResource.class);

    @PostMapping("/calculate")
    @ResponseBody
    public ResponseEntity<String> calculate(@RequestBody Expression body) {
        LOGGER.info("Received: " + body.getExp());
        if (body.getExp() == null) return ResponseEntity.noContent().build();
        String result;
        try {
            result = new CalculatorService().calculate(body.getExp());
        } catch (NumberFormatException e) {
            LOGGER.error("Error with expression format: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Received error from calculation service " + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        LOGGER.info("Sending: " + result);
        return ResponseEntity.ok(result);
    }

}
