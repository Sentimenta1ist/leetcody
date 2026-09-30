package com;

import com.utils.ListNode;
import com.utils.Node;
import com.utils.TreeNode;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.utils.LinkedListUtils.buildList;
import static com.utils.LinkedListUtils.toArray;
import static com.utils.NaryTreeUtils.buildNaryTree;
import static com.utils.NaryTreeUtils.printTree;
import static com.utils.TreeUtils.buildTree;
import static com.utils.TreeUtils.toArray;
import static com.utils.TreeUtils.printTree;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Problems {
    public static void main(String[] args) {

    }


    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            Integer num = map.get(target - nums[i]);
            if (num != null) {
                return new int[] {num, i};
            } else {
                map.put(nums[i], i);
            }
        }

        return null;
    }

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

    @Test
    public void twoSumTest() {
        assertArrayEquals(new int[] {0, 1}, twoSum(new int[] {2, 7, 11, 15}, 9));
        assertArrayEquals(new int[] {1, 2}, twoSum(new int[] {3, 2, 4}, 6));
        assertArrayEquals(new int[] {0, 1}, twoSum(new int[] {3, 3}, 6));
        assertArrayEquals(new int[] {0, 2}, twoSum(new int[] {-1, 5, -3, 4}, -4));
        assertArrayEquals(new int[] {3, 4}, twoSum(new int[] {1, 2, 3, 4, 5}, 9));
    }


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

    public int maxProfit2(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                sum += prices[i] - prices[i - 1];
            }
        }

        return sum;
    }

    @Test
    public void maxProfitTest() {
        assertEquals(5, maxProfit(new int[] {7, 1, 5, 3, 6, 4}));
        assertEquals(0, maxProfit(new int[] {7, 6, 4, 3, 1}));
        assertEquals(1, maxProfit(new int[] {1, 2}));
        assertEquals(2, maxProfit(new int[] {2, 4, 1}));
        assertEquals(0, maxProfit(new int[] {1}));
        assertEquals(0, maxProfit(new int[] {3, 3, 3, 3}));
    }

    @Test
    public void maxProfit2Test() {
        assertEquals(7, maxProfit2(new int[] {7, 1, 5, 3, 6, 4}));
        assertEquals(4, maxProfit2(new int[] {1, 2, 3, 4, 5}));
        assertEquals(0, maxProfit2(new int[] {7, 6, 4, 3, 1}));
        assertEquals(0, maxProfit2(new int[] {1}));
        assertEquals(0, maxProfit2(new int[] {3, 3, 3, 3}));
        assertEquals(4, maxProfit2(new int[] {2, 4, 1, 3}));
    }

    public ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode dummy = curr.next;
            curr.next = prev;
            prev = curr;
            curr = dummy;
        }
        return prev;
    }

    @Test
    public void reverseListTest() {
        assertArrayEquals(new int[] {5, 4, 3, 2, 1}, toArray(reverseList(buildList(new int[] {1, 2, 3, 4, 5}))));
        assertArrayEquals(new int[] {2, 1}, toArray(reverseList(buildList(new int[] {1, 2}))));
        assertArrayEquals(new int[] {1}, toArray(reverseList(buildList(new int[] {1}))));
        //assertArrayEquals(new int[] {}, toArray(reverseList(buildList(new int[] {}))));
    }

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

    @Test
    public void reverseBetweenTest() {
        assertArrayEquals(new int[] {1, 4, 3, 2, 5}, toArray(reverseBetween(buildList(new int[] {1, 2, 3, 4, 5}), 2, 4)));
        assertArrayEquals(new int[] {5}, toArray(reverseBetween(buildList(new int[] {5}), 1, 1)));
        assertArrayEquals(new int[] {2, 1}, toArray(reverseBetween(buildList(new int[] {1, 2}), 1, 2)));
        assertArrayEquals(new int[] {1, 2, 3}, toArray(reverseBetween(buildList(new int[] {1, 2, 3}), 1, 1)));
        assertArrayEquals(new int[] {4, 3, 2, 1}, toArray(reverseBetween(buildList(new int[] {1, 2, 3, 4}), 1, 4)));
    }

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

    @Test
    public void invertTreeTest() {
        assertArrayEquals(new Integer[] {4, 7, 2, 9, 6, 3, 1},
                toArray(invertTree(buildTree(new Integer[] {4, 2, 7, 1, 3, 6, 9}))));
        assertArrayEquals(new Integer[] {2, 3, 1}, toArray(invertTree(buildTree(new Integer[] {2, 1, 3}))));
        assertArrayEquals(new Integer[] {}, toArray(invertTree(buildTree(new Integer[] {}))));
    }

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

    @Test
    public void reverseOddLevelsTest() {
        TreeNode tree1 = buildTree(new Integer[] {2, 3, 5, 8, 13, 21, 34});
        System.out.println("Before:");
        printTree(tree1);
        TreeNode result1 = reverseOddLevels(tree1);
        System.out.println("After:");
        printTree(result1);
        assertArrayEquals(new Integer[] {2, 5, 3, 8, 13, 21, 34}, toArray(result1));

        TreeNode tree2 = buildTree(new Integer[] {7, 13, 11});
        System.out.println("Before:");
        printTree(tree2);
        TreeNode result2 = reverseOddLevels(tree2);
        System.out.println("After:");
        printTree(result2);
        assertArrayEquals(new Integer[] {7, 11, 13}, toArray(result2));

        TreeNode tree3 = buildTree(new Integer[] {5});
        System.out.println("Before:");
        printTree(tree3);
        TreeNode result3 = reverseOddLevels(tree3);
        System.out.println("After:");
        printTree(result3);
        assertArrayEquals(new Integer[] {5}, toArray(result3));

        TreeNode tree4 = buildTree(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        System.out.println("Before:");
        printTree(tree4);
        TreeNode result4 = reverseOddLevels(tree4);
        System.out.println("After:");
        printTree(result4);
        assertArrayEquals(new Integer[] {1, 3, 2, 4, 5, 6, 7, 15, 14, 13, 12, 11, 10, 9, 8}, toArray(result4));
    }

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

    @Test
    public void reverseOddLevelsDFSTest() {
        TreeNode tree1 = buildTree(new Integer[] {2, 3, 5, 8, 13, 21, 34});
        System.out.println("Before:");
        printTree(tree1);
        TreeNode result1 = reverseOddLevelsDFS(tree1);
        System.out.println("After:");
        printTree(result1);
        assertArrayEquals(new Integer[] {2, 5, 3, 8, 13, 21, 34}, toArray(result1));

        TreeNode tree2 = buildTree(new Integer[] {7, 13, 11});
        System.out.println("Before:");
        printTree(tree2);
        TreeNode result2 = reverseOddLevelsDFS(tree2);
        System.out.println("After:");
        printTree(result2);
        assertArrayEquals(new Integer[] {7, 11, 13}, toArray(result2));

        TreeNode tree3 = buildTree(new Integer[] {5});
        System.out.println("Before:");
        printTree(tree3);
        TreeNode result3 = reverseOddLevelsDFS(tree3);
        System.out.println("After:");
        printTree(result3);
        assertArrayEquals(new Integer[] {5}, toArray(result3));

        TreeNode tree4 = buildTree(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        System.out.println("Before:");
        printTree(tree4);
        TreeNode result4 = reverseOddLevelsDFS(tree4);
        System.out.println("After:");
        printTree(result4);
        assertArrayEquals(new Integer[] {1, 3, 2, 4, 5, 6, 7, 15, 14, 13, 12, 11, 10, 9, 8}, toArray(result4));
    }

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

    @Test
    public void preorderTraversalTest() {
        TreeNode binaryTree = buildTree(new Integer[] {1, null, 2, 3});
        printTree(binaryTree);
        assertEquals(List.of(1, 2, 3), preorderTraversal(binaryTree));

        Node naryTree = buildNaryTree(new Integer[] {1, null, 3, 2, 4, null, 5, 6});
        printTree(naryTree);
        assertEquals(List.of(1, 3, 5, 6, 2, 4), preorderTraversal(naryTree));
    }

    @Test
    public void postorderTraversalTest() {
        TreeNode binaryTree = buildTree(new Integer[] {1, null, 2, 3});
        printTree(binaryTree);
        assertEquals(List.of(3, 2, 1), postorderTraversal(binaryTree));

        Node naryTree = buildNaryTree(new Integer[] {1, null, 3, 2, 4, null, 5, 6});
        printTree(naryTree);
        assertEquals(List.of(5, 6, 3, 2, 4, 1), postorderTraversal(naryTree));
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

    @Test
    public void maxDepthTest() {
        Node tree1 = buildNaryTree(new Integer[] {1, null, 3, 2, 4, null, 5, 6});
        printTree(tree1);
        assertEquals(3, maxDepth(tree1));

        Node tree2 = buildNaryTree(new Integer[] {1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10});
        printTree(tree2);
        assertEquals(3, maxDepth(tree2));

        assertEquals(0, maxDepth(buildNaryTree(new Integer[] {})));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }


    @Test
    public void maxDepthBinaryTest() {
        TreeNode tree1 = buildTree(new Integer[] {3, 9, 20, null, null, 15, 7});
        printTree(tree1);
        assertEquals(3, maxDepth(tree1));

        TreeNode tree2 = buildTree(new Integer[] {1, null, 2});
        printTree(tree2);
        assertEquals(2, maxDepth(tree2));

        assertEquals(0, maxDepth(buildTree(new Integer[] {})));
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return helperMinDepth(root, 1);
    }

    private int helperMinDepth(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            return depth;
        }
        if (root.left == null) {
            return helperMinDepth(root.right, depth + 1);
        }
        if (root.right == null) {
            return helperMinDepth(root.left, depth + 1);
        } else {
            return Math.min(helperMinDepth(root.left, depth + 1), helperMinDepth(root.right, depth + 1));
        }
    }

    @Test
    public void minDepthTest() {
        TreeNode tree1 = buildTree(new Integer[] {3, 9, 20, null, null, 15, 7});
        printTree(tree1);
        assertEquals(2, minDepth(tree1));

        TreeNode tree2 = buildTree(new Integer[] {2, null, 3, null, 4, null, 5, null, 6});
        printTree(tree2);
        assertEquals(5, minDepth(tree2));

        TreeNode tree3 = buildTree(new Integer[] {1});
        printTree(tree3);
        assertEquals(1, minDepth(tree3));

        assertEquals(0, minDepth(buildTree(new Integer[] {})));
    }

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

    @Test
    public void isSameTreeTest() {
        TreeNode p1 = buildTree(new Integer[] {1, 2, 3});
        TreeNode q1 = buildTree(new Integer[] {1, 2, 3});
        printTree(p1);
        printTree(q1);
        assertEquals(true, isSameTree(p1, q1));

        TreeNode p2 = buildTree(new Integer[] {1, 2});
        TreeNode q2 = buildTree(new Integer[] {1, null, 2});
        printTree(p2);
        printTree(q2);
        assertEquals(false, isSameTree(p2, q2));

        assertEquals(true, isSameTree(buildTree(new Integer[] {}), buildTree(new Integer[] {})));
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null || subRoot == null) {
            return false;
        }
        return isSameTree(root, subRoot) || isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }


    @Test
    public void isSubtreeTest() {
        TreeNode root1 = buildTree(new Integer[] {3, 4, 5, 1, 2});
        TreeNode subRoot1 = buildTree(new Integer[] {4, 1, 2});
        printTree(root1);
        printTree(subRoot1);
        assertEquals(true, isSubtree(root1, subRoot1));

        TreeNode root2 = buildTree(new Integer[] {3, 4, 5, 1, 2, null, null, null, null, 0});
        TreeNode subRoot2 = buildTree(new Integer[] {4, 1, 2});
        printTree(root2);
        printTree(subRoot2);
        assertEquals(false, isSubtree(root2, subRoot2));
    }

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

    @Test
    public void levelOrderTest() {
        TreeNode tree1 = buildTree(new Integer[] {3, 9, 20, null, null, 15, 7});
        printTree(tree1);
        assertEquals(List.of(List.of(3), List.of(9, 20), List.of(15, 7)), levelOrder(tree1));

        TreeNode tree2 = buildTree(new Integer[] {1});
        printTree(tree2);
        assertEquals(List.of(List.of(1)), levelOrder(tree2));

        assertEquals(List.of(), levelOrder(buildTree(new Integer[] {})));
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

    @Test
    public void levelOrderNaryTest() {
        Node tree1 = buildNaryTree(new Integer[] {1, null, 3, 2, 4, null, 5, 6});
        printTree(tree1);
        assertEquals(List.of(List.of(1), List.of(3, 2, 4), List.of(5, 6)), levelOrder(tree1));

        Node tree2 = buildNaryTree(new Integer[] {1, null, 2, 3, 4, 5, null, null, 6, 7, null, 8, null, 9, 10});
        printTree(tree2);
        assertEquals(List.of(List.of(1), List.of(2, 3, 4, 5), List.of(6, 7, 8, 9, 10)), levelOrder(tree2));

        assertEquals(List.of(), levelOrder(buildNaryTree(new Integer[] {})));
    }

    public int[] findMode(TreeNode root) {
        return new int[0];

    }

    public void helperFindMode(TreeNode root, int prev, int count, int maxCount, List<Integer> res) {
        if (root == null) {
            return;
        }
        if (root.val == prev) {
            count++;
        }
        if (count > maxCount) {
            res = new ArrayList<>();
        }

    }

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
                if (!map1.containsKey(s1[i]) || !map2.containsKey(t1[i]) || map1.get(s1[i]) != t1[i]
                        || map2.get(t1[i]) != s1[i]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Test
    public void isIsomorphicTest() {
        assertEquals(true, isIsomorphic("egg", "add"));
        assertEquals(false, isIsomorphic("f11", "b23"));
        assertEquals(true, isIsomorphic("paper", "title"));

        // two DIFFERENT source chars both trying to map to the same target char —
        // easy to miss if you only track s->t and forget to check t->s as well
        assertEquals(false, isIsomorphic("badc", "baba"));
        assertEquals(false, isIsomorphic("ab", "aa"));

        // a char is allowed to map to itself
        assertEquals(true, isIsomorphic("foo", "foo"));

        // every position maps to the same single target char — still consistent
        assertEquals(true, isIsomorphic("aaaa", "bbbb"));

        // single character — trivially isomorphic either way
        assertEquals(true, isIsomorphic("a", "a"));
        assertEquals(true, isIsomorphic("a", "b"));
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            nums[Math.abs(nums[i]) - 1] = -Math.abs(nums[Math.abs(nums[i]) - 1]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }

    @Test
    public void findDisappearedNumbersTest() {
        assertEquals(List.of(5, 6), findDisappearedNumbers(new int[] {4, 3, 2, 7, 8, 2, 3, 1}));
        assertEquals(List.of(2), findDisappearedNumbers(new int[] {1, 1}));

        // nothing missing — every number from 1 to n appears
        assertEquals(List.of(), findDisappearedNumbers(new int[] {1, 2, 3}));

        // everything missing except one repeated number
        assertEquals(List.of(1, 2, 3), findDisappearedNumbers(new int[] {4, 4, 4, 4}));

        // single element
        assertEquals(List.of(), findDisappearedNumbers(new int[] {1}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);
            if (map.containsKey(sorted)) {
                map.get(sorted).add(str);
            } else {
                List<String> el = new ArrayList<>();
                el.add(str);
                map.put(sorted, el);
            }
        }
        return new ArrayList<>(map.values());
    }

    private List<List<String>> normalizeGroups(List<List<String>> groups) {
        List<List<String>> normalized = new ArrayList<>();
        for (List<String> group : groups) {
            List<String> sortedGroup = new ArrayList<>(group);
            Collections.sort(sortedGroup);
            normalized.add(sortedGroup);
        }
        normalized.sort((a, b) -> a.toString().compareTo(b.toString()));
        return normalized;
    }

    @Test
    public void groupAnagramsTest() {
        assertEquals(normalizeGroups(List.of(List.of("bat"), List.of("nat", "tan"), List.of("ate", "eat", "tea"))),
                normalizeGroups(groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"})));

        assertEquals(normalizeGroups(List.of(List.of(""))), normalizeGroups(groupAnagrams(new String[] {""})));

        assertEquals(normalizeGroups(List.of(List.of("a"))), normalizeGroups(groupAnagrams(new String[] {"a"})));

        // no anagram partners at all — every string is its own group
        assertEquals(normalizeGroups(List.of(List.of("abc"), List.of("def"), List.of("ghi"))),
                normalizeGroups(groupAnagrams(new String[] {"abc", "def", "ghi"})));

        // everything is one big anagram group
        assertEquals(normalizeGroups(List.of(List.of("abc", "bca", "cab"))),
                normalizeGroups(groupAnagrams(new String[] {"abc", "bca", "cab"})));
    }

    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];

        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            res[i] = prefix;
            prefix *= nums[i];
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i];
        }
        return res;
    }

    @Test
    public void productExceptSelfTest() {
        assertArrayEquals(new int[] {24, 12, 8, 6}, productExceptSelf(new int[] {1, 2, 3, 4}));

        assertArrayEquals(new int[] {0, 0, 9, 0, 0}, productExceptSelf(new int[] {-1, 1, 0, -3, 3}));

        // exactly two elements — smallest allowed input
        assertArrayEquals(new int[] {3, 2}, productExceptSelf(new int[] {2, 3}));

        // two zeros — every position ends up 0 (product always includes at least one zero)
        assertArrayEquals(new int[] {0, 0, 0}, productExceptSelf(new int[] {0, 5, 0}));

        // all negative — sign flips should still come out right
        assertArrayEquals(new int[] {6, 3, 2}, productExceptSelf(new int[] {-1, -2, -3}));
    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                left++;
                right--;
            } else if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            } else if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            } else {
                return false;
            }

        }
        return true;
    }

    @Test
    public void isPalindromeTest() {
        // digits count as alphanumeric too
        assertTrue(isPalindrome("1a1"));
        assertTrue(isPalindrome("A man, a plan, a canal: Panama"));
        assertFalse(isPalindrome("race a car"));

        // everything is non-alphanumeric — becomes an empty string, trivially a palindrome
        assertTrue(isPalindrome(" "));

        // single character
        assertTrue(isPalindrome("a"));

        // mixed case + punctuation, not a palindrome
        assertFalse(isPalindrome("Hello, World!"));
    }

    private static final Map<Character, Character> BRACKET_PAIRS = Map.of(')', '(', ']', '[', '}', '{');

    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (BRACKET_PAIRS.containsKey(c)) {
                if (stack.isEmpty() || !stack.pop().equals(BRACKET_PAIRS.get(c))) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    @Test
    public void isValidTest() {
        assertTrue(isValid("([])"));
        assertTrue(isValid("()"));
        assertTrue(isValid("()[]{}"));
        assertFalse(isValid("(]"));

        // unmatched opening bracket — nothing ever closes it
        assertFalse(isValid("("));

        // unmatched closing bracket — nothing to close
        assertFalse(isValid(")"));

        // same bracket types, but interleaved in the wrong order
        assertFalse(isValid("([)]"));

        // nested nested nested — nothing left open at the end
        assertTrue(isValid("{[()]}"));
    }

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            maxArea = Math.max(Math.min(height[left], height[right]) * (right - left), maxArea);
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }
        return maxArea;
    }


    @Test
    public void maxAreaTest() {
        assertEquals(49, maxArea(new int[] {1, 8, 6, 2, 5, 4, 8, 3, 7}));
        assertEquals(1, maxArea(new int[] {1, 1}));

        // last element is the tallest — the widest pair (ends) wins over any taller-but-narrower pair
        assertEquals(25, maxArea(new int[] {5, 4, 3, 2, 1, 6}));

        // all equal height — area is just width * height for the full span
        assertEquals(20, maxArea(new int[] {5, 5, 5, 5, 5}));

        // two elements — smallest possible input
        assertEquals(3, maxArea(new int[] {3, 7}));
    }

    public boolean exist(char[][] board, String word) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && backtrackExist(i, j, board, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean backtrackExist(int i, int j, char[][] board, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
            return false;
        }
        if (board[i][j] != word.charAt(index)) {
            return false;
        }
        char k = board[i][j];
        board[i][j] = '+';
        boolean result = backtrackExist(i + 1, j, board, word, index + 1) || backtrackExist(i, j + 1, board, word, index + 1)
                || backtrackExist(i - 1, j, board, word, index + 1) || backtrackExist(i, j - 1, board, word, index + 1);
        board[i][j] = k;
        return result;
    }

    @Test
    public void existTest() {
        char[][] board1 = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        assertTrue(exist(board1, "ABCCED"));
        assertTrue(exist(board1, "SEE"));
        assertFalse(exist(board1, "ABCB"));

        // single cell, single letter word
        assertTrue(exist(new char[][] {{'A'}}, "A"));

        // word longer than the number of cells on the board — can never fit
        assertFalse(exist(new char[][] {{'A', 'B'}, {'C', 'D'}}, "ABCDE"));

        // would spell the word only by reusing the same cell twice — not allowed
        assertFalse(exist(new char[][] {{'A', 'A'}}, "AAA"));
    }

    Map<Character, List<Character>> phoneLetters =
            Map.of('2', List.of('a', 'b', 'c'), '3', List.of('d', 'e', 'f'), '4', List.of('g', 'h', 'i'), '5',
                    List.of('j', 'k', 'l'), '6', List.of('m', 'n', 'o'), '7', List.of('p', 'q', 'r', 's'), '8',
                    List.of('t', 'u', 'v'), '9', List.of('w', 'x', 'y', 'z'));

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) {
            return res;
        }
        backtrack(res, new StringBuilder(), digits, 0);
        return res;
    }

    public void backtrack(List<String> res, StringBuilder curr, String digits, int index) {
        if (curr.length() == digits.length()) {
            res.add(curr.toString());
            return;
        }
        List<Character> letters = phoneLetters.get(digits.charAt(index));
        for (Character letter : letters) {
            curr.append(letter);
            backtrack(res, curr, digits, index + 1);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    @Test
    public void letterCombinationsTest() {
        assertEquals(List.of("ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"), letterCombinations("23"));
        assertEquals(List.of("a", "b", "c"), letterCombinations("2"));

        // empty input — no digits, no combinations
        assertEquals(List.of(), letterCombinations(""));

        // digit "9" maps to 4 letters, not 3
        assertEquals(List.of("w", "x", "y", "z"), letterCombinations("9"));

        // three digits — combinations grow multiplicatively (3 * 4 * 4 = 48 results)
        assertEquals(48, letterCombinations("279").size());
    }
}
