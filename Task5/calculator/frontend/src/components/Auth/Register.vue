<template>
    <form @submit.prevent="submit">
        <Input type="text" v-model="username" input-label="Username" />
        <Input type="text" v-model="password" input-label="Password" />
        <button type="submit">Register</button>
        <p>{{ submitMessage }}</p>
    </form>
</template>

<script lang="ts">
import { defineComponent } from "vue";
import Input from "../Feedback/Input.vue";
import { registerUser, retrieveNewToken } from "@/services/auth"

export default defineComponent({
    components: {
        Input,
    },
    data: () => ({
        username: "",
        password: "",
        submitMessage: "",
    }),
    methods: {
        async submit() {
            await registerUser(this.username, this.password)
            let token: string | null = await retrieveNewToken(this.username, this.password)
            if (token == null) {
                this.submitMessage = "Invalid username or password"
                return
            } else {
                this.submitMessage = "Successfully registered"
            }
            sessionStorage.setItem("token", token)
        }
    }
})
</script>

<style></style>