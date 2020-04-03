package test;

import calculator.java_files.Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    private Stack stack;

    @Test
    @DisplayName("Checking if True is being returned by isEmpty() method with empty stack")
    void tester_isEmpty_notUsed() {
        /** When **/
        stack = new Stack();

        /** expected **/
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Checking if False is being returned by isEmpty() method with used stack")
    void tester_isEmpty_Used() {
        /** When **/
        stack = new Stack();
        stack.push("10");

        /** expected **/
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("Checking if True is being returned by isEmpty() method with used but cleared stack")
    void tester_isEmpty_UsedAndCleared() throws Exception {
        /** When **/
        stack = new Stack();
        stack.push("10");
        stack.pop();

        /** expected **/
        assertTrue(stack.isEmpty());
    }

    @Test
    @DisplayName("Checking if Exception is being thrown when using pop() method on empty stack")
    void tester_front_notUsed() {
        /** When **/
        stack = new Stack();

        /** expected **/
        assertThrows(Exception.class, () -> {
            stack.top();
        });
    }

    @Test
    @DisplayName("Checking if Exception is being thrown when using pop() method on empty stack")
    void tester_pop_notUsed() {
        /** When **/
        stack = new Stack();

        /** expected **/
        assertThrows(Exception.class, () -> {
            stack.pop();
        });
    }

    @Test
    @DisplayName("Pushing \"i\" string 10 times, which is getting extra  \"i\" every push and is being checked by top() method")
    void tester_StackPusher_StackTopper() throws Exception {
        /** When **/
        stack = new Stack();
        String tested = "i";

        for (int i = 0; i<10; i++){
            stack.push(tested);

            /** expected **/
            assertEquals(tested, stack.top());

            tested += "i";
        }
    }

    @Test
    @DisplayName("Pushing \"i\" string 10 times, which is getting extra  \"i\" every push, then removing 9 elements from the stack and checking if the first String equals \"i\"")
    void tester_StackPopper() throws Exception {
        /** When **/
        stack = new Stack();
        String tested_first = "i";
        String adder = "i";

        for (int i = 0; i<10; i++){
            stack.push(adder);
            adder += "i";
        }

        for (int i = 0; i<9; i++){
            stack.pop();
        }

        /** expected **/
        assertEquals(tested_first, stack.top());
    }

    @Test
    @DisplayName("Throwing excepction and then using the stack")
    void tester_AfterExceptionUsing() throws Exception {
        /** When **/
        stack = new Stack();
        try {stack.pop();} catch (Exception ignored) {}

        stack.push("va1");
        stack.push("va2");
        stack.pop();

        /** expected **/
        assertFalse(stack.isEmpty());
        assertEquals("va1", stack.top());
    }
}
