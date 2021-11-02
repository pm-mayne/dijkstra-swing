package backend.model.solver;

import java.util.List;

public class Path {

    private final List<Vertex> vertices;

    public Path(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    @Override
    public String toString() {
        return "" + vertices;
    }
}
