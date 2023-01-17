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
