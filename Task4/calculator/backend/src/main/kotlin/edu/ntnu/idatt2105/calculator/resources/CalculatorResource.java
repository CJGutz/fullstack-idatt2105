package edu.ntnu.idatt2105.calculator.resources;

import edu.ntnu.idatt2105.calculator.services.CalculatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorResource {

    @PostMapping("/calculate")
    public String calculate(@RequestBody String body) {
        return new CalculatorService().calculate(body);
    }


}
