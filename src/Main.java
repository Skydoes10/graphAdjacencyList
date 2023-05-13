import graph.GraphAdjacencyList;
import graph.Vertex;

public class Main {
    public static void main(String[] args) {
        GraphAdjacencyList<Integer> graph = new GraphAdjacencyList<>(false);

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

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
    }
}