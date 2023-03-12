package edu.ntnu.idatt2105.calculator.resources;

import edu.ntnu.idatt2105.calculator.dto.CalculationDTO;
import edu.ntnu.idatt2105.calculator.models.Calculation;
import edu.ntnu.idatt2105.calculator.services.CalculatorService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class CalculatorResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculatorResource.class);

    private final CalculatorService calculatorService;

    public CalculatorResource(CalculatorService calculatorService) {
        super();
        this.calculatorService = calculatorService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Object> calculate(@RequestBody Calculation body) {
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
        CalculationDTO calculationDTO = modelMapper.map(calculation, CalculationDTO.class);

        return ResponseEntity.ok(calculationDTO);
    }

}
