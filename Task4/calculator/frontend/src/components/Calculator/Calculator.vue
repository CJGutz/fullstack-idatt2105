<template>
  <div class="flex-center">
    <div id="calculator">
      <div id="calculator-display" data-testid="calculator-display">
        {{ value }}
      </div>

      <div class="flex-center">
        <div id="numbers-plus">
          <div class="buttons-3-grid">
            <Button type="C" @click="
  value = '0';
showingResult = true;
            " data-testid="calculator-button-C" />
            <Button type="ANS" @click="
  value = '0';
showingResult = true;
            " data-testid="calculator-button-AC" />
            <Button type="DEL" @click="value = value.replace(/(\s+)*(\S)(\s*)$/, '')"
              data-testid="calculator-button-DEL" />
          </div>
          <div class="buttons-3-grid">
            <Button v-for="number in 9" :key="number" :type="number.toString()" @click="addToValue(number.toString())"
              :data-testid="`calculator-button-${number}`" />
          </div>
          <div class="buttons-3-grid">
            <Button v-for="button in bottom" :key="button" :type="button" @click="addToValue(button)"
              :data-testid="`calculator-button-${button}`" />
          </div>
        </div>
        <div id="operators">
          <Button v-for="button in operators" :key="button" :type="button" @click="addOperator(button)"
            :data-testid="`calculator-button-${button}`" />
          <Button type="=" background="orange" @click="compute()" data-testid="calculator-button-=" />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Button from "./Button.vue";

export default defineComponent({
  name: "Calculator",
  components: {
    Button,
  },
  methods: {
    compute() {
      let result = this.evaluateExpression(this.value).toString();
      this.showingResult = true;
      this.$emit("finish-computation", this.value, result);
      this.value = result;
    },
    addToValue(value: string) {
      if (this.showingResult) {
        this.value = value;
        this.showingResult = false;
      } else {
        if (value === "." && this.value.match(/\.+[\d]*\s*$/)) return;
        this.value += value;
      }
    },
    addOperator(operator: string) {
      if (this.value.match(/\d\s*$/) || this.showingResult) {
        this.value += ` ${operator} `;
        this.showingResult = false;
      }
    },
    evaluateExpression(expression: string): string {
      let outputQueue = "";
      let operatorStack = [] as string[];
      let operators = {
        "/": 2,
        "*": 2,
        "+": 1,
        "-": 1,
      } as { [key: string]: number };
      expression = expression.replace(/\s+/g, "");
      let currentNumber = "";
      for (let i = 0; i < expression.length; i++) {
        let token = expression[i];
        if (!isNaN(parseInt(token)) || token === ".") {
          currentNumber += token;
          if (i === expression.length - 1) {
            outputQueue += currentNumber + " ";
          }
        } else {
          outputQueue += currentNumber + " ";
          currentNumber = "";
          if (token in operators) {
            let o1 = token;
            let o2 = operatorStack[operatorStack.length - 1];
            while (o2 in operators && operators[o1] <= operators[o2]) {
              outputQueue += operatorStack.pop() + " ";
              o2 = operatorStack[operatorStack.length - 1];
            }
            operatorStack.push(o1);
          } else {
            return "Error: Invalid characters";
          }
        }
      }
      while (operatorStack.length > 0) {
        outputQueue += operatorStack.pop() + " ";
      }
      let rpn = outputQueue;
      let resultStack = [] as string[];
      let tokens = rpn.split(" ");
      for (let i = 0; i < tokens.length; i++) {
        let token = tokens[i];
        if (!isNaN(parseFloat(token))) {
          resultStack.push(parseFloat(token).toString());
        } else {
          if (resultStack.length > 1) {
            let a = parseFloat(resultStack.pop() || "0");
            let b = parseFloat(resultStack.pop() || "0");
            let result = 0;
            switch (token) {
              case "+":
                result = b + a;
                break;
              case "-":
                result = b - a;
                break;
              case "*":
                result = b * a;
                break;
              case "/":
                result = b / a;
                break;
            }
            resultStack.push(result.toString());
          }
        }
      }
      return resultStack[0];
    },
  },
  data: () => ({
    bottom: ["", "0", "."],
    operators: ["+", "-", "*", "/"],
    value: "0",
    showingResult: true,
  }),
});
</script>

<style scoped>
#calculator-display {
  padding-left: 1.25rem;
  padding-right: 1.25rem;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  min-width: fit-content;
  overflow: auto;
  text-align: center;
  margin-bottom: 1rem;
  background-color: rgb(51 65 85);
  font-size: 1.5rem;
  font-weight: 600;
  line-height: 2rem;
}

#calculator {
  padding: 2.5rem;
  text-align: center;
  color: rgb(255 255 255);
  width: 100%;
}

@media (min-width: 640px) {
  #calculator {
    width: 40%;
  }
}

#numbers-plus {
  flex-basis: 75%;
}

.buttons-3-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
}

#operators {
  display: grid;
  grid-template-columns: repeat(1, minmax(0, 1fr));
  flex-basis: 25%;
}
</style>
