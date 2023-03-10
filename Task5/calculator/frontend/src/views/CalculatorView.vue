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
import { LogItem } from "@/components/Calculator/Log.vue";

export default defineComponent({
  components: {
    Intro,
    Calculator,
    Log,
  },
  data: () => ({
    log: [] as LogItem[],
  }),
  methods: {
    addToLog(computation: string, result: number) {
      this.log.push({
        computation,
        result,
      });
    },
  },
});
</script>
