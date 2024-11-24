package ca.bytube._09_graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Graph<V,E> {

    public Graph() {}

    public Graph(WeightManager<E> weightmanager) {
        this.weightManager = weightmanager;

    }
    protected WeightManager<E> weightManager;

    public interface WeightManager<E>{
        public int compare(E w1, E w2);

        public E add(E w1, E w2);

        public E zero();

    }
    abstract public int verticesSize();

    abstract public int edgesSize();

    abstract public void addVertex(V v);
    abstract public void removeVertex(V v);

    abstract public void addEdge(V fromV, V toV);
    abstract public void addEdge(V fromV, V toV, E weight);
    abstract public void removeEdge(V fromV, V toV);

    abstract Map<V,E> shortestPath(V begin);
    abstract Map<V,PathInfo<V,E>> shortestPathwithPathInfo(V begin);

    abstract void bfs(V begin);

    abstract void dfs(V begin);

    abstract Set<EdgeInfo<V,E>> mst();

    abstract List<V> topologicalSort(V begin);

    public static abstract class VertexVisitor<V> {
        public abstract boolean visit(V val);
    }

    void bfs(V begin, VertexVisitor<V> visit) {}

    public static class EdgeInfo<V, E> {
        E weight;
        V from;
        V to;

        public EdgeInfo(E weight, V from, V to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }
    }

    public static class PathInfo<V,E> {
        public E weight;
        public List<EdgeInfo<V,E>> edgeinfos = new LinkedList<>();
        public PathInfo() {}

        public PathInfo(E weight) {
            this.weight = weight;
        }
    }
}
