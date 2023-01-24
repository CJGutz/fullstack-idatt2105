<template>
  <div class="text-3xl font-bold">Send us your feedback</div>
  <FeedbackForm />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import FeedbackForm from "@/components/Feedback/Form.vue";

export default defineComponent({
  components: {
    FeedbackForm,
  },
});
</script>
