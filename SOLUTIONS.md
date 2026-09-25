# Log of Solved Problems

Clean code + a short description of the approach. Update this after every newly closed problem (you can
ask Claude to add an entry once the tests turn green).

---

## Two Sum (LeetCode 1) — Easy

**Pattern:** arrays/hashing, complement in a hashmap in a single pass.
**Approach:** for each element, look up `target - nums[i]` in the hashmap of already-seen elements.
If found — that's the pair. O(n) time, O(n) space.

```java
public int[] twoSum2(int[] nums, int target) {
    Map<Integer, Integer> seen = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        Integer complementIndex = seen.get(complement);
        if (complementIndex != null) {
            return new int[] {complementIndex, i};
        }
        seen.put(nums[i], i);
    }
    return null;
}
```

---

## Best Time to Buy and Sell Stock (LeetCode 121) — Easy

**Pattern:** arrays, running min + Kadane's algorithm in disguise.
**Approach:** keep the minimum price seen so far and the maximum profit `price - minPrice` at each
step. A single transaction, O(n)/O(1).

```java
public int maxProfit(int[] prices) {
    int maxProfit = 0;
    int minIndex = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] - prices[minIndex] < 0) {
            minIndex = i;
        }
        if (prices[i] - prices[minIndex] > maxProfit) {
            maxProfit = prices[i] - prices[minIndex];
        }
    }
    return maxProfit;
}
```

---

## Best Time to Buy and Sell Stock II (LeetCode 122) — Medium

**Pattern:** greedy.
**Approach:** unlimited transactions ⇒ just sum up every positive day-to-day price increase
(equivalent to "buy at every local minimum, sell at every local maximum").

```java
public int maxProfit2(int[] prices) {
    int sum = 0;
    for (int i = 1; i < prices.length; i++) {
        if (prices[i] > prices[i - 1]) {
            sum += prices[i] - prices[i - 1];
        }
    }
    return sum;
}
```

---

## Reverse Linked List (LeetCode 206) — Easy

**Pattern:** linked lists, in-place reversal (3 pointers).
**Approach:** `prev` starts at `null`, `curr` starts at `head` (not `head.next`!). At each step, save
`curr.next`, flip the arrow, move both pointers forward. Classic bug — starting `curr` at `head.next`
and `prev` at `head`: then `head.next` never gets nulled out and you get a cycle.

```java
public ListNode reverseList(ListNode head) {
    ListNode curr = head;
    ListNode prev = null;
    while (curr != null) {
        ListNode next = curr.next;
        curr.next = prev;
        prev = curr;
        curr = next;
    }
    return prev;
}
```

---

## Reverse Linked List II (LeetCode 92) — Medium

**Pattern:** linked lists, the same in-place reversal but on a bounded segment + stitching the edges
back together.
**Approach:** a dummy node for the `left == 1` case; walk `prev` to the node before `left`; reverse
exactly `right - left + 1` nodes with the same algorithm as `reverseList`; at the end, manually connect
the old segment tail (now the new end) to whatever comes after the segment, and the old `prev` to the
new head of the segment.

```java
public ListNode reverseBetween(ListNode head, int left, int right) {
    ListNode dummy = new ListNode(0, head);
    ListNode prev = dummy;
    for (int i = 0; i < left - 1; i++) {
        prev = prev.next;
    }

    ListNode curr = prev.next;
    ListNode prevInSegment = null;
    for (int i = 0; i < right - left + 1; i++) {
        ListNode next = curr.next;
        curr.next = prevInSegment;
        prevInSegment = curr;
        curr = next;
    }

    prev.next.next = curr;
    prev.next = prevInSegment;

    return dummy.next;
}
```

---

## Find the First Letter to Appear Twice (LeetCode 2351) — Easy

**Pattern:** arrays/hashing, a hashset for "already seen".
**Approach:** walk the string, add characters to a `Set`; the moment you see a character already in
the set — that's the first repeated one.

```java
public char repeatedCharacter(String s) {
    Set<Character> seen = new HashSet<>();
    seen.add(s.charAt(0));
    for (int i = 1; i < s.length(); i++) {
        if (seen.contains(s.charAt(i))) {
            return s.charAt(i);
        } else {
            seen.add(s.charAt(i));
        }
    }
    return 'a';
}
```

---

## Invert Binary Tree (LeetCode 226) — Easy

**Pattern:** trees, pre-order DFS with in-place mutation.
**Approach:** at every node, swap `.left`/`.right`, then recursively do the same for both (already
swapped) subtrees. The inner recursive calls' return value can be ignored — the method mutates and
returns the same node that's already reachable through `root.left`/`root.right`.

