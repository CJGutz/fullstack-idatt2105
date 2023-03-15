<template>
  <Intro />
  <Calculator @finish-computation="addToLog" />
  <Log :log="log" />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Calculator from "@/components/Calculator/Calculator.vue";
import Intro from "@/components/Calculator/Intro.vue";
import Log from "@/components/Calculator/Log.vue";
import { getHistory, Calculation } from "@/services/calculator"

export default defineComponent({
  components: {
    Intro,
    Calculator,
    Log,
  },
  data: () => ({
    log: [] as Calculation[],
  }),
  methods: {
    addToLog(expression: string, result: string) {
      this.log.push({
        expression,
        result,
      });
    },
  },
  async mounted() {
    let token = sessionStorage.getItem("token")
    if (!token) {
      this.$router.push("/register");
      return;
    }
    let calculations = await getHistory(token);
    this.log = calculations;
  },
});
</script>
