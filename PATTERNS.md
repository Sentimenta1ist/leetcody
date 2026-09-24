# Шпаргалка по паттернам

Организовано в порядке плана изучения (см. `CLAUDE.md`). Это шаблоны **форм**, которые нужно уметь
воспроизвести не задумываясь — не решения конкретных задач.

---

## 1. Деревья

### DFS (рекурсивно)
```java
void dfs(TreeNode node) {
    if (node == null) return;          // base case
    dfs(node.left);
    // pre-order: обработка ЗДЕСЬ, до ухода в детей
    dfs(node.right);
    // post-order: обработка ЗДЕСЬ, после возврата из обоих детей
}
```
- **Pre-order** — когда обработка родителя не зависит от результата детей (копирование, печать).
- **In-order** — для BST даёт отсортированный порядок.
- **Post-order** — когда результат узла зависит от результатов детей (высота, диаметр, сумма поддерева).

### BFS (по уровням)
```java
Queue<TreeNode> queue = new LinkedList<>();
queue.add(root);
while (!queue.isEmpty()) {
    int levelSize = queue.size();      // фиксируем ДО цикла — иначе не отделить уровни
    for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        // обработать node
        if (node.left != null) queue.add(node.left);
        if (node.right != null) queue.add(node.right);
    }
}
```

### Частые под-паттерны
- **Высота/диаметр** — post-order DFS, диаметр = max(лево, право) отдельно возвращается вверх, а
  "лево + право" трекается в глобальную переменную (не в return).
- **LCA (lowest common ancestor)** — "информация поднимается наверх через return": если узел нашёл
  оба искомых значения ниже себя (по одному в каждом поддереве) — он и есть ответ.
- **Validate BST** — DFS с передачей границ `(min, max)` вниз через параметры рекурсии.
- **Path Sum варианты** — DFS, накапливающий сумму по пути (либо передавать остаток вниз, либо
  накапливать текущую сумму).
- **Инвертировать дерево на месте** — pre-order DFS: свапнуть `.left`/`.right` у текущего узла ДО
  рекурсии, потом рекурсивно то же для обоих (уже переставленных) поддеревьев. Возвращаемое значение
  внутренних вызовов можно игнорировать — это мутация в чистом виде.
- **Симметрия / зеркальные пары ("Mirror", Symmetric Tree, работа по уровням в perfect-дереве)** —
  вместо одного `dfs(node)` рекурсия идёт сразу по **двум** узлам: `dfs(left, right)`, начиная с
  `dfs(root.left, root.right)`. Рекурсивные вызовы идут в **скрещенные** пары —
  `dfs(left.right, right.left)` и `dfs(left.left, right.right)` — это единственные две комбинации,
  дающие зеркально-симметричные позиции на следующем уровне. Если нужно доп. знать чётность
  уровня/глубины — передавай `boolean`-флаг и инвертируй его (`!flag`) на каждом шаге вглубь, чётность
  всегда строго чередуется, не нужен явный счётчик.

---

## 2. Массивы / Хеш-суммы

### Two Sum (комплемент в hashmap за один проход)
```java
Map<Integer, Integer> seen = new HashMap<>();
for (int i = 0; i < nums.length; i++) {
    int complement = target - nums[i];
    if (seen.containsKey(complement)) return new int[]{seen.get(complement), i};
    seen.put(nums[i], i);
}
```

### Prefix sum + hashmap (подмассив с суммой K)
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

### Sliding window (переменный размер)
```java
int left = 0;
for (int right = 0; right < nums.length; right++) {
    // добавить nums[right] в окно
    while (/* окно невалидно */) {
        // убрать nums[left] из окна
        left++;
    }
    // обновить ответ, используя текущее окно [left, right]
}
```

### Two pointers (навстречу друг другу)
```java
int left = 0, right = nums.length - 1;
while (left < right) {
    // сравнить/посчитать, сдвинуть left++ или right--
}
```

---

## 3. Графы

### DFS с visited
```java
void dfs(int node, Set<Integer> visited, Map<Integer, List<Integer>> graph) {
    if (visited.contains(node)) return;
    visited.add(node);
    for (int neighbor : graph.get(node)) dfs(neighbor, visited, graph);
}
```

