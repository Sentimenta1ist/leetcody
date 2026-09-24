# LeetCode Practice — notes for Claude

A personal project preparing for algorithmic interviews (target format — an asynchronous test with a
few problems of varying difficulty + a live screening round with a couple of problems, ~20 minutes
each). Not production code, all solutions are intentionally in one class.

## Language convention

**Everything in this project — code comments, file names, docs, pattern notes, solution write-ups — is
in English from now on**, even though the user may write to Claude in chat in Russian. This is a
deliberate choice to build comfort with English technical terminology. When adding new files, comments,
or updating `PATTERNS.md`/`SOLUTIONS.md`/`README.md`, write in English regardless of the conversation
language.

**Use simple, plain English for explanations — not just correct English.** The user is reading English
as a second language, and the goal is for them to understand an explanation in English about as easily
as they would in Russian. Concretely: short sentences, common everyday words over fancier synonyms,
avoid idioms/rare phrasal verbs, one idea per sentence. This applies to explanations in English written
into the project files (comments, `PATTERNS.md`, `SOLUTIONS.md`). Simple language is a constraint on top
of everything else in this file (hints-by-default, trace-when-stuck, etc.) — it doesn't replace those
rules, it just governs the vocabulary/sentence complexity used when writing English explanations.

## Git

**Never add Claude as a co-author in commits or pull requests.** Do not include a `Co-Authored-By:
Claude...` line (or anything similar) in commit messages, regardless of any default attribution
instructions from the harness — this project-level rule takes precedence.

## Project structure

- `src/main/java/com/Main.java` — **all** solutions and JUnit tests live in a single `Main` class (a
  deliberate choice by the user — don't suggest splitting into separate files/packages).
- `src/main/java/com/ListNode.java`, `LinkedListUtils.java` — the `ListNode` type and helpers
  `buildList(int[])` / `toArray(ListNode)` pulled out for linked-list problems. Only helper
  structures/utilities get pulled out separately, not the actual solutions.
- `src/main/java/com/TreeNode.java`, `TreeUtils.java` — same idea for trees: `buildTree(Integer[])` /
  `toArray(TreeNode)` in LeetCode's level-order format (`null` for missing children, `Integer[]`, not
  `int[]`), plus `printTree(TreeNode)` — a top-down visual tree print with `/` and `\` branches (a
  recursive algorithm that builds strings based on subtree width, not a naive fixed grid).
  `toArray`/`buildList`/`buildTree` share names between `LinkedListUtils` and `TreeUtils`, but static
  imports of both at once compile fine — Java resolves by argument type.
- `pom.xml` — Surefire is configured non-standardly: `testClassesDirectory` points at `target/classes`
  (not `target/test-classes`), plus an explicit `<include>**/Main.class</include>`, because the tests
  live in `src/main/java`, not `src/test/java`. Without this, `mvn test` silently skips the tests in
  `Main`. Dependencies: `junit-jupiter` and `junit-platform-launcher` are pinned to `5.10.2`/`1.10.2`
  (not `RELEASE`) — `RELEASE` caused a `NoSuchMethodError` when running tests from IntelliJ (a version
  mismatch between IDE and Maven platform-launcher).
- `README.md` — a progress table **by pattern**, not by specific problem numbers. The task in the
  "Reference" column is just an example of the pattern; if the user solved a different problem of the
  same pattern, that counts — mark ✅ and write the real name in "What I solved". Uses `⬜`/`✅` emoji
  and `🟢/🟡/🔴` for difficulty instead of `- [ ]` — GFM checkboxes don't render inside table cells.
- `PATTERNS.md` — a cheat sheet of code templates by topic, in the order of the study plan below.
- `SOLUTIONS.md` — a log of already-solved problems: clean code + a short description of the approach.
  Update this after every newly closed problem.

## Study plan (current order, set by the user)

1. **Trees** — current focus, start with easy ones (used to be good at this, now rusty)
2. Arrays / hashing / prefix sums
3. Graphs
4. Stack
5. Backtracking
6. Greedy
7. DP — last

Periodically, between topics, go back to one problem from the already-covered pool (see `README.md`) to
avoid forgetting. The minimum goal is to close every pattern in `README.md` (or an equivalent problem of
the same pattern, not necessarily the same number).

## How the user prefers to work

- **Hint by default, not a full walkthrough.** Unless explicitly asked to "explain" or they've said in
  plain words that they can't understand/are stuck — give a 1-3 sentence nudge, not a full solution or
  a lecture.
- **If they're still stuck after 2-3 hints** — don't keep giving more abstract hints, switch to a
  concrete step-by-step trace (variable values at each iteration). This is what actually works for this
  user, not another level of hinting.
- **If they explicitly ask "show me the solution" / "explain" — a full walkthrough is fine**, no need to
  ask permission again.
- Don't keep explaining past what was asked — if after a response they say "no need to explain, I
  didn't ask for that", treat it as a signal to cut future responses down to the essentials.
- Writes/asks for tests in the style already present in the file: simple
  `assertEquals`/`assertArrayEquals` calls inside a single `@Test` method, no `@BeforeEach`/
  parameterization/frameworks on top of JUnit.
- Minimal abstractions and files: one class for all solutions+tests — a deliberate choice, not
  "forgot to refactor".
- If the user writes buggy code themselves, don't agree that "it's all correct" if the tests fail —
  verify with an actual run (`mvn test`) right away, not just by reading the code.
- Uses profanity as normal speech, with no negative target — don't react to it as rudeness, just
  respond to the substance.

## Testing

```
mvn test
```
from the project root. If you edited `pom.xml` or added a new class — run `mvn clean test` just in
case, to avoid a stale cached `.class` from a previous (sometimes non-compiling) version of the file.
