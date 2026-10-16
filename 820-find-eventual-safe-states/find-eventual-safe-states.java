import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        
        // 1. Create a reversed graph and an array to track in-degrees
        List<List<Integer>> adjRev = new ArrayList<>();
        int[] inDegree = new int[n];
        
        for (int i = 0; i < n; i++) {
            adjRev.add(new ArrayList<>());
        }
        
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                // Original edge: i -> neighbor
                // Reversed edge: neighbor -> i
                adjRev.get(neighbor).add(i);
                inDegree[i]++;
            }
        }
        
        // 2. Add all terminal nodes (nodes with 0 out-degree in the original graph)
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        
        // 3. Process the queue using BFS
        boolean[] isSafe = new boolean[n];
        while (!q.isEmpty()) {
            int node = q.poll();
            isSafe[node] = true;
            
            for (int neighbor : adjRev.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.offer(neighbor);
                }
            }
        }
        
        // 4. Collect all safe nodes in ascending order
        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (isSafe[i]) {
                safeNodes.add(i);
            }
        }
        
        return safeNodes;
    }
}
