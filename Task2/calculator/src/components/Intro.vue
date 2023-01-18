<template>
  <div class="text-center">
    <div class="text-3xl p-5 font-bold">Calculator</div>
    <div class="text-lg p-3">This is a simple calculator</div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
  name: 'Intro',
});
</script>

<style scoped>
.text-center {
  text-align: center;
}

.text-3xl {
  font-size: 1.875rem
    /* 30px */
  ;
  line-height: 2.25rem
    /* 36px */
  ;
}

.p-5 {
  padding: 1.25rem
    /* 20px */
  ;
}

.font-bold {
  font-weight: 700;
}

.text-lg {
  font-size: 1.125rem
    /* 18px */
  ;
  line-height: 1.75rem
    /* 28px */
  ;
}

.p-3 {
  padding: 0.75rem
    /* 12px */
  ;
}
</style>