# Лог решённых задач

Чистый код + краткое описание подхода. Обновлять при каждой новой закрытой задаче (можно попросить
Claude добавить запись после того как тесты позеленели).

---

## Two Sum (LeetCode 1) — Easy

**Паттерн:** массивы/хеш-суммы, комплемент в hashmap за один проход.
**Подход:** для каждого элемента ищем `target - nums[i]` в уже пройденной части массива через hashmap.
Если нашли — это и есть пара. O(n) время, O(n) память.

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

**Паттерн:** массивы, running min + Kadane's algorithm в переодетом виде.
**Подход:** держим минимальную цену среди пройденных дней и максимальный профит `price - minPrice`
на каждом шаге. Одна транзакция, O(n)/O(1).

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

**Паттерн:** гриди.
**Подход:** неограниченное число транзакций ⇒ просто суммируем каждый положительный день-к-дню прирост
цены (эквивалентно "купить на каждом локальном минимуме, продать на каждом локальном максимуме").

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

**Паттерн:** связные списки, разворот на месте (3 указателя).
**Подход:** `prev` стартует с `null`, `curr` — с `head` (не `head.next`!). На каждом шаге запоминаем
`curr.next`, разворачиваем стрелку, сдвигаем оба указателя. Классический баг — начать `curr` с
`head.next` и `prev` с `head`: тогда `head.next` никогда не обнуляется и получается цикл.

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

**Паттерн:** связные списки, тот же разворот на месте, но на ограниченном сегменте + подшивка краёв.
**Подход:** dummy-нода на случай `left == 1`; довести `prev` до узла перед `left`; развернуть ровно
`right - left + 1` узлов тем же алгоритмом, что и в `reverseList`; в конце вручную подключить старый
хвост сегмента (который стал новым концом) к тому, что после сегмента, и старого `prev` — к новой
голове сегмента.

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

**Паттерн:** массивы/хеш-суммы, hashset для "уже видели".
**Подход:** идём по строке, добавляем символы в `Set`; как только встретили символ, который уже в
сете — это и есть первый повторившийся.

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

**Паттерн:** деревья, pre-order DFS с мутацией на месте.
**Подход:** на каждом узле меняем местами `.left`/`.right`, потом рекурсивно делаем то же самое для
обоих (уже переставленных) поддеревьев. Возвращаемое значение внутренних рекурсивных вызовов можно
игнорировать — метод мутирует и возвращает тот же узел, который и так уже доступен через
`root.left`/`root.right`.

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

**Паттерн:** деревья, BFS по уровням (сбор значений уровня + разворот) — версия 1.
**Подход:** обычный level-order BFS с `levelSize`-снимком; на каждом уровне складываем узлы **этого
уровня** (список пересоздаётся каждую итерацию `while`, иначе уровни смешиваются) в `ArrayList`, и если
уровень нечётный — разворачиваем их значения two-pointer свапом (`left`/`right` навстречу друг другу).
Дерево гарантированно **perfect** (по условию), поэтому не нужно беспокоиться о неполных уровнях.

```java
public TreeNode reverseOddLevels(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int level = 0;
    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        ArrayList<TreeNode> nodes = new ArrayList<>(); // пересоздаётся КАЖДЫЙ уровень
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

**Версия 2 — DFS с парой "зеркальных" указателей** (короче, без явного сбора уровня в список):
рекурсия сразу идёт по **двум** узлам одновременно — изначально `root.left` и `root.right` (концы
уровня 1). Флаг `swap` говорит, нужно ли поменять их значения (стартует с `true`, потому что уровень 1
нечётный), и инвертируется на каждом шаге вглубь, потому что чётность уровня строго чередуется.
Рекурсивные вызовы идут в **скрещенные** пары — `(left.right, right.left)` и `(left.left, right.right)` —
это единственные две комбинации, дающие зеркально-симметричные позиции на следующем уровне (структура
дерева при этом не меняется, только значения).

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
