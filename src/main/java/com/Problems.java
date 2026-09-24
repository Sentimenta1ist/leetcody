package com;

import com.utils.ListNode;
import com.utils.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import org.junit.jupiter.api.Test;

import static com.utils.LinkedListUtils.buildList;
import static com.utils.LinkedListUtils.toArray;
import static com.utils.TreeUtils.buildTree;
import static com.utils.TreeUtils.toArray;
import static com.utils.TreeUtils.printTree;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        if(swap) {
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
}
