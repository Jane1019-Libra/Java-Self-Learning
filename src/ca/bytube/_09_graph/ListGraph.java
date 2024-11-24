package ca.bytube._09_graph;

import java.util.*;

public class ListGraph<V,E> extends Graph<V,E>{

    private Map<V, Vertex<V,E>> vertices = new HashMap<>();

    private Set<Edge<V,E>> edges = new HashSet<>();

    public ListGraph(WeightManager<E> weightmanager) {
        super(weightmanager);
    }

    private Comparator<Edge<V,E>> edgeComparator = new Comparator<>() {
        public int compare(Edge<V,E> o1, Edge<V,E> o2) {
            return weightManager.compare(o1.weight, o2.weight);
        }
    };

    private static class Vertex<V,E> {
        V value;
        Set<Edge<V,E>> inEdges = new HashSet<>();
        Set<Edge<V,E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value) &&
                    Objects.equals(inEdges, vertex.inEdges) &&
                    Objects.equals(outEdges, vertex.outEdges);
        }

        @Override
        public int hashCode() {
            return value == null ? 0: value.hashCode();
        }

    }

    private static class Edge<V, E> {
        Vertex<V,E> from;
        Vertex<V,E> to;

        E weight;

        public Edge(Vertex<V,E> from, Vertex<V,E> to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) &&
                    Objects.equals(to, edge.to) &&
                    Objects.equals(weight, edge.weight);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, weight);
        }

        public EdgeInfo<V,E> info() {
            return new EdgeInfo<>(weight, from.value, to.value);
        }
    }
    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<V,E>(v));
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V,E> vertex = vertices.remove(v);
        if (vertex == null) return;
        for (Iterator<Edge<V,E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();) {
            Edge<V,E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        for (Iterator<Edge<V,E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();) {
            Edge<V,E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void addEdge(V fromV, V toV) {
        addEdge(fromV, toV, null);
    }

    @Override
    public void addEdge(V fromV, V toV, E weight) {
        Vertex<V,E> fromVertex = vertices.get(fromV);
        if (fromVertex == null) {
            fromVertex = new Vertex<>(fromV);
            vertices.put(fromV, fromVertex);
        }
        Vertex<V,E> toVertex = vertices.get(toV);
        if (toVertex == null) {
            toVertex = new Vertex<>(toV);
            vertices.put(toV, toVertex);
        }

        Edge<V,E> edge = new Edge<>(fromVertex, toVertex);
        if (weight != null) edge.weight = weight;
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void removeEdge(V fromV, V toV) {
        Vertex<V,E> fromVertex = vertices.get(fromV);
        if (fromVertex == null) return;
        Vertex<V,E> toVertex = vertices.get(toV);
        if (toVertex == null) return;
        Edge<V,E> edge = new Edge<>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)) {
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }

    @Override
    Map<V, E> shortestPath(V begin) {
        return dijkstra(begin);
    }

    private Map<V, PathInfo<V, E>> bellmanFord(V begin) {
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<V,PathInfo<V,E>> selectedPaths= new HashMap<>();
        selectedPaths.put(beginVertex.value, new PathInfo<>(weightManager.zero()));
        for (int i = 0; i < vertices.size() - 1; i++) {
            for (Edge<V,E> edge: edges) {
                PathInfo<V,E> fromPath = selectedPaths.get(edge.from.value);
                if (fromPath == null) continue;
                relaxationbellman(edge, selectedPaths, fromPath);
            }
        }
        return selectedPaths;
    }

    private boolean relaxationbellman(Edge<V, E> edge, Map<V,PathInfo<V,E>> paths, PathInfo<V,E> fromPath){
        E newWeight = weightManager.add(fromPath.weight, edge.weight);

        PathInfo<V,E> oldPath = paths.get(edge.to.value);
        if (oldPath != null && weightManager.compare(newWeight, oldPath.weight) >= 0) {return false;}
        else {
            if (oldPath == null) {
                oldPath = new PathInfo<>();
                paths.put(edge.to.value, oldPath);
            } else {
                oldPath.edgeinfos.clear();
            }
            oldPath.weight = edge.weight;
            oldPath.edgeinfos.addAll(fromPath.edgeinfos);
            oldPath.edgeinfos.add(edge.info());
            paths.put(edge.to.value, oldPath);
            return true;
        }
    }

    Map<V, E> dijkstra(V begin) {
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<Vertex<V,E>,E> paths = new HashMap<>();
        Map<V,E> selectedPaths= new HashMap<>();
        // 1. 初始化 paths
        for (Edge<V,E> edge: beginVertex.outEdges) {
            paths.put(edge.to, edge.weight);
        }
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, E> minimum = getMinPath(paths);
            Vertex<V, E> minVertex = minimum.getKey();
            E minWeight = minimum.getValue();
            selectedPaths.put(minVertex.value, minWeight);
            paths.remove(minVertex);
            for (Edge<V, E> edge : minVertex.outEdges) {
                E newWeight = weightManager.add(minimum.getValue(), edge.weight);

                E oldWeight = paths.get(edge.to);
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0) {
                    paths.put(edge.to, newWeight);
                }
            }
        }
        return selectedPaths;
    }

    @Override
    Map<V, PathInfo<V, E>> shortestPathwithPathInfo(V begin) {
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;
        Map<Vertex<V,E>,PathInfo<V,E>> paths = new HashMap<>();
        Map<V,PathInfo<V,E>> selectedPaths= new HashMap<>();
        // 1. 初始化 paths
        for (Edge<V,E> edge: beginVertex.outEdges) {
            PathInfo<V,E> pathinfo = new PathInfo<>();
            pathinfo.weight = edge.weight;
            pathinfo.edgeinfos.add(edge.info());
            paths.put(edge.to, pathinfo);
        }
        while (!paths.isEmpty()) {
            Map.Entry<Vertex<V, E>, PathInfo<V,E>> minimum = getMinPathwithInfo(paths);
            Vertex<V, E> minVertex = minimum.getKey();
            PathInfo<V,E> minPath = minimum.getValue();
            selectedPaths.put(minVertex.value, minPath);
            paths.remove(minVertex);
            for (Edge<V, E> edge : minVertex.outEdges) {
                relaxation(edge,paths, minPath);
            }
        }
        return selectedPaths;
    }

    private void relaxation(Edge<V, E> edge, Map<Vertex<V,E>,PathInfo<V,E>> paths, PathInfo<V,E> minPath){
        E newWeight = weightManager.add(minPath.weight, edge.weight);

        PathInfo<V,E> oldPath = paths.get(edge.to);
        if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0) {
            PathInfo<V,E> pathInfo = new PathInfo<>();
            pathInfo.weight = edge.weight;
            pathInfo.edgeinfos.addAll(minPath.edgeinfos);
            pathInfo.edgeinfos.add(edge.info());
            paths.put(edge.to, pathInfo);
        }
    }

    private Map.Entry<Vertex<V,E>,PathInfo<V,E>> getMinPathwithInfo(Map<Vertex<V,E>,PathInfo<V,E>> paths) {
        Iterator<Map.Entry<Vertex<V,E>, PathInfo<V,E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V,E>, PathInfo<V,E>> minimum = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V,E>, PathInfo<V,E>> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue().weight, minimum.getValue().weight) < 0) {
                minimum = nextEntry;
            }
        }
        return minimum;
    }

    private Map.Entry<Vertex<V,E>,E> getMinPath(Map<Vertex<V,E>,E> paths) {
        Iterator<Map.Entry<Vertex<V,E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V,E>, E> minimum = iterator.next();
        while (iterator.hasNext()) {
            Map.Entry<Vertex<V,E>, E> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue(), minimum.getValue()) < 0) {
                minimum = nextEntry;
            }
        }
        return minimum;
    }

    @Override
    void bfs(V begin) {
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Queue<Vertex<V,E>> queue = new LinkedList<>();
        Set<Vertex<V,E>> visited = new HashSet<>();
        queue.offer(beginVertex);
        visited.add(beginVertex);
        while(!queue.isEmpty()) {
            Vertex<V,E> poll = queue.poll();
            System.out.println(poll.value);
            for (Edge<V,E> edge : poll.outEdges) {
                if (!visited.contains(edge.to)) {
                    queue.offer(edge.to);
                    visited.add(edge.to);
                }
            }
        }
    }

    void bfs(V begin, VertexVisitor<V> visit) {
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Queue<Vertex<V,E>> queue = new LinkedList<>();
        Set<Vertex<V,E>> visited = new HashSet<>();
        queue.offer(beginVertex);
        visited.add(beginVertex);
        while(!queue.isEmpty()) {
            Vertex<V,E> poll = queue.poll();
            if (visit.visit(poll.value)) break;
            for (Edge<V,E> edge : poll.outEdges) {
                if (!visited.contains(edge.to)) {
                    queue.offer(edge.to);
                    visited.add(edge.to);
                }
            }
        }
    }

    @Override
    void dfs(V begin) {
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        dfs(beginVertex, new HashSet<>());
    }

    void dfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V,E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Set<Vertex<V,E>> visited = new HashSet<>();
        Stack<Vertex<V,E>> stack = new Stack<>();
        stack.push(beginVertex);
        System.out.println(beginVertex);
        visited.add(beginVertex);

        while(!stack.isEmpty()) {
            Vertex<V,E> pop = stack.pop();
            for (Edge<V,E> edge: pop.outEdges) {
                if (!visited.contains(edge.to)) {
                    stack.push(edge.from);
                    stack.push(edge.to);

                    System.out.println(edge.to.value);
                    visited.add(edge.to);
                    // 为什么 break 因为不能去访问 vertex.outedge 的其他遍 要去访问边上的更深的点
                    break;
                }
            }
        }
    }

    void dfs(Vertex<V,E> beginVertex,  Set<Vertex<V,E>> visited) {
        System.out.println(beginVertex.value);
        visited.add(beginVertex);
        for (Edge<V,E> edge :beginVertex.outEdges) {
            if (!visited.contains(edge.to)) {
                dfs(edge.to, visited);
            }
        }
    }
    @Override
    Set<EdgeInfo<V, E>> mst() {
        return Prim();
    }

    private Set<EdgeInfo<V, E>> Prim() {
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        Set<Vertex<V,E>> addVertices = new HashSet<>();
        Iterator<Vertex<V,E>> iterator = vertices.values().iterator();
        Vertex<V,E> vertex = iterator.next();
        MinHeap<Edge<V,E>> minHeap = new MinHeap<>(vertex.outEdges, edgeComparator);
        while(!minHeap.isEmpty() && addVertices.size() < vertices.size()) {
            Edge<V, E> edge = minHeap.remove();
            if (addVertices.contains(edge.to)) continue;
            edgeInfos.add(edge.info());
            addVertices.add(edge.to);
            minHeap.addAll(edge.to.outEdges);
        }
        return edgeInfos;
    }

    private Set<EdgeInfo<V,E>> kruskal() {
        int edgeSize = edges.size() - 1;
        if (edgeSize == -1) return null;
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        MinHeap<Edge<V,E>> minHeap = new MinHeap<>(edges, edgeComparator);
        UnionFind<Vertex<V,E>> uf = new UnionFind<>();
        vertices.forEach((V v, Vertex<V,E> vertex)-> {
            uf.makeSet(vertex);
        });
        while(!minHeap.isEmpty() && edgeInfo.size() < vertices.size() - 1) {
            Edge<V,E> edge = minHeap.remove();
            if (uf.isSame(edge.to, edge.from)) continue;
            edgeInfos.add(edge.info());
            uf.union(edge.from, edge.to);
        }
        return edgeInfos;
    }
    List<V> topologicalSort(V begin) {
        Map<Vertex<V,E>, Integer> map = new HashMap<>();
        Queue<Vertex<V,E>> queue = new LinkedList<>();
        List<V> list = new LinkedList<>();
        vertices.forEach((V v, Vertex<V,E> vertex) -> {
            if (vertex.inEdges.size() == 0) queue.offer(vertex);
            else map.put(vertex, vertex.inEdges.size());
        });
        while(!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                Integer toIn = map.get(edge.to) - 1;
                if (toIn == 0) queue.offer(edge.to);
                else map.put(edge.to, toIn);
            }
        }
        return list;
    }

}
