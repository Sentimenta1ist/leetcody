package com;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeUtils {

    /**
     * Строит дерево из level-order массива в формате LeetCode:
     * null означает отсутствующего ребёнка, но под null дальше ничего не описывается.
     * Пример: [4,2,7,1,3,6,9] или [1,2,3,null,4].
     */
    public static TreeNode buildTree(Integer[] values) {
        if (values == null || values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < values.length) {
            TreeNode current = queue.poll();
            if (i < values.length) {
                Integer leftVal = values[i++];
                if (leftVal != null) {
                    current.left = new TreeNode(leftVal);
                    queue.add(current.left);
                }
            }
            if (i < values.length) {
                Integer rightVal = values[i++];
                if (rightVal != null) {
                    current.right = new TreeNode(rightVal);
                    queue.add(current.right);
                }
            }
        }
        return root;
    }

    /**
     * Обратное преобразование — level-order массив с null для отсутствующих детей,
     * с обрезанными висящими null в конце (как в выводе LeetCode).
     */
    public static Integer[] toArray(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {
                result.add(null);
            } else {
                result.add(current.val);
                queue.add(current.left);
                queue.add(current.right);
            }
        }
        int end = result.size();
        while (end > 0 && result.get(end - 1) == null) {
            end--;
        }
        return result.subList(0, end).toArray(new Integer[0]);
    }

    /**
     * Красивый визуальный принт дерева сверху вниз: корень наверху, дети под ним,
     * ветки рисуются символами '/' и '\'.
     */
    public static void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("(пусто)");
            return;
        }
        for (String line : display(root).lines) {
            System.out.println(line);
        }
    }

    private static class Display {
        final List<String> lines;
        final int width;
        final int middle; // позиция символа, к которому подходит ветка сверху

        Display(List<String> lines, int width, int middle) {
            this.lines = lines;
            this.width = width;
            this.middle = middle;
        }
    }

    private static Display display(TreeNode node) {
        String s = String.valueOf(node.val);
        int u = s.length();

        if (node.left == null && node.right == null) {
            return new Display(List.of(s), u, u / 2);
        }

        if (node.right == null) {
            Display left = display(node.left);
            String firstLine = " ".repeat(left.middle + 1) + "_".repeat(left.width - left.middle - 1) + s;
            String secondLine = " ".repeat(left.middle) + "/" + " ".repeat(left.width - left.middle - 1 + u);
            List<String> lines = new ArrayList<>();
            lines.add(firstLine);
            lines.add(secondLine);
            for (String line : left.lines) {
                lines.add(line + " ".repeat(u));
            }
            return new Display(lines, left.width + u, left.width + u / 2);
        }

        if (node.left == null) {
            Display right = display(node.right);
            String firstLine = s + "_".repeat(right.middle) + " ".repeat(right.width - right.middle);
            String secondLine = " ".repeat(u + right.middle) + "\\" + " ".repeat(right.width - right.middle - 1);
            List<String> lines = new ArrayList<>();
            lines.add(firstLine);
            lines.add(secondLine);
            for (String line : right.lines) {
                lines.add(" ".repeat(u) + line);
            }
            return new Display(lines, right.width + u, u / 2);
        }

        Display left = display(node.left);
        Display right = display(node.right);
        String firstLine = " ".repeat(left.middle + 1) + "_".repeat(left.width - left.middle - 1) + s
                + "_".repeat(right.middle) + " ".repeat(right.width - right.middle);
        String secondLine = " ".repeat(left.middle) + "/"
                + " ".repeat(left.width - left.middle - 1 + u + right.middle) + "\\"
                + " ".repeat(right.width - right.middle - 1);

        List<String> leftLines = new ArrayList<>(left.lines);
        List<String> rightLines = new ArrayList<>(right.lines);
        while (leftLines.size() < rightLines.size()) {
            leftLines.add(" ".repeat(left.width));
        }
        while (rightLines.size() < leftLines.size()) {
            rightLines.add(" ".repeat(right.width));
        }

        List<String> lines = new ArrayList<>();
        lines.add(firstLine);
        lines.add(secondLine);
        for (int i = 0; i < leftLines.size(); i++) {
            lines.add(leftLines.get(i) + " ".repeat(u) + rightLines.get(i));
        }

        return new Display(lines, left.width + right.width + u, left.width + u / 2);
    }
}
