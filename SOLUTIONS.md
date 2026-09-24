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
