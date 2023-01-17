<template>
    <div class="text-base rounded-md shadow-lg py-2 m-1"
        :class="background, disabled ? '' : 'hover:opacity-95 cursor-pointer'" :disabled="disabled">
        {{ type }}
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    name: 'Button',
    props: {
        type: String,
        background: {
            type: String,
            default: 'bg-gray-600',
        },
    },
    computed: {
        disabled() {
            return this.type === '';
        },
    },
});
</script>
