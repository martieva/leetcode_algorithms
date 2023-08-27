//Minimum Depth of Binary Tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (leftDepth == 0) {
            return rightDepth + 1;
        }
        if (rightDepth == 0) {
            return leftDepth + 1;
        }
        return Math.min(leftDepth, rightDepth) + 1;
        
    }
}

//Maximum Difference Between Node and Ancestor
//Given the root of a binary tree, find the maximum value v for which there exist different nodes a and b where v = |a.val - b.val| and a is an ancestor of b.
//A node a is an ancestor of b if either: any child of a is equal to b or any child of a is an ancestor of b.
//TODO

//Diameter of Binary Tree
//Given the root of a binary tree, return the length of the diameter of the tree.
// The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.
// The length of a path between two nodes is represented by the number of edges between them.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        
        int[] maxLeft = search(root.left);
        int[] maxRight = search(root.right);
        int both = maxLeft[0] + maxRight[0] + 1;
        return Math.max(Math.max(maxLeft[1], maxRight[1]), both) - 1;
    }
    
    //Count and return both the longest straight (can be added to) and the longest non-straight
    private int[] search(TreeNode node) {
        if (node == null) {
            return new int[]{0,0};
        }

        int[] left = search(node.left);
        int[] right = search(node.right);
        
        return new int[]{Math.max(left[0], right[0]) + 1, 
                         Math.max(Math.max(left[0] + right[0] + 1, left[1]), right[1])};
        
        
    }
}

//Sum of Deepest Leaves
//Given the root of a binary tree, return the sum of values of its deepest leaves.
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int deepestLeavesSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        
        int sum = 0;

        while (!queue.isEmpty()) {
            int nodesInCurrentLevel = queue.size();
            // do some logic here for the current level
            sum = 0;

            for (int i = 0; i < nodesInCurrentLevel; i++) {
                TreeNode node = queue.remove();

                // do some logic here on the current node
                sum += node.val;

                // put the next level onto the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
        }
        
        return sum;
    }
}

//Binary Tree Zigzag Level Order Traversal
//Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> lli = new ArrayList();
        if (root == null) {
            return lli;
        }
        queue.add(root);
        

        while (!queue.isEmpty()) {
            int nodesInCurrentLevel = queue.size();
            // do some logic here for the current level
            List<Integer> levelList = new ArrayList();

            for (int i = 0; i < nodesInCurrentLevel; i++) {
                TreeNode node = queue.remove();

                // do some logic here on the current node
                levelList.add(node.val);

                // put the next level onto the queue
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            if (lli.size() % 2 != 0) {
                Collections.reverse(levelList);
            }
            lli.add(levelList);
        }
        
        return lli;
    }
}

//Insert into a Binary Search Tree
//You are given the root node of a binary search tree (BST) and a value to insert into the tree. 
//Return the root node of the BST after the insertion. 
//It is guaranteed that the new value does not exist in the original BST.
//TODO

//Find if Path Exists in Graph
//There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). 
//The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. 
//Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
//Determine if there is a valid path that exists from vertex source to vertex destination.
class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        if (edges.length == 0) {
            return true;
        }
        Map<Integer, List<Integer>> graph = new HashMap();
        
        for (int i = 0; i < edges.length; i++) {
            if (!graph.containsKey(edges[i][0])) {
                graph.put(edges[i][0], new ArrayList());
            }
            if (!graph.containsKey(edges[i][1])) {
                graph.put(edges[i][1], new ArrayList());
            }
            graph.get(edges[i][0]).add(edges[i][1]);
            graph.get(edges[i][1]).add(edges[i][0]);
        }
        
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            return false;
        }
        return findDestination(graph, graph.remove(source), destination);
    }
    
    private boolean findDestination(Map<Integer, List<Integer>> graph, List<Integer> values, int destination) {
        if (values.contains(destination)) {
            return true;
        }
        for (Integer value : values) {
            if (graph.containsKey(value)) {
                if (findDestination(graph, graph.remove(value), destination)) {
                    return true;
                }
            }
        }
        return false;
    }
}

//Number of Connected Components in Undirected Graph
//You have a graph of n nodes. You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
//Return the number of connected components in the graph.
class Solution {
    public int countComponents(int n, int[][] edges) {
        Set<Integer> seen = new HashSet();
        int total = 0;
        
        
        for (int i = 0; i < edges.length; i++) {
            if (!seen.contains(edges[i][0]) && !seen.contains(edges[i][1])) {
                total++;
                seeLinks(edges[i][0], edges[i][1], edges, seen);
            }
            seen.add(edges[i][0]);
            seen.add(edges[i][1]);
        }

        return total + (n-seen.size());
    }
    
    private void seeLinks(int a, int b, int[][] edges, Set<Integer> seen) {
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][0] == a || edges[i][0] == b || edges[i][1] == a || edges[i][1] == b) {
                if (!seen.contains(edges[i][0]) || !seen.contains(edges[i][1])) {   
                    seen.add(edges[i][0]);
                    seen.add(edges[i][1]);
                    seeLinks(edges[i][0], edges[i][1], edges, seen);
                }
            }
        }
    }
}

