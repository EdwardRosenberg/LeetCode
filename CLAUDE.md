# CLAUDE.md — LeetCode Prep Repo

## Purpose

This repo is a **browsable review guide** for interview prep, organized by algorithm pattern. It is the byproduct of a timed practice routine — the owner solves problems under time pressure elsewhere, then hands the solution here for curation. Optimize everything for **review-ability months later**, not for leetcode submission golf.

## The standard task

The usual request is: `add today's solution: <problem name>` plus pasted or committed solution code. When you get it, do all of the following:

1. Place a cleaned reference solution in the correct pattern package (see structure below).
2. Write a JUnit test class covering: the problem's given examples, at least two edge cases (empty/single-element/boundary values), and one larger input.
3. Write the per-solution `.md` doc page next to the solution, and add its row to that pattern's `README.md` index. If the pattern package is new, add it to the root `README.md` too.
4. Run `./gradlew test` and confirm green before finishing.

Keep the whole operation tight. This should take minutes, not become a refactoring session.

## Repo structure

```
src/main/java/<pattern>/<ProblemName>.java       ← solution
src/main/java/<pattern>/<ProblemName>.md         ← per-solution doc page
src/main/java/<pattern>/README.md                ← pattern index (see below)
src/test/java/<pattern>/<ProblemName>Test.java
README.md                                        ← root index, links to each pattern's README.md
```

