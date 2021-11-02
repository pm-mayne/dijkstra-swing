package backend.model.api;

import backend.model.solver.*;

import java.util.List;
import java.util.stream.Collectors;

public class SolverAPI {

    public Result solve(List<String> inputGraph, String sourceVertexName, String destinationVertexName, String separator, ALGORITHM algorithm) {
        switch(algorithm) {
            case A_STAR:
            case BELLMAN:
                return error("Error: this algorithm is not yet implemented");
            case DIJKSTRA:
                return solveDijkstra(inputGraph, sourceVertexName, destinationVertexName, separator);
        }
        return error("Error: Missing input: Algorithm type.");
    }

    private Result solveDijkstra(List<String> inputGraph, String sourceVertexName, String destinationVertexName, String separator) {
        List<Edge> edges = inputGraph.stream()
                .map(line -> line.split(separator))
                .map(spl -> new Edge(new Vertex(spl[0]), new Vertex(spl[1]), Integer.parseInt(spl[2])))
                .collect(Collectors.toList());

        Graph graph = new Graph(edges);

        Dijkstra dijkstra = new Dijkstra(graph, new Vertex(sourceVertexName), new Vertex(destinationVertexName));
        Path result = dijkstra.solve();
        return result(result.toString());
    }

    private Result result(String s) {
        System.out.println(s);
        return new Result(s, false);
    }

    private Result error(String s) {
        System.err.println(s);
        return new Result(s, true);
    }
}
