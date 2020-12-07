package uet.oop.bomberman.ai;

import java.util.*;

public class bfsMatrix {
    private int V; // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency Lists

    bfsMatrix(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public List<Integer> findMinimunMove(int[][] matrix) {
        int s = 0; // source
        int d = 0; //destination

        int m = matrix.length;
        int n = matrix[0].length;
        bfsMatrix bfs = new bfsMatrix(V);

        int k = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) { // 0 la ko di dc , 1 source, 2 la destination
                    if (isSafe(i, j + 1, m, n, matrix)) {
                        bfs.addEdge(k, k + 1);
                    }
                    if (isSafe(i, j - 1, m, n, matrix)) {
                        bfs.addEdge(k, k-1);
                    }
                    if (j < n-1 && isSafe(i+1, j, m, n, matrix)) {
                        bfs.addEdge(k, k+n);
                    }
                    if (i > 0 && isSafe(i-1, j, m, m, matrix)) {
                        bfs.addEdge(k, k-n);
                    }
                }
                if (matrix[i][j] == 1) {
                    s = k;
                } else if (matrix[i][j] == 2) {
                    d = k;
                }
                k++;
            }
        }
        return bfs(s, d, bfs);
    }

    List<Integer> bfs(int s, int d, bfsMatrix matrix) {
        if (s == d) {
            return null;
        }
        int[] level = new int[V];
        for (int i = 0; i < V; i++) {
            level[i] = -1;
        }
        Queue<Integer> queue = new LinkedList<>();
        level[s] = 0;
        queue.add(s);

        while (!queue.isEmpty()) {
            s = queue.poll();
            Iterator<Integer> i = matrix.adj[s].listIterator();
            while (i.hasNext()) {
                int tmp = i.next();
                if (level[tmp] < 0 || level[tmp] > level[s] + 1) {
                    level[tmp] = level[s]+1;
                    queue.add(tmp);
                }
            }
        }
        if (level[d] != -1) {
            List<Integer> result = new ArrayList<>();
            result.add(d);
            int k = level[d] - 1;
            int m = d;
            while(k >= 0) {
                Iterator<Integer> i = matrix.adj[m].listIterator();
                while (i.hasNext()) {
                    int tmp = i.next();
                    if (level[tmp] == k) {
                        result.add(tmp);
                        k--;
                        m = tmp;
                    }
                }
            }
            return result;
        }
        return null;
    }

    public boolean isSafe(int i, int j, int m, int n, int[][] matrix) {
        if (i < 0 || i >= m || j < 0 || j >= n || matrix[i][j] == 0) {
            return false;
        }
        return true;
    }
}


