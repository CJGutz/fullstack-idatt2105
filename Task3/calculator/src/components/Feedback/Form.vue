<template>
    <form @submit.prevent="submit">
        <fieldset class="mb-10">
            <Input input-label="Name" v-model="name" :error="errors.name" />
            <Input input-label="Email" v-model="email" :error="errors.email" type="email" />
            <Input input-label="Message" v-model="message" :error="errors.message" class="h-32" />
        </fieldset>
        <button :disabled="hasErrors" type="submit" :class="hasErrors ? 'opacity-50' : ''"
            class="border-2 border-black px-3 bg-slate-800 text-white">Submit</button>
    </form>
    <div v-if="submitMessage" class="text-xl font-bold m-10 border-b-yellow-300 border-b-4">
        {{ submitMessage }}
    </div>
</template>

<script lang="ts">
import { defineComponent, inject, ref } from 'vue';
import Input from '@/components/Feedback/Input.vue';
import { useForm, useField, FieldContext } from 'vee-validate';
import { object as yupObject, string as yupString } from 'yup';
import { Store } from 'vuex';
import axios from 'axios';

export default defineComponent({
    components: {
        Input,
    },
    setup() {
        const store = inject('store') as Store<{ feedback: { [key: string]: string } }>

        const schema = yupObject({
            name: yupString().required('Name is required'),
            email: yupString().required('Email is required').email('Not a valid email'),
            message: yupString().required('Message is required'),
        })

        const { handleSubmit, errors } = useForm({
            validationSchema: schema,
        })

        const { value: name } = useField('name') as FieldContext<string>
        const { value: email } = useField('email') as FieldContext<string>
        const { value: message, setValue: setMessage } = useField('message') as FieldContext<string>

        if (store.state.feedback.name && store.state.feedback.email) {
            name.value = store.state.feedback.name
            email.value = store.state.feedback.email
        }

        const submitMessage = ref('')

        const submit: any = handleSubmit((values) => {
            axios.post('http://localhost:3000/data/', {})
                .then(() => {
                    setMessage('')
                    store.dispatch('saveFeedbackInfo', values)
                    submitMessage.value = 'Thanks for your feedback!'
                    setTimeout(() => {
                        submitMessage.value = ''
                    }, 3000)
                }).catch(() => {
                    submitMessage.value = 'Something went wrong. Please try again later.'
                })
        })

        return {
            name,
            email,
            message,
            errors,
            submit,
            submitMessage,
        }
    },
    computed: {
        hasErrors(): boolean {
            return Object.keys(this.errors).length > 0;
        }
    },
})
</script>