//Max area of island
// You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
// The area of an island is the number of cells with a value 1 in the island.
// Return the maximum area of an island in grid. If there is no island, return 0.
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        List<Map<Integer, List<Integer>>> land = new ArrayList();
        
        for (int y = 0; y < grid.length; y++) {
            xLoop: for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 1) {
                    int foundInA = -1;
                    int foundInB = -1;
                    for (int i = 0; i < land.size(); i++) {
                        Map<Integer, List<Integer>> map = land.get(i);
                        if (map.containsKey(y) && map.get(y).contains(x-1)) {
                            map.get(y).add(x);
                            foundInA = i;
                            break;
                        } else if (map.containsKey(y-1) && map.get(y-1).contains(x)) {
                            if (map.containsKey(y)) {
                                map.get(y).add(x);
                              } else {
                                map.put(y, new ArrayList());
                                map.get(y).add(x);
                              }
                            foundInB = i;
                            break;
                        }
                    }
                    if (foundInA > -1) {
                        Map<Integer, List<Integer>> oldMap = land.get(foundInA);
                        for (Iterator<Map<Integer, List<Integer>>> iterator = land.iterator(); iterator.hasNext();) {
                        Map<Integer, List<Integer>> map = iterator.next();
                            if (!map.equals(oldMap)) {
                                if (map.containsKey(y-1) && map.get(y-1).contains(x)) {
                                    for (Integer key : map.keySet()) {
                                        if (oldMap.containsKey(key)) {
                                            oldMap.get(key).addAll(map.get(key));
                                        } else {
                                            oldMap.put(key, new ArrayList());
                                            oldMap.get(key).addAll(map.get(key));
                                        }
                                    }
                                    iterator.remove();
                                }
                            }
                        }
                    } else if (foundInB > -1) {
                        Map<Integer, List<Integer>> oldMap = land.get(foundInB);
                        for (Iterator<Map<Integer, List<Integer>>> iterator = land.iterator(); iterator.hasNext();) {
                        Map<Integer, List<Integer>> map = iterator.next();
                            if (!map.equals(oldMap)) {
                                if (map.containsKey(y) && map.get(y).contains(x-1)) {
                                    for (Integer key : map.keySet()) {
                                        if (oldMap.containsKey(key)) {
                                            oldMap.get(key).addAll(map.get(key));
                                        } else {
                                            oldMap.put(key, new ArrayList());
                                            oldMap.get(key).addAll(map.get(key));
                                        }
                                    }
                                    iterator.remove();
                                }
                            }
                        }
                    }else {
                        Map<Integer, List<Integer>> newMap = new HashMap();
                        newMap.put(y, new ArrayList());
                        newMap.get(y).add(x);
                        land.add(newMap);
                    }
                }
            }
        }

        int max = 0;
        for (Map<Integer, List<Integer>> map : land) {
            int total = 0;
            for (List<Integer> list : map.values()) {
                total += list.size();
            }
            max = Math.max(max, total);
        }
        return max;
    }
}

//Minimum Genetic Mutation
//Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. 
//If there is no such a mutation, return -1.
class Pair {
    String node;
    int steps;
    Pair(String node, int steps) {
        this.node = node;
        this.steps = steps;
    }
}

class Solution {
    Map<Character, List<String>> lettersMap = new HashMap();
    List<String> bankList = new ArrayList();
    public int minMutation(String startGene, String endGene, String[] bank) {
        for (int i = 0; i < bank.length; i++) {
            bankList.add(bank[i]);
        }
        if (!bankList.contains(endGene)) {
            return -1;
        }
        if (startGene.equals(endGene)) {
            return 0;
        }
        lettersMap.put('A', Arrays.asList("C", "G", "T"));
        lettersMap.put('C', Arrays.asList("A", "G", "T"));
        lettersMap.put('G', Arrays.asList("C", "A", "T"));
        lettersMap.put('T', Arrays.asList("C", "G", "A"));
        Queue<Pair> queue = new LinkedList<>();
        Set<String> seen = new HashSet<>();
        queue.add(new Pair(startGene, 0));
        seen.add(startGene);
        
        while(!queue.isEmpty()) {
            Pair pair = queue.remove();
            String node = pair.node;
            int steps = pair.steps;
            if (node.equals(endGene)) {
                return steps;
            }
            
            for (String neighbor: neighbors(node)) {
                if (!seen.contains(neighbor)) {
                    seen.add(neighbor);
                    queue.add(new Pair(neighbor, steps + 1));
                }
            }
        }
        
        return -1;
    }
    
    public List<String> neighbors(String node) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (String letter : lettersMap.get(node.charAt(i))) {
                String mutation = node.substring(0, i) + letter + node.substring(i + 1);
                if (bankList.contains(mutation)) {
                    ans.add(mutation);
                }
            }
        }
        
        return ans;
    }
}

//Word Ladder
//Given two words, beginWord and endWord, and a dictionary wordList, 
//return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.
class Pair {
    String node;
    int steps;
    Pair(String node, int steps) {
        this.node = node;
        this.steps = steps;
    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        wordList.remove(beginWord);
        
        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            String node = pair.node;
            int steps = pair.steps;
            if (node.equals(endWord)) {
                return steps;
            }
            
            for (String neighbor: neighbors(node, wordList)) {
                queue.add(new Pair(neighbor, steps + 1));
            }
        }
        
        return 0;
    }
    
    public List<String> neighbors(String word, List<String> wordList) {
        List<String> ans = new ArrayList();
        for (String allowed : wordList) {
            int count = 0;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) != allowed.charAt(i)) {
                    count++;
                }
            }
            if (count == 1) {
                ans.add(allowed);
            }
        }
        wordList.removeAll(ans);
        return ans;
    }
}