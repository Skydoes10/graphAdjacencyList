import graph.GraphAdjacencyList;
import graph.Vertex;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        GraphAdjacencyList<Integer> graph = new GraphAdjacencyList<>(false);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 4);
        graph.addEdge(2, 4, 7);
        graph.addEdge(3, 4, 3);
        graph.addEdge(4, 5, 1);
        graph.addEdge(1, 5, 20);

        graph.BFS(1);

        System.out.println("BFS:");
        for (Vertex<Integer> vertex : graph.getVertices()) {
            System.out.println(vertex.getValue() + " " + vertex.getColor() + " " + vertex.getDistance() + " " + vertex.getParent());
        }

        System.out.println();

        graph.DFS(1);

        System.out.println("DFS:");
        for (Vertex<Integer> vertex : graph.getVertices()) {
            System.out.println(vertex.getValue() + " " + vertex.getColor() + " " + vertex.getDiscoveryTime() + " " + vertex.getFinishingTime() + " " + vertex.getParent());
        }

        System.out.println();

        Map<Vertex<Integer>, Vertex<Integer>> dijkstra = graph.dijkstra(1);

        System.out.println("Dijkstra:");
        for (Map.Entry<Vertex<Integer>, Vertex<Integer>> entry : dijkstra.entrySet()) {
            System.out.println("Vertex: " + entry.getKey() + " Prev: " + entry.getValue());
        }

        System.out.println();

        Vertex<Integer>[][] prevMatrix = graph.floydWarshall();

        System.out.println("Floyd-Warshall:");
        for (Vertex<Integer>[] vertices : prevMatrix) {
            for (int i = 0; i < prevMatrix.length; i++) {
                System.out.print(vertices[i] + " ");
            }
            System.out.println();
        }
    }
}