package stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class GenerateParenthesis {
    List<String> ans = new ArrayList<>();
    Stack<String> stack = new Stack<>();

    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, n);
        return ans;
    }

    private void backtrack(int open, int closed, int n) {
        if (open == closed && closed == n) {
            Iterator<String> parenthesis = stack.iterator();
            StringBuilder sb = new StringBuilder();
            while (parenthesis.hasNext()) {
                sb.append(parenthesis.next());
            }
            ans.add(sb.toString());
        }

        if (open < n) {
            stack.push("(");
            backtrack(open + 1, closed, n);
            stack.pop();
        }

        if (open > closed) {
            stack.push(")");
            backtrack(open, closed + 1, n);
            stack.pop();
        }
    }
}
