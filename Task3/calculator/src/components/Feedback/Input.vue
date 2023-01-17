<template>
    <div class="m-3">
        <label :id="inputLabel" class="p-5 text-lg">{{ inputLabel }}</label>
        <input :value="modelValue" :placeholder="inputLabel"
            @input="$emit('update:modelValue', ($event.target as HTMLInputElement).value)" :id="inputLabel"
            class="border-4 p-1" :class="error ? 'border-red-500' : ''" v-bind="$attrs" />
        <div v-if="error" class="text-red-500">
            {{ error }}
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

export default defineComponent({
    props: {
        inputLabel: {
            type: String,
            required: true,
        },
        modelValue: {
            type: [String, Number],
            default: '',
        },
        error: {
            type: String,
            default: '',
        }
    }
})
</script>