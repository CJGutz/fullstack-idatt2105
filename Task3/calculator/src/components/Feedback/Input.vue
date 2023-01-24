<template>
  <div>
    <label :id="inputLabel">{{ inputLabel }}</label>
    <component
      :value="modelValue"
      :placeholder="inputLabel"
      @input="
        $emit('update:modelValue', ($event.target as HTMLInputElement).value)
      "
      :id="inputLabel"
      :style="{ borderColor: error ? 'red' : '' }"
      v-bind="$attrs"
      :is="component"
    />
    <div v-if="error" id="error-message">
      {{ error }}
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  props: {
    inputLabel: {
      type: String,
      required: true,
    },
    modelValue: {
      type: [String, Number],
      default: "",
    },
    error: {
      type: String,
      default: "",
    },
    component: {
      type: String,
      default: "input",
    },
  },
});
</script>

<style scoped>
label {
  padding: 20px;
}

input,
textarea {
  border: 4px black solid;
  padding: 4px;
}

#error-message {
  color: red;
}
</style>
