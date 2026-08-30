# Stack

Cue: LIFO order matters — the most recently seen unresolved item is exactly the one the next step needs (a matching bracket, a pending operand, a taller bar, a slower car ahead).

## Pattern skeleton

```
stack = []
for item in items:
  while stack not empty and top violates some condition against item:
    resolve(stack.pop(), item)
  stack.push(item)
resolve whatever's left on the stack against the end of input
```

A monotonic stack (heights, temperatures, arrival times) keeps its contents in strictly increasing or decreasing order; popping happens exactly when the invariant would otherwise break, and each pop resolves something about the popped element using the item that just arrived. Not every stack problem is monotonic, though — Valid Parentheses and Evaluate RPN use a stack simply because the problem's own structure is LIFO (nesting, postfix operand order).

| Problem | Difficulty | Cue | Solution | Last Solved |
|---------|-----------|-----|----------|-------------|
| [Valid Parentheses](https://leetcode.com/problems/valid-parentheses/) | Easy | matching brackets — a closer must match the most recently opened bracket | [ValidParentheses.md](validparentheses/ValidParentheses.md) | 2026-08-30 |
| [Min Stack](https://leetcode.com/problems/min-stack/) | Medium | stack that also supports O(1) getMin alongside push/pop | [MinStack.md](minstack/MinStack.md) | 2026-08-30 |
| [Evaluate Reverse Polish Notation](https://leetcode.com/problems/evaluate-reverse-polish-notation/) | Medium | evaluate postfix expression — an operator always applies to the two most recently seen operands | [ReversePolishNotation.md](reversepolishnotation/ReversePolishNotation.md) | 2026-08-30 |
| [Generate Parentheses](https://leetcode.com/problems/generate-parentheses/) | Medium | generate every well-formed combination — backtrack with open/close counts as guardrails | [GenerateParenthesis.md](generateparenthesis/GenerateParenthesis.md) | 2026-08-30 |
| [Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) | Medium | next greater element to the right — how many steps until a bigger value shows up | [DailyTemperatures.md](dailytemperatures/DailyTemperatures.md) | 2026-08-30 |
| [Car Fleet](https://leetcode.com/problems/car-fleet/) | Medium | cars merge into a fleet when a slower car ahead blocks faster cars behind | [CarFleet.md](carfleet/CarFleet.md) | 2026-08-30 |
| [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram/) | Hard | largest rectangle under a histogram — width is limited by the shortest bar in the span | [LargestRectangle.md](largestrectangle/LargestRectangle.md) | 2026-08-30 |
