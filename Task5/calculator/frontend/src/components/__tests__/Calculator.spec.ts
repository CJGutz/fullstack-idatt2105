import Calculator from "@/components/Calculator/Calculator.vue";
import { mount } from "@vue/test-utils";
import { describe, test, expect } from "vitest";

describe("Calculator", () => {
  test("Calculator render display with 0 successfully at mount", () => {
    const wrapper = mount(Calculator);
    const displayText = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayText).toBe("0");
  });

  test("Calculator render display with 1 successfully after click 1", async () => {
    const wrapper = mount(Calculator);
    const button = wrapper.find('[data-testid="calculator-button-1"]');
    await button.trigger("click");
    const displayText = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayText).toBe("1");
  });

  test('Calculator displays 3 after clicking "5", "-", and "2"', async () => {
    const wrapper = mount(Calculator);
    const button5 = wrapper.find('[data-testid="calculator-button-5"]');
    const buttonMinus = wrapper.find('[data-testid="calculator-button--"]');
    const button2 = wrapper.find('[data-testid="calculator-button-2"]');
    const buttonEqual = wrapper.find('[data-testid="calculator-button-="]');
    await button5.trigger("click");
    await buttonMinus.trigger("click");
    await button2.trigger("click");
    await buttonEqual.trigger("click");
    const displayText = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayText).toBe("3");
  });

  test("Calculator displays 0 after clicking number then C", async () => {
    const wrapper = mount(Calculator);
    const button5 = wrapper.find('[data-testid="calculator-button-5"]');
    const buttonClear = wrapper.find('[data-testid="calculator-button-C"]');
    await button5.trigger("click");
    await buttonClear.trigger("click");
    const displayText = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayText).toBe("0");
  });

  test("Calculations are done in order of operations", async () => {
    const wrapper = mount(Calculator);
    const button5 = wrapper.find('[data-testid="calculator-button-5"]');
    const buttonMinus = wrapper.find('[data-testid="calculator-button--"]');
    const button2 = wrapper.find('[data-testid="calculator-button-2"]');
    const buttonMultiply = wrapper.find('[data-testid="calculator-button-*"]');
    const button3 = wrapper.find('[data-testid="calculator-button-3"]');
    const buttonEqual = wrapper.find('[data-testid="calculator-button-="]');
    await button5.trigger("click");
    await buttonMinus.trigger("click");
    await button2.trigger("click");
    await buttonMultiply.trigger("click");
    await button3.trigger("click");
    await buttonEqual.trigger("click");
    const displayText = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayText).toBe("-1");
  });

  test("Calculator can handle decimal operations", async () => {
    const wrapper = mount(Calculator);
    const button5 = wrapper.find('[data-testid="calculator-button-5"]');
    const butonDivide = wrapper.find('[data-testid="calculator-button-/"]');
    const button0 = wrapper.find('[data-testid="calculator-button-0"]');
    const buttonDot = wrapper.find('[data-testid="calculator-button-."]');
    const button1 = wrapper.find('[data-testid="calculator-button-1"]');
    const buttonEqual = wrapper.find('[data-testid="calculator-button-="]');
    await button5.trigger("click");
    await butonDivide.trigger("click");
    await button0.trigger("click");
    await buttonDot.trigger("click");
    await button1.trigger("click");
    await buttonEqual.trigger("click");
    const displayText = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayText).toBe("50");
  });

  test("DEL removes the last input and not whitespace", async () => {
    const wrapper = mount(Calculator);
    const button5 = wrapper.find('[data-testid="calculator-button-5"]');
    const buttonMinus = wrapper.find('[data-testid="calculator-button--"]');
    const buttonDEL = wrapper.find('[data-testid="calculator-button-DEL"]');
    await button5.trigger("click");
    await buttonMinus.trigger("click");
    const displayTextFirst = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayTextFirst).toBe("5 -");
    await buttonDEL.trigger("click");
    const displayTextSecond = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayTextSecond).toBe("5");
  });

  test("Several operations can not be next to each other", async () => {
    const wrapper = mount(Calculator);
    const button5 = wrapper.find('[data-testid="calculator-button-5"]');
    const buttonMinus = wrapper.find('[data-testid="calculator-button--"]');
    const buttonMultiply = wrapper.find('[data-testid="calculator-button-*"]');
    await button5.trigger("click");
    await buttonMinus.trigger("click");
    await buttonMultiply.trigger("click");
    const displayText = wrapper
      .find('[data-testid="calculator-display"]')
      .text();
    expect(displayText).toBe("5 -");
  });
});
