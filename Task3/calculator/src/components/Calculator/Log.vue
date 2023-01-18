<template>
    <div class="flex-center">
        <div id="log-box">
            <h3>
                Log:
            </h3>
            <ul>
                <li v-for="item in logItems">{{ item }}</li>
            </ul>
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
#log-box {
    width: 100%;
    text-align: start;
    height: 50%;
    border-width: 2px;
    padding: 20px;
    margin: 20px;
    border-color: black;
}

@media (min-width: 640px) {
    #log-box {
        width: 40%;
    }
}

ul {
    padding: 20px;
    max-height: 160px;
    overflow-y: scroll;
    border-bottom: 1px solid black;
}

ul li {
    padding: 8px;
}
</style>
