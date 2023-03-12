package edu.ntnu.idatt2105.calculator.services;

import edu.ntnu.idatt2105.calculator.models.Calculation;
import edu.ntnu.idatt2105.calculator.models.User;
import edu.ntnu.idatt2105.calculator.repositories.CalculationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CalculatorService {

    private final CalculationRepository calculationRepository;

    public CalculatorService(CalculationRepository calculationRepository) {
        super();
        this.calculationRepository = calculationRepository;
    }

    public Calculation saveCalculation(String expression, String result) {
        Calculation calculation = new Calculation();
        calculation.setExpression(expression);
        calculation.setResult(result);

        calculationRepository.save(calculation);
        return calculation;
    }

    public List<Calculation> getAllCalculations() {
        return calculationRepository.findAll();
    }

    public List<Calculation> getCalculationsByUser(User user) {
        return user.getCalculations();
    }

    /**
     * Calculates from multi-operational expression
     *
     * @param expression String
     * @return String
     * @throws NumberFormatException thrown if invalid characters
     */
    public String calculate(String expression) throws NumberFormatException {

        List<String> outputQueue = new ArrayList<>();
        List<Character> operatorStack = new ArrayList<>();
        Map<Character, Integer> operators = Map.of('+', 1, '-', 1, '*', 2, '/', 2);
        expression = expression.replaceAll("\\s+", "");
        StringBuilder currentNumber = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char token = expression.charAt(i);
            if (Character.isDigit(token) || token == '.') {
                currentNumber.append(token);
                if (i == expression.length() - 1)
                    outputQueue.add(currentNumber.toString());
            }
            else {
                outputQueue.add(currentNumber.toString());
                currentNumber = new StringBuilder();
                if (operators.containsKey(token)) {
                    if (operatorStack.size() > 0) {
                        char o2 = operatorStack.get(operatorStack.size() - 1);
                        while (operators.containsKey(o2) && operators.get(token) <= operators.get(o2)) {
                            outputQueue.add(operatorStack.remove(operatorStack.size() - 1).toString());
                            if (operatorStack.size() == 0) break;
                            o2 = operatorStack.get(operatorStack.size() - 1);
                        }
                    }
                    operatorStack.add(token);
                } else {
                    throw new NumberFormatException("Invalid characters");
                }
            }
        }
        while (operatorStack.size() > 0) {
            outputQueue.add(operatorStack.remove(operatorStack.size() - 1).toString());
        }
        List<String> resultStack = new ArrayList<>();
        for (String token : outputQueue) {
            try {
                double number = Double.parseDouble(token);
                resultStack.add(String.valueOf(number));
            } catch (NumberFormatException e) {
                if (resultStack.size() > 1) {
                    double a;
                    double b;
                    try {
                        a = Double.parseDouble(resultStack.remove(resultStack.size() - 1));
                    } catch (NumberFormatException e2) {
                        a = 0;
                    }
                    try {
                        b = Double.parseDouble(resultStack.remove(resultStack.size() - 1));
                    } catch (NumberFormatException e2) {
                        b = 0;
                    }
                    double result = switch (token) {
                        case "+" -> b + a;
                        case "-" -> b - a;
                        case "*" -> b * a;
                        case "/" -> b / a;
                        default -> 0;
                    };
                    resultStack.add(String.valueOf(result));
                }
            }
        }
        return resultStack.get(0);
    }
}