```java
public TreeNode invertTree(TreeNode root) {
    if (root == null) {
        return null;
    }
    TreeNode node = root.left;
    root.left = root.right;
    root.right = node;
    invertTree(root.right);
    invertTree(root.left);
    return root;
}
```

---

## Reverse Odd Levels of Binary Tree (LeetCode 2415) — Medium

**Pattern:** trees, level-order BFS (collect a level's values + reverse) — version 1.
**Approach:** regular level-order BFS with a `levelSize` snapshot; at every level, collect **that
level's** nodes (the list is recreated every `while` iteration, otherwise levels bleed into each other)
into an `ArrayList`, and if the level is odd — reverse their values with a two-pointer swap (`left`/
`right` converging). The tree is guaranteed to be **perfect** (per the problem statement), so there's no
need to worry about incomplete levels.

```java
public TreeNode reverseOddLevels(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int level = 0;
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        ArrayList<TreeNode> nodes = new ArrayList<>(); // recreated EVERY level
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            nodes.add(node);
            if (node.left != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        if (level % 2 == 1) {
            int left = 0, right = nodes.size() - 1;
            while (left < right) {
                int temp = nodes.get(left).val;
                nodes.get(left).val = nodes.get(right).val;
                nodes.get(right).val = temp;
                left++;
                right--;
            }
        }
        level++;
    }
    return root;
}
```

**Version 2 — DFS with a pair of "mirror" pointers** (shorter, no explicit level collection): the
recursion walks **two** nodes at once — starting with `root.left` and `root.right` (the two ends of
level 1). The `swap` flag says whether their values need swapping (starts at `true`, since level 1 is
odd), and flips at every step down, because level parity strictly alternates. The recursive calls go in
**crossed** pairs — `(left.right, right.left)` and `(left.left, right.right)` — these are the only two
combinations that give mirror-symmetric positions at the next level (the tree's structure never
changes, only the values).

```java
public TreeNode reverseOddLevelsDFS(TreeNode root) {
    reverseOddLevelsDFStraverse(root.left, root.right, true);
    return root;
}

public void reverseOddLevelsDFStraverse(TreeNode left, TreeNode right, boolean swap) {
    if (left == null) {
        return;
    }
    if (swap) {
        int tmp = left.val;
        left.val = right.val;
        right.val = tmp;
    }
    reverseOddLevelsDFStraverse(left.right, right.left, !swap);
    reverseOddLevelsDFStraverse(left.left, right.right, !swap);
}
```

---

## Binary & N-ary Tree Preorder/Postorder Traversal (LeetCode 144 / 145 / 589 / 590) — Easy

**Pattern:** trees, DFS that returns a list built purely from recursive results — no shared accumulator.
**Approach:** each call builds and returns its own `List<Integer>`; the parent just `addAll`s what its
children returned, combined with its own `val` — `val` first for pre-order, `val` last for post-order.
The binary and n-ary versions are overloads of the same method name (resolved by argument type): the
binary one recurses into `left`/`right`, the n-ary one loops over `children`.

```java
public List<Integer> preorderTraversal(TreeNode root) {
    if (root == null) {
        return new ArrayList<>();
    }
    List<Integer> res = new ArrayList<>();
    res.add(root.val);
    res.addAll(preorderTraversal(root.left));
    res.addAll(preorderTraversal(root.right));
    return res;
}

public List<Integer> preorderTraversal(Node root) {
    if (root == null) {
        return new ArrayList<>();
    }
    List<Integer> res = new ArrayList<>();
    res.add(root.val);
    for (Node child : root.children) {
        res.addAll(preorderTraversal(child));
    }
    return res;
}

public List<Integer> postorderTraversal(TreeNode root) {
    if (root == null) {
        return new ArrayList<>();
    }
    List<Integer> res = new ArrayList<>();
    res.addAll(postorderTraversal(root.left));
    res.addAll(postorderTraversal(root.right));
    res.add(root.val);
    return res;
}

public List<Integer> postorderTraversal(Node root) {
    if (root == null) {
        return new ArrayList<>();
    }
    List<Integer> res = new ArrayList<>();
    for (Node child : root.children) {
        res.addAll(postorderTraversal(child));
    }
    res.add(root.val);
    return res;
}
```

---

## Maximum Depth of Binary & N-ary Tree (LeetCode 104 / 559) — Easy

**Pattern:** trees, post-order DFS height — the answer is computed on the way back up the recursion, not
on the way down.
**Approach:** for a binary node, `1 + max(depth(left), depth(right))`. For an n-ary node, loop over all
children and keep the biggest depth seen so far in a **local** `max` variable (a fresh one on every call,
not a shared field), then add 1 at the end.

```java
public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}

public int maxDepth(Node root) {
    if (root == null) {
        return 0;
    }
    int max = 0;
    for (int i = 0; i < root.children.size(); i++) {
        max = Math.max(max, maxDepth(root.children.get(i)));
    }
    return max + 1;
}
```

---

## Same Tree & Subtree of Another Tree (LeetCode 100 / 572) — Easy

**Pattern:** trees, DFS structural comparison — the second problem directly reuses the first.
**Approach:** `isSameTree` — three base cases (both null → true, only one null → false, values differ →
false), then recurse into both child pairs. `isSubtree` walks every node of `root` and asks "is the
subtree starting here the same tree as `subRoot`?" — using `isSameTree` as the check at each node.

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
    if (p == null && q == null) {
        return true;
    }
    if (p == null || q == null) {
        return false;
    }
    if (p.val != q.val) {
        return false;
    }
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}

