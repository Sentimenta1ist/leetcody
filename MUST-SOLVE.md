# Must-Solve — 123 Problems (Google / Amazon / Microsoft / Meta)

A frequency-based list, not organized by pattern-teaching order like `README.md`. Source: LeetCode Premium company tags, snapshot from 2026-07-12 ([github.com/snehasishroy/leetcode-companywise-interview-questions](https://github.com/snehasishroy/leetcode-companywise-interview-questions)), window = last 6 months. Selection: a problem made the list if it appears for at least 3 of the 4 companies (Google/Amazon/Microsoft/Meta) and has a high frequency score; 94-97% of these companies' most-frequent problems have a LeetCode number under 1000. A few classics (Alien Dictionary, Clone Graph, LCS) were added by hand — they keep showing up in interview write-ups even without a high recent tag count.

**⭐ CORE** (64 problems) — know these by heart: recognize the pattern in under a minute, code it without looking anything up. The rest close out variations of the same patterns.

Each line: difficulty, CORE flag, one-line key idea (try it yourself before reading it), and which companies tagged it in the last 6 months — **bold** company = tagged in the last 30 days.

**Progress: 9/123 total, 9/64 core.**

## Arrays & Hashing (3/11, 4 core)

- [x] [Two Sum](https://leetcode.com/problems/two-sum) (1) — 🟢 Easy ⭐ CORE — hash map value→index, один проход — _**Google**, **Amazon**, **Microsoft**, **Meta**, Apple, **Bloomberg**_
- [x] [Best Time to Buy and Sell Stock](https://leetcode.com/problems/best-time-to-buy-and-sell-stock) (121) — 🟢 Easy ⭐ CORE — держи min цены слева, max(price−min) — _**Google**, **Amazon**, **Microsoft**, **Meta**, **Apple**, **Bloomberg**, Uber_
- [x] [Group Anagrams](https://leetcode.com/problems/group-anagrams) (49) — 🟡 Medium ⭐ CORE — ключ = отсортированное слово или счётчик 26 букв — _**Google**, **Amazon**, Microsoft, **Meta**, Apple, **Bloomberg**_
- [ ] [Longest Consecutive Sequence](https://leetcode.com/problems/longest-consecutive-sequence) (128) — 🟡 Medium ⭐ CORE — set; стартуй только с x, где нет x−1 — _**Google**, **Amazon**, **Microsoft**, Meta, Apple, Bloomberg_
- [ ] [Valid Anagram](https://leetcode.com/problems/valid-anagram) (242) — 🟢 Easy — счётчик 26 букв — _**Google**, Amazon, **Microsoft**, Meta, Bloomberg_
- [ ] [Contains Duplicate](https://leetcode.com/problems/contains-duplicate) (217) — 🟢 Easy — set — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Valid Sudoku](https://leetcode.com/problems/valid-sudoku) (36) — 🟡 Medium — 3 набора set: строки, столбцы, боксы (r//3,c//3) — _Google, Amazon, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Set Matrix Zeroes](https://leetcode.com/problems/set-matrix-zeroes) (73) — 🟡 Medium — первая строка/столбец как маркеры, O(1) памяти — _**Google**, Amazon, **Microsoft**, Meta, Bloomberg_
- [ ] [Longest Common Prefix](https://leetcode.com/problems/longest-common-prefix) (14) — 🟢 Easy — вертикальное сканирование или sort + сравнить первое/последнее — _**Google**, **Amazon**, **Microsoft**, Meta, **Apple**, **Bloomberg**_
- [ ] [String to Integer (atoi)](https://leetcode.com/problems/string-to-integer-atoi) (8) — 🟡 Medium — пробелы → знак → цифры → clamp overflow — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg, Netflix_
- [ ] [First Missing Positive](https://leetcode.com/problems/first-missing-positive) (41) — 🔴 Hard — cyclic sort: ставь x на индекс x−1 — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_

## Two Pointers (1/8, 3 core)

- [x] [3Sum](https://leetcode.com/problems/3sum) (15) — 🟡 Medium ⭐ CORE — sort + фикс i + two pointers, пропуск дублей — _**Google**, **Amazon**, **Microsoft**, Meta, Apple, **Bloomberg**_
- [ ] [Container With Most Water](https://leetcode.com/problems/container-with-most-water) (11) — 🟡 Medium ⭐ CORE — двигай меньшую стенку — _**Google**, **Amazon**, **Microsoft**, Meta, Apple, Bloomberg_
- [ ] [Valid Palindrome](https://leetcode.com/problems/valid-palindrome) (125) — 🟢 Easy ⭐ CORE — два указателя, пропуск не-alnum — _**Google**, Amazon, Microsoft, Meta, Apple, **Bloomberg**_
- [ ] [Merge Sorted Array](https://leetcode.com/problems/merge-sorted-array) (88) — 🟢 Easy — заполняй с конца — _**Google**, **Amazon**, Microsoft, **Meta**, Bloomberg_
- [ ] [Sort Colors](https://leetcode.com/problems/sort-colors) (75) — 🟡 Medium — Dutch flag: low/mid/high — _**Google**, **Amazon**, Microsoft, **Meta**, Apple, Bloomberg_
- [ ] [Next Permutation](https://leetcode.com/problems/next-permutation) (31) — 🟡 Medium — найди первый спад справа, swap с ближайшим бо́льшим, reverse хвоста — _**Google**, Amazon, **Microsoft**, **Meta**, Bloomberg_
- [ ] [Rotate Array](https://leetcode.com/problems/rotate-array) (189) — 🟡 Medium — три reverse — _**Google**, **Amazon**, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Two Sum II - Input Array Is Sorted](https://leetcode.com/problems/two-sum-ii-input-array-is-sorted) (167) — 🟡 Medium — l/r по сумме — _**Google**, Amazon, Microsoft, Meta, Bloomberg_

## Sliding Window (0/7, 3 core)

- [ ] [Longest Substring Without Repeating Characters](https://leetcode.com/problems/longest-substring-without-repeating-characters) (3) — 🟡 Medium ⭐ CORE — окно + last seen index — _**Google**, **Amazon**, **Microsoft**, **Meta**, Apple, **Bloomberg**, Netflix_
- [ ] [Longest Repeating Character Replacement](https://leetcode.com/problems/longest-repeating-character-replacement) (424) — 🟡 Medium ⭐ CORE — окно валидно пока len−maxFreq ≤ k — _**Google**, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Minimum Window Substring](https://leetcode.com/problems/minimum-window-substring) (76) — 🔴 Hard ⭐ CORE — need-счётчик + formed, сжимай слева — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Minimum Size Subarray Sum](https://leetcode.com/problems/minimum-size-subarray-sum) (209) — 🟡 Medium — расширяй, пока sum ≥ target — сжимай — _**Google**, Amazon, Microsoft, **Meta**, Bloomberg_
- [ ] [Max Consecutive Ones III](https://leetcode.com/problems/max-consecutive-ones-iii) (1004) — 🟡 Medium — окно с ≤ k нулями — _**Google**, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Fruit Into Baskets](https://leetcode.com/problems/fruit-into-baskets) (904) — 🟡 Medium — окно с ≤ 2 типами — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Permutation in String](https://leetcode.com/problems/permutation-in-string) (567) — 🟡 Medium — фиксированное окно + сравнение счётчиков — _Google, **Amazon**, **Microsoft**, Meta, Apple, Bloomberg_

## Stack (0/8, 3 core)

- [ ] [Valid Parentheses](https://leetcode.com/problems/valid-parentheses) (20) — 🟢 Easy ⭐ CORE — стек открывающих скобок — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Daily Temperatures](https://leetcode.com/problems/daily-temperatures) (739) — 🟡 Medium ⭐ CORE — монотонный убывающий стек индексов — _Google, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Min Stack](https://leetcode.com/problems/min-stack) (155) — 🟡 Medium ⭐ CORE — второй стек минимумов — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Decode String](https://leetcode.com/problems/decode-string) (394) — 🟡 Medium — стек (строка, множитель) — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Basic Calculator II](https://leetcode.com/problems/basic-calculator-ii) (227) — 🟡 Medium — стек чисел, * и / применяй сразу — _Google, Amazon, Meta, Apple, Bloomberg_
- [ ] [Asteroid Collision](https://leetcode.com/problems/asteroid-collision) (735) — 🟡 Medium — стек, столкновения только → vs ← — _Google, Amazon, Meta, Bloomberg_
- [ ] [Largest Rectangle in Histogram](https://leetcode.com/problems/largest-rectangle-in-histogram) (84) — 🔴 Hard — монотонный стек, ширина по границам — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Basic Calculator](https://leetcode.com/problems/basic-calculator) (224) — 🔴 Hard — стек знака при скобках — _Google, Amazon, Microsoft, Meta, **Bloomberg**_

## Binary Search (0/8, 5 core)

- [ ] [Binary Search](https://leetcode.com/problems/binary-search) (704) — 🟢 Easy ⭐ CORE — классика lo ≤ hi — _**Google**, Amazon, Microsoft, **Meta**, Bloomberg_
- [ ] [Search in Rotated Sorted Array](https://leetcode.com/problems/search-in-rotated-sorted-array) (33) — 🟡 Medium ⭐ CORE — одна половина всегда отсортирована — _**Google**, **Amazon**, Microsoft, **Meta**, **Bloomberg**, Uber_
- [ ] [Koko Eating Bananas](https://leetcode.com/problems/koko-eating-bananas) (875) — 🟡 Medium ⭐ CORE — бинпоиск по ответу: скорость k — _**Google**, **Amazon**, Microsoft, **Meta**, Apple, Bloomberg_
- [ ] [Find First and Last Position of Element in Sorted Array](https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array) (34) — 🟡 Medium ⭐ CORE — два поиска: lower bound и upper bound — _Google, **Amazon**, Microsoft, **Meta**, Apple, Bloomberg_
- [ ] [Find Peak Element](https://leetcode.com/problems/find-peak-element) (162) — 🟡 Medium — иди в сторону роста — _**Google**, Amazon, Microsoft, **Meta**, Bloomberg, Uber_
- [ ] [Search a 2D Matrix](https://leetcode.com/problems/search-a-2d-matrix) (74) — 🟡 Medium — матрица как плоский массив — _**Google**, **Amazon**, **Microsoft**, Meta, Bloomberg_
- [ ] [Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays) (4) — 🔴 Hard ⭐ CORE — бинпоиск разреза по меньшему массиву — _**Google**, **Amazon**, Microsoft, **Meta**, Apple, **Bloomberg**_
- [ ] [Split Array Largest Sum](https://leetcode.com/problems/split-array-largest-sum) (410) — 🔴 Hard — бинпоиск по ответу + жадная проверка — _**Google**, **Amazon**, Microsoft, Bloomberg, Uber_

## Linked List (1/9, 5 core)

- [x] [Reverse Linked List](https://leetcode.com/problems/reverse-linked-list) (206) — 🟢 Easy ⭐ CORE — prev/cur/next итеративно — _**Google**, **Amazon**, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Merge Two Sorted Lists](https://leetcode.com/problems/merge-two-sorted-lists) (21) — 🟢 Easy ⭐ CORE — dummy head — _**Google**, **Amazon**, **Microsoft**, Meta, Bloomberg_
- [ ] [Add Two Numbers](https://leetcode.com/problems/add-two-numbers) (2) — 🟡 Medium ⭐ CORE — сложение с carry — _**Google**, **Amazon**, **Microsoft**, **Meta**, Bloomberg_
- [ ] [Remove Nth Node From End of List](https://leetcode.com/problems/remove-nth-node-from-end-of-list) (19) — 🟡 Medium ⭐ CORE — два указателя с отрывом n — _**Google**, **Amazon**, Microsoft, **Meta**, Bloomberg_
- [ ] [Palindrome Linked List](https://leetcode.com/problems/palindrome-linked-list) (234) — 🟢 Easy — середина → reverse второй половины → сравнить — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Copy List with Random Pointer](https://leetcode.com/problems/copy-list-with-random-pointer) (138) — 🟡 Medium — hash map old→new или interleaving — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Reorder List](https://leetcode.com/problems/reorder-list) (143) — 🟡 Medium — середина + reverse + merge — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Merge k Sorted Lists](https://leetcode.com/problems/merge-k-sorted-lists) (23) — 🔴 Hard ⭐ CORE — min-heap из голов, O(N log k) — _Google, **Amazon**, Microsoft, Meta, Apple, **Bloomberg**_
- [ ] [Reverse Nodes in k-Group](https://leetcode.com/problems/reverse-nodes-in-k-group) (25) — 🔴 Hard — reverse группами по k, склейка — _Google, **Amazon**, **Microsoft**, Bloomberg_

## Trees (1/12, 6 core)

- [x] [Maximum Depth of Binary Tree](https://leetcode.com/problems/maximum-depth-of-binary-tree) (104) — 🟢 Easy ⭐ CORE — DFS 1+max(l,r) — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Binary Tree Level Order Traversal](https://leetcode.com/problems/binary-tree-level-order-traversal) (102) — 🟡 Medium ⭐ CORE — BFS по уровням — _Google, Amazon, Microsoft, Bloomberg_
- [ ] [Diameter of Binary Tree](https://leetcode.com/problems/diameter-of-binary-tree) (543) — 🟢 Easy ⭐ CORE — DFS высоты, обновляй ответ l+r — _Google, Amazon, **Microsoft**, Meta, Bloomberg_
- [ ] [Lowest Common Ancestor of a Binary Tree](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree) (236) — 🟡 Medium ⭐ CORE — если найден в обеих ветках — это LCA — _Google, **Amazon**, Meta, Bloomberg_
- [ ] [Validate Binary Search Tree](https://leetcode.com/problems/validate-binary-search-tree) (98) — 🟡 Medium ⭐ CORE — DFS с границами (lo, hi) — _Google, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Binary Tree Right Side View](https://leetcode.com/problems/binary-tree-right-side-view) (199) — 🟡 Medium — BFS, последний на уровне — _Google, **Amazon**, Microsoft, Meta_
- [ ] [Kth Smallest Element in a BST](https://leetcode.com/problems/kth-smallest-element-in-a-bst) (230) — 🟡 Medium — inorder, k-й элемент — _Google, Amazon, **Microsoft**, Meta, Uber_
- [ ] [Symmetric Tree](https://leetcode.com/problems/symmetric-tree) (101) — 🟢 Easy — зеркальное сравнение двух узлов — _**Google**, Amazon, Microsoft, **Meta**, Bloomberg_
- [ ] [All Nodes Distance K in Binary Tree](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree) (863) — 🟡 Medium — parent-указатели + BFS от target — _**Google**, Amazon, Microsoft, Meta, Apple_
- [ ] [Binary Tree Maximum Path Sum](https://leetcode.com/problems/binary-tree-maximum-path-sum) (124) — 🔴 Hard ⭐ CORE — DFS возвращает лучшую ветку, ответ = node+l+r — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Serialize and Deserialize Binary Tree](https://leetcode.com/problems/serialize-and-deserialize-binary-tree) (297) — 🔴 Hard — preorder с null-маркерами — _Google, Amazon, Microsoft, Uber_
- [ ] [Vertical Order Traversal of a Binary Tree](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree) (987) — 🔴 Hard — BFS/DFS с (col,row), сортировка — _Amazon, Microsoft, Meta, Bloomberg, Uber_

## Heap / Top-K (0/7, 5 core)

- [ ] [Top K Frequent Elements](https://leetcode.com/problems/top-k-frequent-elements) (347) — 🟡 Medium ⭐ CORE — счётчик + heap размера k или bucket sort — _**Google**, **Amazon**, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Kth Largest Element in an Array](https://leetcode.com/problems/kth-largest-element-in-an-array) (215) — 🟡 Medium ⭐ CORE — min-heap размера k или quickselect — _**Google**, Amazon, Microsoft, **Meta**, Apple, Bloomberg_
- [ ] [Meeting Rooms II](https://leetcode.com/problems/meeting-rooms-ii) (253) — 🟡 Medium ⭐ CORE — сортировка по start + min-heap по end — _Google, Amazon, Microsoft, Meta, Apple, Bloomberg, Uber_
- [ ] [Task Scheduler](https://leetcode.com/problems/task-scheduler) (621) — 🟡 Medium — формула (maxFreq−1)*(n+1)+countMax — _Google, **Amazon**, Microsoft, **Apple**, Bloomberg_
- [ ] [Sort Characters By Frequency](https://leetcode.com/problems/sort-characters-by-frequency) (451) — 🟡 Medium — счётчик + сортировка/bucket — _Google, Amazon, **Microsoft**, **Meta**, Bloomberg_
- [ ] [Find Median from Data Stream](https://leetcode.com/problems/find-median-from-data-stream) (295) — 🔴 Hard ⭐ CORE — две кучи: max-heap слева, min-heap справа — _**Google**, Amazon, Microsoft, Apple, Bloomberg, Uber_
- [ ] [Sliding Window Maximum](https://leetcode.com/problems/sliding-window-maximum) (239) — 🔴 Hard ⭐ CORE — монотонная deque индексов — _Google, **Amazon**, **Microsoft**, Meta, **Bloomberg**_

## Graphs (0/12, 5 core)

- [ ] [Number of Islands](https://leetcode.com/problems/number-of-islands) (200) — 🟡 Medium ⭐ CORE — DFS/BFS заливка — _**Google**, **Amazon**, Microsoft, Meta, Apple, Bloomberg, Uber_
- [ ] [Rotting Oranges](https://leetcode.com/problems/rotting-oranges) (994) — 🟡 Medium ⭐ CORE — multi-source BFS — _**Google**, **Amazon**, **Microsoft**, Meta, Bloomberg, Uber_
- [ ] [Course Schedule](https://leetcode.com/problems/course-schedule) (207) — 🟡 Medium ⭐ CORE — топосорт Кана / детект цикла — _**Google**, **Amazon**, Microsoft, Meta, Apple, Uber_
- [ ] [Course Schedule II](https://leetcode.com/problems/course-schedule-ii) (210) — 🟡 Medium ⭐ CORE — топосорт Кана, выдать порядок — _Google, **Amazon**, Microsoft, Meta, Apple, Bloomberg, Uber, Netflix_
- [ ] [Clone Graph](https://leetcode.com/problems/clone-graph) (133) — 🟡 Medium — DFS + map old→clone — _Google, Amazon, Meta_
- [ ] [Evaluate Division](https://leetcode.com/problems/evaluate-division) (399) — 🟡 Medium — взвешенный граф + DFS/BFS — _Google, Amazon, Microsoft, Bloomberg, Uber_
- [ ] [Number of Provinces](https://leetcode.com/problems/number-of-provinces) (547) — 🟡 Medium — DFS или Union-Find — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Accounts Merge](https://leetcode.com/problems/accounts-merge) (721) — 🟡 Medium — Union-Find по email — _Google, Amazon, Microsoft, Meta, **Bloomberg**_
- [ ] [Surrounded Regions](https://leetcode.com/problems/surrounded-regions) (130) — 🟡 Medium — заливка от границы — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Word Ladder](https://leetcode.com/problems/word-ladder) (127) — 🔴 Hard ⭐ CORE — BFS по словам, паттерны h*t — _Google, **Amazon**, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Making A Large Island](https://leetcode.com/problems/making-a-large-island) (827) — 🔴 Hard — пометить острова id+размер, пробовать каждый 0 — _Google, Amazon, Microsoft, Meta, Uber_
- [ ] [Alien Dictionary](https://leetcode.com/problems/alien-dictionary) (269) — 🔴 Hard — граф из соседних слов + топосорт — _Google, Amazon, Bloomberg, Uber_

## Backtracking (1/8, 6 core)

- [ ] [Letter Combinations of a Phone Number](https://leetcode.com/problems/letter-combinations-of-a-phone-number) (17) — 🟡 Medium ⭐ CORE — DFS по цифрам — _**Google**, **Amazon**, Microsoft, Meta, Apple, **Bloomberg**_
- [ ] [Generate Parentheses](https://leetcode.com/problems/generate-parentheses) (22) — 🟡 Medium ⭐ CORE — open < n, close < open — _**Google**, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Subsets](https://leetcode.com/problems/subsets) (78) — 🟡 Medium ⭐ CORE — взять/не взять — _Google, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Permutations](https://leetcode.com/problems/permutations) (46) — 🟡 Medium ⭐ CORE — used[] или swap — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Combination Sum](https://leetcode.com/problems/combination-sum) (39) — 🟡 Medium ⭐ CORE — DFS с повтором текущего индекса — _**Google**, Amazon, Microsoft, Meta, Bloomberg_
- [x] [Word Search](https://leetcode.com/problems/word-search) (79) — 🟡 Medium ⭐ CORE — DFS по сетке, метка visited — _Google, **Amazon**, Microsoft, Meta, Bloomberg, Uber_
- [ ] [Palindrome Partitioning](https://leetcode.com/problems/palindrome-partitioning) (131) — 🟡 Medium — DFS + проверка палиндрома — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [N-Queens](https://leetcode.com/problems/n-queens) (51) — 🔴 Hard — sets: cols, diag r−c, anti r+c — _**Google**, **Amazon**, Microsoft, **Meta**, Bloomberg_

## Dynamic Programming (1/15, 10 core)

- [x] [Climbing Stairs](https://leetcode.com/problems/climbing-stairs) (70) — 🟢 Easy ⭐ CORE — fib: dp[i]=dp[i−1]+dp[i−2] — _**Google**, **Amazon**, Microsoft, Meta, **Bloomberg**_
- [ ] [House Robber](https://leetcode.com/problems/house-robber) (198) — 🟡 Medium ⭐ CORE — max(skip, take+dp[i−2]) — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Maximum Subarray](https://leetcode.com/problems/maximum-subarray) (53) — 🟡 Medium ⭐ CORE — Kadane — _**Google**, **Amazon**, **Microsoft**, **Meta**, Apple, **Bloomberg**_
- [ ] [Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring) (5) — 🟡 Medium ⭐ CORE — expand around center — _**Google**, **Amazon**, **Microsoft**, **Meta**, Apple, Bloomberg, Uber_
- [ ] [Coin Change](https://leetcode.com/problems/coin-change) (322) — 🟡 Medium ⭐ CORE — unbounded knapsack, min монет — _**Google**, **Amazon**, Microsoft, **Meta**, Bloomberg_
- [ ] [Longest Increasing Subsequence](https://leetcode.com/problems/longest-increasing-subsequence) (300) — 🟡 Medium ⭐ CORE — O(n log n): tails + бинпоиск — _**Google**, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Jump Game](https://leetcode.com/problems/jump-game) (55) — 🟡 Medium ⭐ CORE — жадно: самая дальняя достижимая — _**Google**, **Amazon**, Microsoft, **Meta**, **Bloomberg**_
- [ ] [Unique Paths](https://leetcode.com/problems/unique-paths) (62) — 🟡 Medium — dp по сетке / комбинаторика — _Google, Amazon, **Microsoft**, Meta, Bloomberg_
- [ ] [Maximum Product Subarray](https://leetcode.com/problems/maximum-product-subarray) (152) — 🟡 Medium — держи и max, и min — _**Google**, **Amazon**, Microsoft, **Meta**, Bloomberg_
- [ ] [Word Break](https://leetcode.com/problems/word-break) (139) — 🟡 Medium ⭐ CORE — dp[i] = есть j: dp[j] и s[j:i] в словаре — _Google, Amazon, Microsoft, Bloomberg_
- [ ] [Edit Distance](https://leetcode.com/problems/edit-distance) (72) — 🟡 Medium ⭐ CORE — 2D dp: insert/delete/replace — _**Google**, **Amazon**, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Longest Common Subsequence](https://leetcode.com/problems/longest-common-subsequence) (1143) — 🟡 Medium — 2D dp по двум строкам — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Trapping Rain Water](https://leetcode.com/problems/trapping-rain-water) (42) — 🔴 Hard ⭐ CORE — two pointers с leftMax/rightMax — _**Google**, **Amazon**, **Microsoft**, Meta, **Bloomberg**_
- [ ] [Longest Valid Parentheses](https://leetcode.com/problems/longest-valid-parentheses) (32) — 🔴 Hard — стек индексов или dp — _Google, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Regular Expression Matching](https://leetcode.com/problems/regular-expression-matching) (10) — 🔴 Hard — 2D dp, особый случай * — _**Google**, Amazon, Microsoft, Meta, **Bloomberg**_

## Design (0/6, 4 core)

- [ ] [LRU Cache](https://leetcode.com/problems/lru-cache) (146) — 🟡 Medium ⭐ CORE — hash map + двусвязный список — _**Google**, **Amazon**, **Microsoft**, Meta, Apple, Bloomberg, Uber, Netflix_
- [ ] [Time Based Key-Value Store](https://leetcode.com/problems/time-based-key-value-store) (981) — 🟡 Medium ⭐ CORE — map key→[(t,v)] + бинпоиск по t — _Google, Amazon, Meta, Apple, Bloomberg, Uber, Netflix_
- [ ] [Insert Delete GetRandom O(1)](https://leetcode.com/problems/insert-delete-getrandom-o1) (380) — 🟡 Medium ⭐ CORE — массив + map value→index, swap с последним — _**Google**, Amazon, Apple, Bloomberg, Uber_
- [ ] [Design Hit Counter](https://leetcode.com/problems/design-hit-counter) (362) — 🟡 Medium — очередь таймстемпов / кольцевой буфер 300 — _Google, Microsoft, Meta, Apple, Uber_
- [ ] [Implement Trie (Prefix Tree)](https://leetcode.com/problems/implement-trie-prefix-tree) (208) — 🟡 Medium ⭐ CORE — Trie: children + isEnd — _Google, Microsoft, Apple, Bloomberg_
- [ ] [Word Search II](https://leetcode.com/problems/word-search-ii) (212) — 🔴 Hard — Trie из слов + DFS по доске — _Google, **Amazon**, Microsoft, Meta, Uber_

## Intervals & Prefix Sum (1/7, 3 core)

- [ ] [Merge Intervals](https://leetcode.com/problems/merge-intervals) (56) — 🟡 Medium ⭐ CORE — sort по start, склеивай — _Google, **Amazon**, **Microsoft**, Meta, Apple, **Bloomberg**, Uber_
- [ ] [Insert Interval](https://leetcode.com/problems/insert-interval) (57) — 🟡 Medium — до / пересечение / после — _Google, **Amazon**, Microsoft, Meta_
- [ ] [Non-overlapping Intervals](https://leetcode.com/problems/non-overlapping-intervals) (435) — 🟡 Medium — sort по end, жадно — _Google, Amazon, Microsoft, Bloomberg_
- [ ] [Subarray Sum Equals K](https://leetcode.com/problems/subarray-sum-equals-k) (560) — 🟡 Medium ⭐ CORE — prefix sum + map count[sum−k] — _**Google**, **Amazon**, Microsoft, Meta, Apple, **Bloomberg**_
- [x] [Product of Array Except Self](https://leetcode.com/problems/product-of-array-except-self) (238) — 🟡 Medium ⭐ CORE — prefix × suffix, без деления — _**Google**, **Amazon**, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Contiguous Array](https://leetcode.com/problems/contiguous-array) (525) — 🟡 Medium — 0→−1, prefix sum + first index — _Google, Amazon, Microsoft, Meta, Bloomberg_
- [ ] [Gas Station](https://leetcode.com/problems/gas-station) (134) — 🟡 Medium — если total ≥ 0 — старт после последнего провала — _Google, Amazon, **Microsoft**, Meta, Bloomberg_

## Math & Matrix (0/5, 2 core)

- [ ] [Rotate Image](https://leetcode.com/problems/rotate-image) (48) — 🟡 Medium ⭐ CORE — транспонировать + reverse строк — _**Google**, **Amazon**, **Microsoft**, Meta, Apple, Bloomberg_
- [ ] [Spiral Matrix](https://leetcode.com/problems/spiral-matrix) (54) — 🟡 Medium ⭐ CORE — четыре границы, сужай — _**Google**, **Amazon**, Microsoft, Meta, Apple, Bloomberg_
- [ ] [Pow(x, n)](https://leetcode.com/problems/powx-n) (50) — 🟡 Medium — быстрое возведение, n<0 — _**Google**, **Amazon**, Microsoft, Meta, Bloomberg_
- [ ] [Majority Element](https://leetcode.com/problems/majority-element) (169) — 🟢 Easy — Boyer–Moore voting — _**Google**, **Amazon**, **Microsoft**, Meta, Bloomberg_
- [ ] [Single Number](https://leetcode.com/problems/single-number) (136) — 🟢 Easy — XOR всего — _Google, Amazon, **Microsoft**, **Meta**, **Bloomberg**_
