<template>
  <div
    id="base-button"
    :class="[background, disabled ? '' : 'undisabled-button']"
    :disabled="disabled"
  >
    {{ type }}
  </div>
</template>

<script lang="ts">
import { defineComponent } from "vue";

export default defineComponent({
  name: "Button",
  props: {
    type: String,
    background: {
      type: String,
      default: "gray",
    },
  },
  computed: {
    disabled() {
      return this.type === "";
    },
  },
});
</script>

<style scoped>
#base-button {
  font-size: 1rem;
  line-height: 1.5rem;
  border-radius: 6px;
  padding-top: 8px;
  padding-bottom: 8px;
  margin: 4px;
  background-color: rgb(75 85 99);
}

.undisabled-button:hover {
  opacity: 95%;
}

.undisabled-button {
  cursor: pointer;
}

.orange {
  background-color: rgb(251 146 60) !important;
}
</style>
