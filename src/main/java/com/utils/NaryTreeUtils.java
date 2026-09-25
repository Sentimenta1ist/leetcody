package com.utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NaryTreeUtils {

    /**
     * Builds an N-ary tree from LeetCode's level-order format: root value, then a
     * null, then each node's children values with a null marking the end of that
     * node's children group. Example: [1,null,3,2,4,null,5,6].
     */
    public static Node buildNaryTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        Node root = new Node(values[0]);
        root.children = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int i = 2; // skip the root value and the null right after it
        while (!queue.isEmpty() && i < values.length) {
            Node parent = queue.poll();
            List<Node> children = new ArrayList<>();
            while (i < values.length && values[i] != null) {
                Node child = new Node(values[i]);
                child.children = new ArrayList<>(); // overwritten below if it turns out to have children
                children.add(child);
                queue.add(child);
                i++;
            }
            i++; // skip the null delimiter
            parent.children = children;
        }
        return root;
    }

    /**
     * A simple indented print. A branch-drawing print like TreeUtils' one for binary
     * trees doesn't generalize cleanly to a node with a variable number of children.
     */
    public static void printTree(Node root) {
        if (root == null) {
            System.out.println("(empty)");
            return;
        }
        System.out.println(root.val);
        printChildren(root.children, "");
    }

    private static void printChildren(List<Node> children, String prefix) {
        if (children == null) {
            return;
        }
        for (int i = 0; i < children.size(); i++) {
            boolean last = i == children.size() - 1;
            Node child = children.get(i);
            System.out.println(prefix + (last ? "└── " : "├── ") + child.val);
            printChildren(child.children, prefix + (last ? "    " : "│   "));
        }
    }
}
