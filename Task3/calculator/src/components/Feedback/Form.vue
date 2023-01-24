<template>
    <form @submit.prevent="submit">
        <fieldset>
            <Input input-label="Name" v-model="name" :error="errors?.name" data-testid="form-input-name" />
            <Input input-label="Email" v-model="email" :error="errors?.email" type="email"
                data-testid="form-input-email" />
            <Input input-label="Message" v-model="message" :error="errors?.message" component="textarea"
                data-testid="form-input-message" />
        </fieldset>
        <button :disabled="hasErrors" type="submit">Submit</button>
    </form>
    <h2 v-if="submitMessage" id="submit-message">
        {{ submitMessage }}
    </h2>
</template>

<script lang="ts">
import { defineComponent, inject, ref } from "vue";
import Input from "./Input.vue";
import { useForm, useField, FieldContext } from "vee-validate";
import { object as yupObject, string as yupString } from "yup";
import { Store } from "vuex";
import { postForm } from "@/services/form";

export default defineComponent({
    components: {
        Input,
    },
    setup() {
        const store = inject("store") as Store<{
            feedback: { [key: string]: string };
        }>;

        const schema = yupObject({
            name: yupString().required("Name is required"),
            email: yupString()
                .required("Email is required")
                .email("Not a valid email"),
            message: yupString().required("Message is required"),
        });

        const { handleSubmit, errors } = useForm({
            validationSchema: schema,
        });

        const { value: name } = useField("name") as FieldContext<string>;
        const { value: email } = useField("email") as FieldContext<string>;
        const { value: message, setValue: setMessage } = useField(
            "message"
        ) as FieldContext<string>;

        if (store.state.feedback.name && store.state.feedback.email) {
            name.value = store.state.feedback.name;
            email.value = store.state.feedback.email;
        }

        const submitMessage = ref("");

        const submit = handleSubmit(async (values) => {
            if (await postForm()) {
                setMessage("");
                store.dispatch("saveFeedbackInfo", values);
                submitMessage.value = "Thanks for your feedback!";
            } else {
                submitMessage.value = "Something went wrong. Please try again later.";
            }
            setTimeout(() => {
                submitMessage.value = "";
            }, 3000);
        });

        return {
            name,
            email,
            message,
            errors,
            submit,
            submitMessage,
        };
    },
    computed: {
        hasErrors(): boolean {
            return Object.keys(this.errors ? this.errors : {}).length > 0;
        },
    },
});
</script>

<style scoped>
fieldset {
    margin-bottom: 40px;
}

button {
    border-width: 2px;
    border: black solid 2px;
    padding-left: 12px;
    padding-right: 12px;
    background-color: rgb(30 41 59);
    color: white;
}

.error-button {
    opacity: 0.5;
}

#submit-message {
    margin-top: 40px;
    border-bottom: rgb(247, 247, 12) solid 4px;
}
</style>
