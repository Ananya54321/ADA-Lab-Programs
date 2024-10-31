import java.util.*;

public class Prims {
    static class Edge implements Comparable<Edge> {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return this.weight - other.weight;
        }
    }

    private static void prims(Map<Integer, List<Edge>> graph, int start) {
        Set<Integer> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(-1, start, 0));

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int currentNode = edge.destination;

            if (visited.contains(currentNode)) {
                continue;
            }

            visited.add(currentNode);

            if (edge.source != -1) {
                mst.add(edge);
                totalWeight += edge.weight;
            }

            for (Edge neighbor : graph.get(currentNode)) {
                if (!visited.contains(neighbor.destination)) {
                    pq.offer(neighbor);
                }
            }
        }

        System.out.println("Minimum Spanning Tree (MST):");
        for (Edge e : mst) {
            System.out.println(e.source + " - " + e.destination + " : " + e.weight);
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<Integer, List<Edge>> graph = new HashMap<>();

        System.out.println("Enter the number of vertices: ");
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        System.out.println("Enter the number of edges: ");
        int edges = sc.nextInt();
        for (int i = 0; i < edges; i++) {
            System.out.println("Enter the source, destination and weight of edge " + (i + 1) + ": ");
            int source = sc.nextInt();
            int destination = sc.nextInt();
            int weight = sc.nextInt();
            graph.get(source).add(new Edge(source, destination, weight));
            graph.get(destination).add(new Edge(destination, source, weight));
        }

        System.out.println("Enter the source vertex: ");
        int start = sc.nextInt();

        prims(graph, start);
    }
}