public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null || subRoot == null) {
        return false;
    }
    return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
}
```
A more efficient alternative (`O(n + m)` instead of `O(n · m)`): serialize both trees to strings with a
delimiter before every value and a marker for `null` (so values like `12` and `2` can't accidentally
look like substrings of each other), then check whether `subRoot`'s string is a substring of `root`'s
string.

---

## Binary & N-ary Tree Level Order Traversal (LeetCode 102 / 429) — Medium

**Pattern:** trees, BFS with a `levelSize` snapshot — the same shape as `reverseOddLevels`, just
collecting values into `List<List<Integer>>` instead of mutating in place.
**Approach:** standard level-order BFS; `levelSize` is read **before** the inner loop so each `while`
iteration corresponds to exactly one tree level. The binary and n-ary versions only differ in how a
node's children get added to the queue (`left`/`right` vs looping over `children`).

```java
public List<List<Integer>> levelOrder(TreeNode root) {
    if (root == null) {
        return Collections.emptyList();
    }
    List<List<Integer>> res = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> levelList = new ArrayList<>();
        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            levelList.add(node.val);
        }
        res.add(levelList);
    }
    return res;
}

public List<List<Integer>> levelOrder(Node root) {
    if (root == null) {
        return new ArrayList<>();
    }
    List<List<Integer>> res = new ArrayList<>();
    Queue<Node> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> levelList = new ArrayList<>();
        for (int i = 0; i < levelSize; i++) {
            Node node = queue.poll();
            for (int j = 0; j < node.children.size(); j++) {
                queue.add(node.children.get(j));
            }
            levelList.add(node.val);
        }
        res.add(levelList);
    }
    return res;
}
```

---

## Isomorphic Strings (LeetCode 205) — Easy

**Pattern:** arrays/hashing, bijective character mapping (a mapping that must be consistent in **both**
directions).
**Approach:** two hashmaps — `s[i] -> t[i]` and `t[i] -> s[i]`. A new pair of characters is only safe to
map if **neither** has been mapped to anything yet; otherwise both existing mappings must already agree
with the current pair, or it's not isomorphic. Tracking only one direction is the classic bug here: it
misses two *different* source characters trying to map to the *same* target character (e.g. `"badc"` vs
`"baba"` — false, but a one-way map alone would say true).

```java
public boolean isIsomorphic(String s, String t) {
    HashMap<Character, Character> map1 = new HashMap<>();
    HashMap<Character, Character> map2 = new HashMap<>();

    char[] s1 = s.toCharArray();
    char[] t1 = t.toCharArray();
    for (int i = 0; i < s1.length; i++) {
        if (!map1.containsKey(s1[i]) && !map2.containsKey(t1[i])) {
            map1.put(s1[i], t1[i]);
            map2.put(t1[i], s1[i]);
        } else {
            if (!map1.containsKey(s1[i]) || !map2.containsKey(t1[i])
                    || map1.get(s1[i]) != t1[i] || map2.get(t1[i]) != s1[i]) {
                return false;
            }
        }
    }

    return true;
}
```
A faster alternative in practice (same `O(n)`, smaller constant factor): replace both hashmaps with two
`int[256]` arrays indexed by character code, storing "last seen position + 1" for each character —
avoids hashing and `Character` autoboxing entirely.
