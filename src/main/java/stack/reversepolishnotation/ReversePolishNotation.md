# Evaluate Reverse Polish Notation

- **LeetCode:** [150 — Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) (Medium)
- **Pattern:** Stack
- **Cue:** "evaluate postfix expression — an operator always applies to the two most recently seen operands"

## Approach

Scan tokens left to right. A number gets pushed. An operator pops the top two operands (the second-popped one came first in the original expression) and pushes the result back. The final value left on the stack is the answer.

- **Time:** O(n) — each token is processed once.
- **Space:** O(n) for the stack.

## Why it works

Postfix notation is defined so that by the time an operator token appears, both of its operands have already been fully evaluated and are sitting on top of the stack — nothing else could legally be between them, because every operator immediately follows its two operands (which may themselves be the results of earlier operator tokens). Popping exactly two values and pushing one net result per operator shrinks the stack by one every time, so a well-formed expression always ends with exactly one value left: the answer.

## Visualizing it

```
tokens = ["2", "1", "+", "3", "*"]

"2"  push 2                    stack: [2]
"1"  push 1                    stack: [2, 1]
"+"  pop 1, pop 2 -> 2+1=3     stack: [3]
"3"  push 3                    stack: [3, 3]
"*"  pop 3, pop 3 -> 3*3=9     stack: [9]

result = 9
```

## Watch out

For non-commutative operators (`-`, `/`), operand order matters: `secondOperand` is popped first (it's the top of the stack, i.e. the operand that appeared *second* in the expression), so the computation must be `firstOperand - secondOperand`, not the reverse — swapping them silently negates every subtraction and inverts every division.

## Code

```java
package stack.reversepolishnotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * LeetCode 150 — Evaluate Reverse Polish Notation (Medium)
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/
 *
 * Pattern: Stack
 * Cue: "evaluate postfix expression — an operator always applies to the two most recently seen operands"
 *
 * Approach: Scan tokens left to right. A number gets pushed. An operator
 * pops the top two operands (the second-popped one came first in the
 * original expression) and pushes the result back. The final value left on
 * the stack is the answer.
 *
 * Time: O(n) — each token is processed once.
 * Space: O(n) for the stack.
 */
public class ReversePolishNotation {

    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        List<String> operators = initOperators();

        for (String token : tokens) {
            if (operators.contains(token)) {
                int secondOperand = stack.pop();
                int firstOperand = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(firstOperand + secondOperand);
                        break;
                    case "-":
                        stack.push(firstOperand - secondOperand);
                        break;
                    case "*":
                        stack.push(firstOperand * secondOperand);
                        break;
                    case "/":
                        stack.push(firstOperand / secondOperand);
                        break;
                }
            } else {
                stack.push(Integer.valueOf(token));
            }
        }

        return stack.peek();
    }

    private List<String> initOperators() {
        List<String> operators = new ArrayList<>(4);

        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        return operators;
    }
}
```
