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
import { postCalculation } from "@/services/calculator";

export default defineComponent({
  name: "Calculator",
  components: {
    Button,
  },
  methods: {
    async compute() {
      let result = await this.evaluateExpression(this.value);
      if (result == undefined || result == null) {
        this.value = "Invalid"
        return
      }

      result = result.toString()
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
    async evaluateExpression(expression: string): Promise<string> {
      return postCalculation(expression)
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
