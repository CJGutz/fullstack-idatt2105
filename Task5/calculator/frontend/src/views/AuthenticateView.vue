<template>
    <Register />
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Register from "@/components/Auth/Register.vue";

export default defineComponent({
    components: {
        Register,
    },
})
</script>