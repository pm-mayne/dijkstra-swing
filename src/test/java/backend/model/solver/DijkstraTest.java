package backend.model.solver;


import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;

public class DijkstraTest {

    @Test
    public void solveTest() {
        Vertex a = new Vertex("a");
        Vertex b = new Vertex("b");
        Vertex c = new Vertex("c");
        Vertex d = new Vertex("d");
        Vertex e = new Vertex("e");
        Vertex f = new Vertex("f");

        List<Edge> edges = asList(new Edge(a, b, 2),
                new Edge(a, c, 3),
                new Edge(a, d, 5),
                new Edge(b, c, 1),
                new Edge(b, e, 6),
                new Edge(c, d, 3),
                new Edge(c, f, 2),
                new Edge(d, e, 4),
                new Edge(d, f, 5),
                new Edge(e, f, 1));

        Graph graph = new Graph(edges);

        Dijkstra dijkstra = new Dijkstra(graph, a, f);
        Path result = dijkstra.solve();

        assert(result.getVertices().equals(asList(a, d, f)));
    }
}