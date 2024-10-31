import java.util.*;

public class ArticulationPoints {
    static int timer = 0;

    private static HashSet<Integer> findArticulationPoints(List<List<Integer>> graph) {
        HashSet<Integer> articulationPoints = new HashSet<>();
        int[] dfsDisc = new int[graph.size()];
        int[] lowestNeigh = new int[graph.size()];
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i]) {
                dfs(graph, i, -1, visited, dfsDisc, lowestNeigh, visited, articulationPoints);
            }
        }
        return articulationPoints;
    }

    private static void dfs(List<List<Integer>> graph, int curr, int parent, boolean[] visited, int[] dfsDisc,
            int[] lowestNeigh, boolean[] visited2, HashSet<Integer> articulationPoints) {
        visited[curr] = true;
        dfsDisc[curr] = lowestNeigh[curr] = timer++;
        int children = 0;
        for (int neighbour : graph.get(curr)) {
            if (neighbour == parent)
                continue;
            if (visited[neighbour]) {
                lowestNeigh[curr] = Math.min(lowestNeigh[curr], dfsDisc[neighbour]);
            } else {
                dfs(graph, neighbour, curr, visited, dfsDisc, lowestNeigh, visited2, articulationPoints);
                lowestNeigh[curr] = Math.min(lowestNeigh[curr], lowestNeigh[neighbour]);
                if (lowestNeigh[neighbour] >= dfsDisc[curr] && parent != -1) {
                    articulationPoints.add(curr);
                }
                children++;
            }
            if (parent == -1 && children > 1 && lowestNeigh[neighbour] >= dfsDisc[curr]) {
                articulationPoints.add(curr);
            }
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // graph in adjacency list representation
        List<List<Integer>> graph = new ArrayList<>();
        System.out.println("Enter the number of vertices: ");
        int n = sc.nextInt();
        System.out.println("Enter the number of edges: ");
        int edges = sc.nextInt();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        System.out.println("Enter the edges: (source) (destination) ");
        for (int i = 0; i < edges; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        HashSet<Integer> articulationPoints = findArticulationPoints(graph);
        if (!articulationPoints.isEmpty()) {
            System.out.println("Articulation points of the given graph are: ");
            for (int i : articulationPoints) {
                System.out.print(i + 1 + " ");
            }
            System.out.println();
        } else {
            System.out.println("The given graph does not have any articulation points.");
        }

    }

}
