# Daily Temperatures

- **LeetCode:** [739 — Daily Temperatures](https://leetcode.com/problems/daily-temperatures/) (Medium)
- **Pattern:** Stack
- **Cue:** "next greater element to the right — how many steps until a bigger value shows up"

## Approach

Keep a stack of day-indices whose "days until warmer" answer is still unresolved, from bottom to top in decreasing order of temperature. On each new day, pop every stacked day whose temperature is beaten by today's — each pop resolves that day's answer as the distance to today. Push today's index (it becomes the newest unresolved day).

- **Time:** O(n) — each index is pushed once and popped at most once.
- **Space:** O(n) for the stack.

## Why it works

The stack only ever holds indices in *decreasing* temperature order from bottom to top — every push maintains that invariant, because anything on top that's colder than the incoming day gets popped and resolved first. That invariant is exactly why popping is safe: when day `currentDay` beats `unresolvedDays.peek()`, `currentDay` is also the *first* day since `previousDay` that's warmer (nothing between them made it onto the stack without already being resolved), so `currentDay - previousDay` is the correct answer, not just *an* answer.

## Visualizing it

```
temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
                 0   1   2   3   4   5   6   7

day=0 (73): stack=[]        -> push            stack=[0]
day=1 (74): 74>73 -> pop 0, ans[0]=1-0=1        stack=[1]
day=2 (75): 75>74 -> pop 1, ans[1]=2-1=1        stack=[2]
day=3 (71): 71<75 -> push                       stack=[2,3]
day=4 (69): 69<71 -> push                       stack=[2,3,4]
day=5 (72): 72>69 -> pop 4, ans[4]=5-4=1
            72>71 -> pop 3, ans[3]=5-3=2
            72<75 -> push                       stack=[2,5]
day=6 (76): 76>72 -> pop 5, ans[5]=6-5=1
            76>75 -> pop 2, ans[2]=6-2=4
            push                                stack=[6]
day=7 (73): 73<76 -> push                       stack=[6,7]

end of input: 6 and 7 never find a warmer day -> ans[6]=ans[7]=0 (default)

result = [1, 1, 4, 2, 1, 1, 0, 0]
```

## Code

```java
package stack.dailytemperatures;

import java.util.Stack;

/**
 * LeetCode 739 — Daily Temperatures (Medium)
 * https://leetcode.com/problems/daily-temperatures/
 *
 * Pattern: Stack
 * Cue: "next greater element to the right — how many steps until a bigger value shows up"
 *
 * Approach: Keep a stack of day-indices whose "days until warmer" answer is
 * still unresolved, from bottom to top in decreasing order of temperature.
 * On each new day, pop every stacked day whose temperature is beaten by
 * today's — each pop resolves that day's answer as the distance to today.
 * Push today's index (it becomes the newest unresolved day).
 *
 * Time: O(n) — each index is pushed once and popped at most once.
 * Space: O(n) for the stack.
 */
public class DailyTemperatures {

    public int[] dailyTemperatures(int[] temperatures) {
        int[] daysUntilWarmer = new int[temperatures.length];
        Stack<Integer> unresolvedDays = new Stack<>();

        for (int currentDay = 0; currentDay < temperatures.length; currentDay++) {
            while (!unresolvedDays.isEmpty() && temperatures[currentDay] > temperatures[unresolvedDays.peek()]) {
                int previousDay = unresolvedDays.pop();
                daysUntilWarmer[previousDay] = currentDay - previousDay;
            }
            unresolvedDays.push(currentDay);
        }
        return daysUntilWarmer;
    }
}
```
