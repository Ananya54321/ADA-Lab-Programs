import java.util.*;

public class Main {
    static class Edge {
        int destination;
        int weight;
        int shortestDistance; // To track shortest distance from the source

        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
            this.shortestDistance = Integer.MAX_VALUE; // Initialize with "infinity"
        }

        Edge(int destination, int weight, int shortestDistance) {
            this.destination = destination;
            this.weight = weight;
            this.shortestDistance = shortestDistance;
        }
    }

    private static Map<Integer, Integer> dijkstra(Map<Integer, List<Edge>> graph, int start) {
        // Initialize distances with infinity for each node
        Map<Integer, Integer> distances = new HashMap<>();
        for (int node : graph.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        // Priority queue to hold edges with minimum shortestDistance (min-heap)
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.shortestDistance));
        priorityQueue.offer(new Edge(start, 0, 0)); // Start node with distance 0

        while (!priorityQueue.isEmpty()) {
            Edge currentEdge = priorityQueue.poll();
            int currNode = currentEdge.destination;
            int currDist = currentEdge.shortestDistance;

            // If current distance is greater than the stored distance, skip
            if (currDist > distances.get(currNode)) continue;

            // Explore neighbors
            for (Edge edge : graph.get(currNode)) {
                int neighbor = edge.destination;
                int newDist = currDist + edge.weight;

                // Update distance if a shorter path is found
                if (newDist < distances.get(neighbor)) {
                    distances.put(neighbor, newDist);
                    priorityQueue.offer(new Edge(neighbor, edge.weight, newDist));
                }
            }
        }

        return distances;
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
            graph.get(source).add(new Edge(destination, weight));
            graph.get(destination).add(new Edge(source, weight));
        }

        System.out.println("Enter the source vertex: ");
        int start = sc.nextInt();

        Map<Integer, Integer> distances = dijkstra(graph, start);
        System.out.println("Shortest distances from source " + start + ": ");
        for (int node : distances.keySet()) {
            System.out.println("To node " + node + " - Distance: " + distances.get(node));
        }
    }
}
