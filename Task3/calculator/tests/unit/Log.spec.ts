import Log from '@/components/Calculator/Log.vue'
import { mount } from '@vue/test-utils'
import { describe, test, expect } from 'vitest'

describe('Log', () => {
    test('Log has no list items at mount', () => {
        const wrapper = mount(Log, { props: { log: [] } })
        const listItems = wrapper.findAll('li')
        expect(listItems.length).toBe(0)
    })

    test('Operation and calculation is displayed in list', () => {
        const wrapper = mount(Log, {
            props: {
                log: [{
                    computation: '5 - 2',
                    result: 3
                }]
            }
        })
        const listItems = wrapper.findAll('li')
        expect(listItems.length).toBe(1)
        expect(listItems[0].text()).toBe('5 - 2 = 3')
    })
})