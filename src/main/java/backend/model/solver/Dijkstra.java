package backend.model.solver;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javafx.scene.input.KeyCode.Q;

public class Dijkstra implements Solver {

    private final Graph graph;
    private final Vertex source;
    private final Vertex destination;
    private final long upperBound;
    private final Map<Vertex, Long> distancesFromSource;
    private final Map<Vertex, Vertex> predecessors;
    private final int maxWeight;

    public Dijkstra(Graph graph, Vertex source, Vertex destination) {
        this.graph = graph;
        this.source = source;
        this.destination = destination;
        upperBound = getUpperBound(graph);
        distancesFromSource = graph.getVertices().stream()
                .collect(Collectors.toMap(Function.identity(), (a) -> upperBound));
        distancesFromSource.put(source, 0L);
        predecessors = new HashMap<>();
        maxWeight = 1 + graph.getEdges().stream()
                .map(Edge::getWeight)
                .max(Comparator.comparingInt(a -> a))
                .orElse(0);
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public Path solve() {
        List<Vertex> vertices = new ArrayList<>(graph.getVertices());
        while (!vertices.isEmpty()) {
            Vertex nearest = findNearest(vertices);
            vertices.remove(nearest);
            List<Vertex> neighboursOfNearest = getNeighboursOf(nearest);
            for (Vertex neighbour : neighboursOfNearest) {
                updateDistances(nearest, neighbour, getWeightOf(nearest, neighbour));
            }
        }
        List<Vertex> path = new ArrayList<>();
        Vertex curr = destination;
        while (curr != null) {
            path.add(curr);
            curr = predecessors.get(curr);
        }
        Collections.reverse(path);
        return new Path(path);
    }

    private int getWeightOf(Vertex v1, Vertex v2) {
        return graph.getEdges().stream()
                .filter(e -> e.getFrom() == v1 || e.getTo() == v1 && e.getFrom() == v2 || e.getTo() == v2)
                .findFirst()
                .orElse(new Edge(v1, v2, maxWeight))
                .getWeight();
    }

    private List<Vertex> getNeighboursOf(Vertex nearest) {
        return graph.getEdges().stream()
                .filter(e -> e.getFrom().equals(nearest) || e.getTo().equals(nearest))
                .flatMap(e -> Stream.of(e.getFrom(), e.getTo()))
                .collect(Collectors.toList());
    }

    private long getUpperBound(Graph graph) {
        return 1 + graph.getEdges().stream()
                .map(Edge::getWeight)
                .reduce(0, Integer::sum);
    }

    private Vertex findNearest(List<Vertex> vertices) {
        long min = upperBound + 1;
        Vertex nearest = null;
        for (Vertex v : vertices) {
            if (distancesFromSource.get(v) < min) {
                min = distancesFromSource.get(v);
                nearest = v;
            }
        }
        return nearest;
    }

    private void updateDistances(Vertex v1, Vertex v2, int weight) {
        if (distancesFromSource.get(v2) > distancesFromSource.get(v1) + weight) {
            distancesFromSource.put(v2, distancesFromSource.get(v1) + weight);
            predecessors.put(v2, v1);
        }
    }

}
