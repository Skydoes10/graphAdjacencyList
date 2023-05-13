package graph;

import java.util.*;

public class GraphAdjacencyList<T> implements IGraph<T> {
    private final ArrayList<Vertex<T>> vertices;
    private final boolean directed;
    private int time;

    public GraphAdjacencyList(boolean directed) {
        this.vertices = new ArrayList<>();
        this.directed = directed;
    }

    @Override
    public void addVertex(T vertex) {
        if (getVertex(vertex) != null) {
            throw new IllegalArgumentException("Vertex already exists");
        }

        this.vertices.add(new Vertex<>(vertex));
    }

    @Override
    public void addEdge(T source, T destination) {
        Vertex<T> vertex1 = getVertex(source);
        Vertex<T> vertex2 = getVertex(destination);

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        if (vertex1.getAdjacent().contains(vertex2)) {
            throw new IllegalArgumentException("Edge already exists");
        }

        vertex1.addAdjacent(vertex2);
        if (!this.directed) {
            vertex2.addAdjacent(vertex1);
        }
    }

    @Override
    public void removeVertex(T vertex) {
        Vertex<T> v = getVertex(vertex);
        if (v == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        this.vertices.remove(v);
        for (Vertex<T> u : this.vertices) {
            u.removeAdjacent(v);
        }
    }

    @Override
    public void removeEdge(T source, T destination) {
        Vertex<T> vertex1 = getVertex(source);
        Vertex<T> vertex2 = getVertex(destination);

        if (vertex1 == null || vertex2 == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        if (!vertex1.getAdjacent().contains(vertex2)) {
            throw new IllegalArgumentException("Edge does not exist");
        }

        vertex1.removeAdjacent(vertex2);
        if (!this.directed) {
            vertex2.removeAdjacent(vertex1);
        }
    }

    @Override
    public void BFS(T start) {
        Vertex<T> s = getVertex(start);
        if (s == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        for (Vertex<T> u : this.vertices) {
            if (!u.equals(s)) {
                u.setColor("white");
                u.setDistance(Integer.MAX_VALUE);
                u.setParent(null);
            }
        }

        s.setColor("gray");
        s.setDistance(0);
        s.setParent(null);

        Queue<Vertex<T>> queue = new LinkedList<>();
        queue.offer(s);

        while (!queue.isEmpty()) {
            Vertex<T> u = queue.poll();
            for (Vertex<T> v : u.getAdjacent()) {
                if (v.getColor().equals("white")) {
                    v.setColor("gray");
                    v.setDistance(u.getDistance() + 1);
                    v.setParent(u);
                    queue.add(v);
                }
            }
            u.setColor("black");
        }
    }

    @Override
    public void DFS(T start) {
        Vertex<T> s = getVertex(start);
        if (s == null) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        for (Vertex<T> u : this.vertices) {
            u.setColor("white");
            u.setParent(null);
        }

        this.time = 0;

        DFSVisit(s);
    }

    private void DFSVisit(Vertex<T> start) {
        this.time++;
        start.setDiscoveryTime(this.time);
        start.setColor("gray");

        for (Vertex<T> u : start.getAdjacent()) {
            if (u.getColor().equals("white")) {
                u.setParent(start);
                DFSVisit(u);
            }
        }

        start.setColor("black");
        this.time++;
        start.setFinishingTime(this.time);
    }

    private Vertex<T> getVertex(T value) {
        for (Vertex<T> u : this.vertices) {
            if (u.getValue().equals(value)) {
                return u;
            }
        }
        return null;
    }

    public boolean isDirected() {
        return this.directed;
    }

    public ArrayList<Vertex<T>> getVertices() {
        return this.vertices;
    }
}
