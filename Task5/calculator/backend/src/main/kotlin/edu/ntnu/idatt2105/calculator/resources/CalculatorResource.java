package edu.ntnu.idatt2105.calculator.resources;

import edu.ntnu.idatt2105.calculator.dto.CalculationDTO;
import edu.ntnu.idatt2105.calculator.models.Calculation;
import edu.ntnu.idatt2105.calculator.models.User;
import edu.ntnu.idatt2105.calculator.services.CalculatorService;
import edu.ntnu.idatt2105.calculator.services.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CalculatorResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorResource.class);

    private final CalculatorService calculatorService;
    private final LoginService loginService;

    public CalculatorResource(CalculatorService calculatorService, LoginService loginService) {
        super();
        this.calculatorService = calculatorService;
        this.loginService = loginService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/calculate")
    @ResponseBody
    public ResponseEntity<Object> calculate(@RequestBody Calculation body, HttpServletRequest request) {
        User user = loginService.getUserByToken(request.getHeader("Authorization").substring(7)).orElseThrow();

        LOGGER.info("Received: " + body.getExpression());
        if (body.getExpression() == null) return ResponseEntity.noContent().build();
        String result;
        try {
            result = calculatorService.calculate(body.getExpression());
        } catch (NumberFormatException e) {
            LOGGER.error("Error with expression format: " + e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Received error from calculation service " + e.getMessage());
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        LOGGER.info("Sending: " + result);

        Calculation calculation = calculatorService.saveCalculation(body.getExpression(), result);
        user.getCalculations().add(calculation);
        CalculationDTO calculationDTO = modelMapper.map(calculation, CalculationDTO.class);

        return ResponseEntity.ok(calculationDTO);
    }

    @GetMapping("/calculations")
    public ResponseEntity<List<CalculationDTO>> getCalculations(HttpServletRequest request) {
        User user = loginService.getUserByToken(request.getHeader("Authorization").substring(7)).orElseThrow();
        List<Calculation> calculations = calculatorService.getCalculationsByUser(user);

        List<CalculationDTO> calculationDTOS = calculations.stream().map(c -> modelMapper.map(c, CalculationDTO.class)).toList();
        return ResponseEntity.ok(calculationDTOS);
    }


}
