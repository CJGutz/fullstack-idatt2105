<template>
    <div class="flex justify-center">
        <div class="w-full sm:w-2/5 text-start h-1/2 border-2 p-5 m-5 border-black">
            <div class="text-lg font-bold pb-5">
                Log:
            </div>
            <div class="p-5 max-h-48 overflow-y-scroll">
                <ul class="list-disc">
                    <li class="p-2" v-for="item in logItems">{{ item }}</li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';

interface LogItem {
    computation: string,
    result: number,
}

export default defineComponent({
    name: 'Log',
    props: {
        log: {
            type: Array as () => LogItem[],
            required: true,
        },
    },
    computed: {
        logItems() {
            return this.log.map((item) => `${item.computation} = ${item.result}`);
        },
    },
});
</script>

<style scoped>
.p-2 {
    padding: 0.5rem
        /* 8px */
    ;
}

.list-disc {
    list-style-type: disc;
}

.overflow-y-scroll {
    overflow-y: scroll;
}

.max-h-48 {
    max-height: 12rem
        /* 192px */
    ;
}

.pb-5 {
    padding-bottom: 1.25rem
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

.border-black {
    --tw-border-opacity: 1;
    border-color: rgb(0 0 0 / var(--tw-border-opacity));
}

.m-5 {
    margin: 1.25rem
        /* 20px */
    ;
}

.border-2 {
    border-width: 2px;
}

.p-5 {
    padding: 1.25rem
        /* 20px */
    ;
}

.h-1\/2 {
    height: 50%;
}

.text-start {
    text-align: start;
}

.flex {
    display: flex;
}

.justify-center {
    justify-content: center;
}

.w-full {
    width: 100%;
}

@media (min-width: 640px) {
    .sm\:w-2\/5 {
        width: 40%;
    }
}
</style>
