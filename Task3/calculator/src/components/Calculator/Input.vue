<template>
    <input :value="value" class="px-5 py-2 text-center mb-4 bg-slate-700 text-2xl font-semibold" />
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    props: {
        value: {
            type: String,
            default: 0,
        }
    }
});
</script>