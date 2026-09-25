# Pattern Cheat Sheet

Organized in the order of the study plan (see `CLAUDE.md`). These are **form** templates you should be
able to reproduce without thinking — not solutions to specific problems.

---

## 1. Trees

### DFS (recursive)
```java
void dfs(TreeNode node) {
    if (node == null) return;          // base case
    dfs(node.left);
    // pre-order: processing HERE, before going into children
    dfs(node.right);
    // post-order: processing HERE, after returning from both children
}
```
- **Pre-order** — when the parent's processing doesn't depend on the children's result (copying, printing).
- **In-order** — gives sorted order for a BST.
- **Post-order** — when the node's result depends on its children's results (height, diameter, subtree sum).

### BFS (level by level)
```java
Queue<TreeNode> queue = new LinkedList<>();
queue.add(root);
while (!queue.isEmpty()) {
    int levelSize = queue.size();      // snapshot BEFORE the loop — otherwise levels can't be separated
    for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        // process node
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
```

### Common sub-patterns
- **Height/diameter** — post-order DFS, the diameter is `max(left, right)` returned up separately,
  while "left + right" is tracked in a global variable (not in the return value).
- **LCA (lowest common ancestor)** — "information bubbles up through the return value": if a node found
  both target values below it (one in each subtree) — it is the answer.
- **Validate BST** — DFS passing `(min, max)` bounds down through recursion parameters.
- **Path Sum variants** — DFS accumulating a sum along the path (either pass the remainder down, or
  accumulate the running sum).
- **Invert a tree in place** — pre-order DFS: swap `.left`/`.right` on the current node BEFORE
  recursing, then recursively do the same for both (already swapped) subtrees. The inner calls' return
  value can be ignored — it's a pure mutation.
- **Symmetry / mirror pairs ("Mirror", Symmetric Tree, working level-by-level in a perfect tree)** —
  instead of one `dfs(node)`, recursion walks **two** nodes at once: `dfs(left, right)`, starting from
  `dfs(root.left, root.right)`. The recursive calls go in **crossed** pairs —
  `dfs(left.right, right.left)` and `dfs(left.left, right.right)` — these are the only two combinations
  that give mirror-symmetric positions at the next level. If you also need to know the parity of the
  level/depth, pass a `boolean` flag and flip it (`!flag`) at every step down — parity always strictly
  alternates, no explicit counter needed.
- **N-ary tree DFS** — same recursive shape as binary tree DFS, just loop over `node.children` instead of
  explicit `left`/`right`. Combine the children's results with a **local** accumulator re-created on
  every call (list concatenation via `addAll`, or `Math.max` in a loop) — never a shared/global field,
  the same way you'd never need one for the binary version.
- **N-ary tree BFS** — same idea as binary BFS, just replace the two `if (node.left/right != null)`
  checks with a loop over `node.children`.
- **Structural comparison (Same Tree / Subtree)** — a helper that compares two trees node-by-node
  (`isSameTree`-style) doubles as the building block for "is X a subtree of Y" — just call that helper
  at every node of the bigger tree instead of only at the root.

---

## 2. Arrays / Hashing

### Two Sum (complement in a hashmap, single pass)
```java
Map<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (seen.containsKey(complement)) return new int[]{seen.get(complement), i};
    seen.put(nums[i], i);
}
```

### Prefix sum + hashmap (subarray with sum K)
```java
Map<Integer, Integer> prefixCount = new HashMap<>();
prefixCount.put(0, 1);
int sum = 0, count = 0;
for (int num : nums) {
    sum += num;
    count += prefixCount.getOrDefault(sum - k, 0);
    prefixCount.merge(sum, 1, Integer::sum);
}
```

### Sliding window (variable size)
```java
int left = 0;
for (int right = 0; right < nums.length; right++) {
    // add nums[right] to the window
    while (/* window invalid */) {
        // remove nums[left] from the window
        left++;
    }
    // update the answer using the current window [left, right]
}
```

### Two pointers (converging)
```java
int left = 0, right = nums.length - 1;
while (left < right) {
    // compare/compute, move left++ or right--
}
```

### Bijective mapping (isomorphic strings)
When a mapping between two sequences must hold **both ways** (no two source elements share a target),
one hashmap isn't enough — track both directions:
```java
Map<Character, Character> aToB = new HashMap<>();
Map<Character, Character> bToA = new HashMap<>();
for (int i = 0; i < a.length(); i++) {
    char x = a.charAt(i), y = b.charAt(i);
    if (!aToB.containsKey(x) && !bToA.containsKey(y)) {
        aToB.put(x, y);
        bToA.put(y, x);
    } else if (!aToB.containsKey(x) || !bToA.containsKey(y)
            || aToB.get(x) != y || bToA.get(y) != x) {
        return false;
    }
}
```
A faster (same `O(n)`, smaller constant) alternative when the alphabet is small: replace both maps with
`int[]` arrays indexed by character code, storing "last seen position" — no hashing, no boxing.

---

