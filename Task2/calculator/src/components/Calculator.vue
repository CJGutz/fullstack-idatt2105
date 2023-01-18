<template>
  <div class="flex justify-center">
    <div class="p-10 text-center text-white w-full sm:w-2/5">
      <div class="px-5 py-2 min-w-fit overflow-auto text-center mb-4 bg-slate-700 text-2xl font-semibold">
        {{ value }}
      </div>

      <div class="flex">
        <div class="basis-3/4">
          <div class="grid grid-cols-3">
            <Button type="C" @click="value = '0'; showingResult = true;" />
            <Button type="ANS" @click="value = '0'; showingResult = true;" />
            <Button type="DEL" @click="value = value.replace(/(\s+)*(\S)(\s*)$/, '')" />
          </div>
          <div class="grid grid-cols-3">
            <Button v-for="number in 9" :key="number" :type="number.toString()"
              @click="addToValue(number.toString())" />
          </div>
          <div class="grid grid-cols-3">
            <Button v-for="button in bottom" :key="button" :type="button" @click="addToValue(button)" />
          </div>
        </div>
        <div class="grid grid-cols-1 basis-1/4">
          <Button v-for="button in operators" :key="button" :type="button" @click="addOperator(button)" />
          <Button type="=" background="bg-orange-400" @click="compute()" />
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Button from './Button.vue';

export default defineComponent({
  name: 'Calculator',
  components: {
    Button,
  },
  methods: {
    compute() {
      let result = this.evaluateExpression(this.value).toString()
      this.showingResult = true
      this.$emit('finish-computation', this.value, result)
      this.value = result
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
        "/": 3,
        "*": 3,
        "+": 2,
        "-": 2
      } as { [key: string]: number }
      expression = expression.replace(/\s+/g, "");
      let currentNumber = "";
      for (let i = 0; i < expression.length; i++) {
        let token = expression[i];
        if (!isNaN(parseInt(token)) || token === ".") {
          currentNumber += token;
          if (i === expression.length - 1) {
            outputQueue += currentNumber + " ";
          }
        }
        else {
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
            return "Error: Invalid characters"
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
.bg-orange-400 {
  --tw-bg-opacity: 1;
  background-color: rgb(251 146 60 / var(--tw-bg-opacity));
}

.flex {
  display: flex;
}

.grid {
  display: grid;
}

.justify-center {
  justify-content: center;
}

.text-center {
  text-align: center;
}

.text-white {
  --tw-text-opacity: 1;
  color: rgb(255 255 255 / var(--tw-text-opacity));
}

.w-full {
  width: 100%;
}

@media (min-width: 640px) {
  .sm\:w-2\/5 {
    width: 40%;
  }
}

.px-5 {
  padding-left: 1.25rem
    /* 20px */
  ;
  padding-right: 1.25rem
    /* 20px */
  ;
}

.py-2 {
  padding-top: 0.5rem
    /* 8px */
  ;
  padding-bottom: 0.5rem
    /* 8px */
  ;
}

.min-w-fit {
  min-width: fit-content;
}

.overflow-auto {
  overflow: auto;
}

.mb-4 {
  margin-bottom: 1rem
    /* 16px */
  ;
}

.bg-slate-700 {
  --tw-bg-opacity: 1;
  background-color: rgb(51 65 85 / var(--tw-bg-opacity));
}

.text-2xl {
  font-size: 1.5rem
    /* 24px */
  ;
  line-height: 2rem
    /* 32px */
  ;
}

.font-semibold {
  font-weight: 600;
}

.p-10 {
  padding: 2.5rem
    /* 40px */
  ;
}

.basis-3\/4 {
  flex-basis: 75%;
}

.grid-cols-3 {
  grid-template-columns: repeat(3, minmax(0, 1fr));
}

.basis-3\/4 {
  flex-basis: 75%;
}

.grid-cols-1 {
  grid-template-columns: repeat(1, minmax(0, 1fr));
}

.basis-1\/4 {
  flex-basis: 25%;
}
</style>