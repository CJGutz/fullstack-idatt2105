import Form from '@/components/Feedback/Form.vue'
import { mount } from '@vue/test-utils'
import axios from 'axios'

jest.mock('@/services/form')
jest.mock('axios')
jest.spyOn(axios, 'post').mockResolvedValue({ "data": {} })
describe('Form', () => {
    test('Form displays name, email and message inputs', () => {
        const wrapper = mount(Form)
        const nameInput = wrapper.find('[data-testid="form-name"]')
        const emailInput = wrapper.find('[data-testid="form-email"]')
        const messageInput = wrapper.find('[data-testid="form-message"]')
        expect(nameInput.exists()).toBe(true)
        expect(emailInput.exists()).toBe(true)
        expect(messageInput.exists()).toBe(true)
    })
})