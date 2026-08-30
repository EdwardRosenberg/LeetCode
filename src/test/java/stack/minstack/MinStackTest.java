package stack.minstack;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinStackTest {

    @Test
    void exampleSequence() {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        assertEquals(-3, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.top());
        assertEquals(-2, minStack.getMin());
    }

    @Test
    void singleElement() {
        MinStack minStack = new MinStack();
        minStack.push(7);

        assertEquals(7, minStack.top());
        assertEquals(7, minStack.getMin());
    }

    @Test
    void minUpdatesWhenNewMinimumPushed() {
        MinStack minStack = new MinStack();
        minStack.push(5);
        minStack.push(3);
        minStack.push(8);
        minStack.push(1);

        assertEquals(1, minStack.getMin());
    }

    @Test
    void minRestoredAfterPoppingCurrentMinimum() {
        // popping the element that IS the current minimum must reveal the
        // min of what's left, not just whatever was pushed most recently
        MinStack minStack = new MinStack();
        minStack.push(5);
        minStack.push(1);

        minStack.pop();
        assertEquals(5, minStack.top());
        assertEquals(5, minStack.getMin());
    }

    @Test
    void duplicateMinimumValuesTrackedCorrectly() {
        MinStack minStack = new MinStack();
        minStack.push(2);
        minStack.push(2);
        minStack.push(2);

        minStack.pop();
        assertEquals(2, minStack.getMin());
        minStack.pop();
        assertEquals(2, minStack.getMin());
    }
}
