<template>
  <Intro />
  <Calculator @finish-computation="addToLog" />
  <Log :log="log" />
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import Calculator from './components/Calculator.vue';
import Intro from './components/Intro.vue';
import Log from './components/Log.vue';

interface LogItem {
  computation: string,
  result: number,
}

export default defineComponent({
  name: 'App',
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
}
);
</script>