### BFS с visited
```java
Queue<Integer> queue = new LinkedList<>();
Set<Integer> visited = new HashSet<>();
queue.add(start);
visited.add(start);                     // помечать ПРИ ДОБАВЛЕНИИ в очередь, не при извлечении
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

### Топологическая сортировка (Kahn's, BFS-based)
```java
int[] inDegree = new int[n];
// ... заполнить inDegree по рёбрам графа
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
// order.size() < n  =>  в графе есть цикл
```

### Multi-source BFS
Как обычный BFS, но в очередь изначально кладутся **все** стартовые узлы сразу (не один), например все
"гнилые апельсины" сразу, а не по одному — это даёт кратчайшее расстояние от ближайшего источника.

---

## 4. Стек

### Базовый стек (matching/undo)
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

### Монотонный стек (next greater element)
```java
int[] result = new int[n];
Deque<Integer> stack = new ArrayDeque<>();   // хранит ИНДЕКСЫ
for (int i = 0; i < n; i++) {
    while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
        result[stack.pop()] = nums[i];
    }
    stack.push(i);
}
// то, что осталось в стеке после цикла — элементы без "следующего большего"
```
Признак задачи: "следующий больший/меньший элемент", "сколько дней ждать пока цена вырастет",
"площадь прямоугольника в гистограмме".

---

## 5. Бектрекинг

```java
void backtrack(List<Integer> current, /* остальное состояние */) {
    if (/* условие завершения */) {
        result.add(new ArrayList<>(current));   // КОПИЯ, не сам current
        return;
    }
    for (int choice : /* доступные варианты на этом шаге */) {
        current.add(choice);            // выбрать
        backtrack(current, ...);        // исследовать
        current.remove(current.size() - 1);  // откатить — самая частая забытая строчка
    }
}
```
Единая идея: перебрать все варианты, после каждой попытки откатить состояние перед следующей.
Частые формы: subsets (брать/не брать), permutations (использованные элементы трекать отдельно),
combination sum (можно повторно использовать элемент — не увеличивать индекс старта).

---

## 6. Гриди

Ментальная модель: **сначала отсортировать по какому-то критерию, потом на каждом шаге брать локально
оптимальный вариант, никогда к нему не возвращаясь.**

Признаки, что тут может сработать гриди (а не DP):
- Задача про интервалы/расписание ("максимум непересекающихся встреч") — сортировка по концу интервала.
- "Минимизируй/максимизируй что-то, где выбор для одного элемента не влияет на то, какие выборы были
  доступны для других" — если это неверно (выборы взаимозависимы сложным образом) — скорее всего DP,
  не гриди.

Как проверить гипотезу гриди на собеседовании: попробовать привести контрпример руками на маленьком
входе (3-4 элемента). Если гриди-стратегия ломается — задача не гриди, минимум почти наверняка DP.

---

## 7. ДП

### Универсальный рецепт — 3 вопроса перед кодом
1. Что значит `dp[i]` (или `dp[i][j]`)? — сформулировать **словами**, не формулой.
2. Как `dp[i]` выражается через более мелкие подзадачи? (переход)
3. Какой base case?

### 6 форм
| Форма | Признак в условии | Референс |
|---|---|---|
| Линейная 1D | "на каждом шаге решить: включать элемент или нет", один массив | Climbing Stairs, House Robber |
| Grid/2D | "путь по сетке вправо/вниз" | Unique Paths, Minimum Path Sum |
| Knapsack | "набор предметов, ограничение по сумме/весу" | Coin Change, Partition Equal Subset Sum |
| Two-sequence | **две** строки/массива на входе | Longest Common Subsequence, Edit Distance |
| Interval DP | "раздели диапазон на части", таблица по ДЛИНЕ диапазона, не по индексу | Longest Palindromic Substring, Burst Balloons |
| State-machine | несколько взаимоисключающих состояний на шаг | Best Time to Buy/Sell Stock (cooldown/fee) |

Порядок действий на новой ДП-задаче: сначала определить, в какую из 6 форм она укладывается, потом
уже писать рекуррентное соотношение под эту форму.
