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
<style scoped>
.px-5 {
    padding-left: 1.25rem
        /* 20px */
    ;
    padding-right: 1.25rem
        /* 20px */
    ;
}

.py-2 {
    padding-top: 0.5rem
        /* 8px */
    ;
    padding-bottom: 0.5rem
        /* 8px */
    ;
}

.text-center {
    text-align: center;
}

.mb-4 {
    margin-bottom: 1rem
        /* 16px */
    ;
}

.bg-slate-700 {
    --tw-bg-opacity: 1;
    background-color: rgb(51 65 85 / var(--tw-bg-opacity));
}

.text-2xl {
    font-size: 1.5rem
        /* 24px */
    ;
    line-height: 2rem
        /* 32px */
    ;
}

.font-semibold {
    font-weight: 600;
}
</style>