Pattern packages (create only as needed — names match what's actually on disk):
`arraysandhashing`, `twopointers`, `slidingwindow`, `stack`, `binarysearch`, `linkedlist`, `trees`, `heap`, `graphs`, `intervals`, `dp`

The repo also has some pre-pattern legacy code (`twosum/`, `movezeroes/`, `containsduplicate/`, `reverseinteger/`, a few top-level classes) that predates this structure. Leave it alone — don't fold it into the pattern packages or indexes unless explicitly asked.

Class names: PascalCase problem name, no leetcode number prefix (`TwoSum`, not `Problem1TwoSum`). The leetcode number goes in the file header instead.

## Solution file conventions

One file per problem — the cleaned reference version. Do not preserve the raw timed attempt as a separate file; clean in place but **do not change the owner's algorithmic approach**. If his approach is suboptimal, keep it working, then add a short `// Alternative:` note in the header describing the better approach in 1–3 lines. Never silently swap his solution for the editorial one — recognizing his own reasoning on review is the point.

Every solution file starts with this header:

```java
/**
 * LeetCode 3 — Longest Substring Without Repeating Characters (Medium)
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 *
 * Pattern: Sliding Window
 * Cue: "longest/shortest substring or subarray satisfying a condition"
 *
 * Approach: Expand right pointer; on duplicate, shrink from left until valid.
 * Window contents tracked in a HashSet.
 *
 * Time: O(n) — each element enters and leaves the window at most once.
 * Space: O(min(n, alphabet))
 */
```

The **Cue** line is mandatory and is the most important line in the file: it's the phrase that should trigger this pattern when reading a new problem statement. Write it as a recognition trigger, not a description.

**Optimize for a tired reviewer with 20 minutes**, months removed from having written this. They should not have to re-derive anything the code could just tell them.

- **Variable names: descriptive over generic.** No single-letter or placeholder names (`s`, `chars`, `arr`, `tmp`, `n`) for anything that carries meaning — name it after its role (`word`, `seenChars`, `windowStart`). Loop counters with no semantic meaning of their own (`i` in a plain `for` over indices) are fine as-is. If a generic name is genuinely clearer than the alternative in context, keep it — the goal is low cognitive load, not a no-short-names rule.
- **Inline comments: explain *intent* at decision points that aren't obvious from the code** — why we shrink here, why the dummy node exists, why this edge case needs special handling. Skip commenting anything the descriptive names or method signature already say. No line-by-line narration, no commented-out code.

## Per-solution MD pages

Every solution gets a `<ProblemName>.md` file next to its `.java` file, in the same package. Content:

1. The same header info as the `.java` doc comment — title, LeetCode link, difficulty, pattern, cue, approach, complexity — as prose/bullets, not a code comment.
2. **An ASCII diagram walking through the mechanic**, when the pattern involves something spatial that's faster to grok visually than in prose — pointer/window movement, stack pushes/pops, tree/list traversal, etc. Trace a short concrete example (not the whole input), show the key transition (e.g. the collision that triggers a shrink), and reuse the code's own variable names in the diagram so the two reinforce each other. Skip it for patterns where a diagram wouldn't add anything over the prose approach (e.g. a plain hash lookup).
3. **`Watch out:` section (optional)** — a one-line note on the failure mode from your timed attempt: "forgot to shrink before adding" or "off-by-one on the window boundary." This is the most valuable months later, because it's *your* blind spot, not generic knowledge. Only include if there's an actual gotcha; skip it for the straightforward cases.
4. The full solution code embedded below it in a fenced ```java block, kept in sync with the `.java` file.

This makes the solution readable on GitHub without opening the IDE, and is the link target from the pattern index.

## Test conventions

Same low-cognitive-load bar as the solution files: a tired reviewer should be able to tell what broke from the test report alone, without reading the test body.

- JUnit 5 (match whatever the repo's Gradle build already uses if different).
- **One scenario per `@Test` method** — no mega-methods bundling many asserts behind one generic name. A failure should point straight at the scenario that broke.
- Test class name: `<ProblemName>Test`, methods named for the scenario: `emptyInput()`, `singleElement()`, `duplicatesAtEnds()`, `exampleOne()`.
- **Add a short comment on any case whose *why* isn't obvious from the method name and input alone** — e.g. why `"dvdf"` is a meaningful case for a substring problem (the repeat isn't at the window's start, so `left` has to jump past it). Skip commenting when the name already says it.
- Plain asserts, no mocking frameworks — these are pure functions.
- Where the problem involves linked lists or trees, add small private builders in the test class (`buildList(int...)`, etc.) rather than pulling in a utility dependency. Reuse an existing builder if one already exists in the package.

## Pattern index pages

Each pattern package has its own `README.md` (e.g. `src/main/java/slidingwindow/README.md`) holding one table of that pattern's problems, in course order. Row format:

| Problem | Difficulty | Cue | Solution | Last Solved |
|---------|-----------|-----|----------|-------------|
| [Two Sum](https://leetcode.com/problems/two-sum/) | Easy | complement lookup → HashMap value→index | [TwoSum.md](TwoSum.md) | 2026-07-11 |

The Solution column links to the `.md` doc page (relative path, same folder), not directly to the `.java` file.

**Last Solved** is the date you added/updated that solution (YYYY-MM-DD). Use it to spot problems you haven't touched in 4+ weeks — those are your spaced-repetition candidates.

## Root README index

The root `README.md` lists every pattern package with a one-line description of its recognition cue category, linking to that pattern's `README.md`. It does not list individual problems — that's the pattern index's job.

When adding a solution: add its row to the pattern's index. If it's a brand-new pattern package, add a line for it to the root README too. Keep cues to one line — the indexes are for pre-interview skimming.

## Cue drill (CUES.md)

`CUES.md` is a self-test tool: it lists every cue with the problem hidden in a `<details>` fold, so you can practice pattern recognition in a 5–10-minute sprint. Read the cue, guess the pattern, click to check.

When you add a new solution to a pattern index, also add its cue to the corresponding section of `CUES.md` in the same `<details>` format. The drill stays in sync with the problem count, not with every edit to the solutions themselves.

## Weekend redo support

On request `redo check: <problem name>`: run only that problem's test class against the owner's fresh attempt (he'll have replaced or added the method), report pass/fail with failing cases, and **do not fix his code** — the redo is practice, failures are the signal.

## Variant policy

The repo contains multiple solutions to the same problem (e.g., `LongestConsecutiveSequence` with four variants; `TopKFrequentElements` + `TopKFrequentElements2`). These predate the curation format.

**Indexing strategy:** When you add a problem to a pattern index, link **only the best reference version** — the one you'd pick first under time pressure, or the one that teaches the pattern cleanest. Add a one-line note in that solution's `.md` header listing the other variants on disk:

```
Variants: [LongestConsecutiveSequenceRecursion](LongestConsecutiveSequenceRecursion.java) (recursive), [LongestConsecutiveSequenceSorted](LongestConsecutiveSequenceSorted.java) (sorted approach)
```

This keeps the skim surface small (the pattern index stays scannable) while preserving the history on disk for deep dives.

## Guardrails

- Never bulk-generate solutions for problems he hasn't attempted. The repo tracks *his* practice; unsolved problems don't belong in it.
- No dependency additions without asking.
- No restructuring or renaming sweeps unless explicitly requested.
- If a request would take more than ~10 minutes of work, say so and propose the minimal version instead.