## 3. Graphs

### DFS with visited
```java
void dfs(int node, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
    if (visited.contains(node)) return;
    visited.add(node);
    for (int neighbor : graph.get(node)) dfs(neighbor, visited, graph);
}
```

### BFS with visited
```java
Queue<Integer> queue = new LinkedList<>();
Set<Integer> visited = new HashSet<>();
queue.add(start);
visited.add(start);                     // mark WHEN ADDING to the queue, not when polling
while (!queue.isEmpty()) {
    int node = queue.poll();
    for (int neighbor : graph.get(node)) {
        if (!visited.contains(neighbor)) {
            visited.add(neighbor);
            queue.add(neighbor);
        }
    }
}
```

### Union-Find
```java
int[] parent;
int find(int x) {
    if (parent[x] != x) parent[x] = find(parent[x]);  // path compression
    return parent[x];
}
void union(int a, int b) {
    int rootA = find(a), rootB = find(b);
    if (rootA != rootB) parent[rootA] = rootB;
}
```

### Topological sort (Kahn's, BFS-based)
```java
int[] inDegree = new int[n];
// ... fill inDegree from the graph's edges
Queue<Integer> queue = new LinkedList<>();
for (int i = 0; i < n; i++) if (inDegree[i] == 0) queue.add(i);
List<Integer> order = new ArrayList<>();
while (!queue.isEmpty()) {
    int node = queue.poll();
    order.add(node);
    for (int neighbor : graph.get(node)) {
        if (--inDegree[neighbor] == 0) queue.add(neighbor);
    }
}
// order.size() < n  =>  the graph has a cycle
```

### Multi-source BFS
Same as regular BFS, but **all** starting nodes are added to the queue up front (not just one) — e.g.
all "rotting oranges" at once, not one at a time — this gives the shortest distance from the nearest
source.

---

## 4. Stack

### Basic stack (matching/undo)
```java
Deque<Character> stack = new ArrayDeque<>();
for (char c : s.toCharArray()) {
    if (isOpening(c)) {
        stack.push(c);
    } else if (stack.isEmpty() || !matches(stack.pop(), c)) {
        return false;
    }
}
return stack.isEmpty();
```

### Monotonic stack (next greater element)
```java
int[] result = new int[n];
Deque<Integer> stack = new ArrayDeque<>();   // stores INDICES
for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
        result[stack.pop()] = nums[i];
    }
    stack.push(i);
}
// whatever's left in the stack after the loop — elements with no "next greater"
```
Signal for this pattern: "next greater/smaller element", "how many days to wait for the price to rise",
"rectangle area in a histogram".

---

## 5. Backtracking

```java
void backtrack(List<Integer> current, /* rest of the state */) {
    if (/* termination condition */) {
        result.add(new ArrayList<>(current));   // a COPY, not current itself
        return;
    }
    for (int choice : /* choices available at this step */) {
        current.add(choice);            // choose
        backtrack(current, ...);        // explore
        current.remove(current.size() - 1);  // undo — the most commonly forgotten line
    }
}
```
The single idea: try every choice, and after each attempt undo the state before trying the next one.
Common shapes: subsets (take/don't take), permutations (track used elements separately),
combination sum (an element can be reused — don't advance the starting index).

---

## 6. Greedy

Mental model: **first sort by some criterion, then at each step take the locally optimal choice,
never revisiting it.**

Signs that greedy might work here (instead of DP):
- A problem about intervals/scheduling ("max non-overlapping meetings") — sort by interval end.
- "Minimize/maximize something where the choice for one element doesn't affect which choices were
  available for others" — if this is false (choices are interdependent in a complex way) — it's
  probably DP, not greedy.

How to sanity-check a greedy hypothesis in an interview: try to construct a counterexample by hand on a
small input (3-4 elements). If the greedy strategy breaks — the problem isn't greedy, it's almost
certainly DP.

---

## 7. DP

### Universal recipe — 3 questions before writing code
1. What does `dp[i]` (or `dp[i][j]`) mean? — state it **in words**, not as a formula.
2. How is `dp[i]` expressed in terms of smaller subproblems? (the transition)
3. What is the base case?

### 6 shapes
| Shape | Signal in the problem statement | Reference |
|---|---|---|
| Linear 1D | "at each step decide: include the element or not", a single array | Climbing Stairs, House Robber |
| Grid/2D | "path through a grid, right/down" | Unique Paths, Minimum Path Sum |
| Knapsack | "a set of items, a constraint on sum/weight" | Coin Change, Partition Equal Subset Sum |
| Two-sequence | **two** strings/arrays as input | Longest Common Subsequence, Edit Distance |
| Interval DP | "split the range into parts", table indexed by range LENGTH, not by index | Longest Palindromic Substring, Burst Balloons |
| State machine | several mutually exclusive states per step | Best Time to Buy/Sell Stock (cooldown/fee) |

Order of operations on a new DP problem: first figure out which of the 6 shapes it fits, then write the
recurrence relation for that shape.
