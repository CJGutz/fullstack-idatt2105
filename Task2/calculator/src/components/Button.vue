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

<style scoped>
.text-base {
    font-size: 1rem
        /* 16px */
    ;
    line-height: 1.5rem
        /* 24px */
    ;
}

.rounded-md {
    border-radius: 0.375rem
        /* 6px */
    ;
}

.shadow-lg {
    --tw-shadow: 0 10px 15px -3px rgb(0 0 0 / 0.1), 0 4px 6px -4px rgb(0 0 0 / 0.1);
    --tw-shadow-colored: 0 10px 15px -3px var(--tw-shadow-color), 0 4px 6px -4px var(--tw-shadow-color);
    box-shadow: var(--tw-ring-offset-shadow, 0 0 #0000), var(--tw-ring-shadow, 0 0 #0000), var(--tw-shadow);
}

.py-2 {
    padding-top: 0.5rem
        /* 8px */
    ;
    padding-bottom: 0.5rem
        /* 8px */
    ;
}

.m-1 {
    margin: 0.25rem
        /* 4px */
    ;
}

.hover\:opacity-95:hover {
    opacity: 0.95;
}

.cursor-pointer {
    cursor: pointer;
}

.bg-gray-600 {
    --tw-bg-opacity: 1;
    background-color: rgb(75 85 99 / var(--tw-bg-opacity));
}
</style>