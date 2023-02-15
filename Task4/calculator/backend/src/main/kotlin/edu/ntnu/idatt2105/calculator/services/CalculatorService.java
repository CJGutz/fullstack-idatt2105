package edu.ntnu.idatt2105.calculator.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CalculatorService {

    public String calculate(String expression) {

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
                    return "Error: Invalid characters";
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
