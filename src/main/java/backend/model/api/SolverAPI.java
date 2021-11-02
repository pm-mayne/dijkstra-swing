package backend.model.api;

import backend.model.solver.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SolverAPI {

    public static Result solve(List<String> inputGraph, String sourceVertexName, String destinationVertexName, String separator, ALGORITHM algorithm) {
        if (sourceVertexName.isEmpty() || destinationVertexName.isEmpty() || inputGraph.isEmpty() || sourceVertexName.equals(destinationVertexName)) {
            return error("Input error");
        }
        switch (algorithm) {
            case A_STAR:
            case BELLMAN:
                return error("Error: this algorithm is not yet implemented");
            case DIJKSTRA:
                return solveDijkstra(inputGraph, sourceVertexName, destinationVertexName, separator);
        }
        return error("Error: Missing input: Algorithm type.");
    }

    private static Result solveDijkstra(List<String> inputGraph, String sourceVertexName, String destinationVertexName, String separator) {
        List<Edge> edges = inputGraph.stream()
                .map(line -> line.split(separator))
                .map(spl -> new Edge(new Vertex(spl[0]), new Vertex(spl[1]), Integer.parseInt(spl[2])))
                .collect(Collectors.toList());

        Graph graph = new Graph(edges);
        Vertex source = new Vertex(sourceVertexName);
        Vertex destination = new Vertex(destinationVertexName);
        System.out.println("-------------- Starting the Dijkstra Algorithm --------------");
        System.out.println("Graph: " + graph);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Source: " + source);
        System.out.println("Destination: " + destination);
        System.out.println("-------------------------------------------------------------");
        Dijkstra dijkstra = new Dijkstra(graph, source, destination);
        Path result = dijkstra.solve();
        System.out.println("------------------- Solver finished task --------------------");
        return result(result.toString());
    }

    private static Result result(String s) {
        System.out.println("Result: " + s);
        return new Result(s, false);
    }

    private static Result error(String s) {
        System.err.println(s);
        return new Result(s, true);
    }

    public static List<String> getVerticesFromGraphText(String text, String separator) {
        return Stream.of(text.split("\n"))
                .map(line -> line.split(separator))
                .filter(spl -> spl.length >= 2)
                .flatMap(spl -> Stream.of(spl[0], spl[1]))
                .distinct()
                .collect(Collectors.toList());
    }
}
