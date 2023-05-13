package graph;

public interface IGraph<T> {

    void addVertex(T vertex);

    void addEdge(T vertex1, T vertex2);

    void removeVertex(T vertex);

    void removeEdge(T vertex1, T vertex2);

    void BFS(T start);

    void DFS(T start);
}
