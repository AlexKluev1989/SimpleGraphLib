package org.simplegraph;

import org.apache.log4j.Logger;
import org.simplegraph.model.Edge;
import org.simplegraph.model.Graph;
import org.simplegraph.util.StreamUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Base abstract implementation of {@link Graph}
 * @param <T> - user defined vertex type
 */
public abstract class AbstractSimpleGraph<T> implements Graph<T> {
    protected Logger logger = Logger.getLogger(getClass());

    private Set<T> vertices = new HashSet<>();
    private Set<SimpleEdge<T>> edges = new HashSet<>();

    public boolean addVertex(T t) {
        if (logger.isDebugEnabled()) {
            logger.debug("trying to add vertex " + t);
        }

        if (t == null) {
            throw new IllegalArgumentException("null is invalid vertex value");
        }
        return vertices.add(t);
    }

    public boolean addEdge(T source, T target) {
        if (logger.isDebugEnabled()) {
            logger.debug("trying to add edge (" + source + ", " + target + ")");
        }
        if (isEdgeExists(source, target)) {
            if (logger.isDebugEnabled()) {
                logger.debug("edge already present.");
            }
            return false;
        }

        addVertex(source);
        addVertex(target);
        return edges.add(new SimpleEdge<>(source, target));
    }

    /**
     *
     * @param source - edge's node
     * @param target - edge's node
     * @return true if edge is alredy present in graph, otherwise false
     */
    protected abstract boolean isEdgeExists(T source, T target);

    /**
     * @param vertex
     * @return list of adjacent vertices
     */
    protected abstract List<T> getAdjacentVertices(T vertex);

    public List<Edge<T>> getPath(T start, T end) {
        if (logger.isDebugEnabled()) {
            logger.debug("trying to find path from " + start + " to " + end);
        }
        List<T> verticesPath = findPathsUtilUsingDFS(start, end);
        return convertToEdgesPath(verticesPath);
    }

    /**
     * Method find first path using DFS(Depth First Search) recursive algorithm.
     * @param start - initial vertex
     * @param end - final vertex
     * @return non-empty list of vertices if path exists or empty list if path is not found.
     * @throws IllegalArgumentException if start or end vertex is null
     */
    private List<T> findPathsUtilUsingDFS(T start, T end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }

        if (start.equals(end)) {
            return Collections.emptyList();
        }

        Set<T> visited = new HashSet<>();
        List<T> path = new ArrayList<>();
        path.add(start);

        boolean result = findPathsUtilUsingDFSRecursion(start, end, visited, path);
        if (result) {
            return path;
        }
        return Collections.emptyList();
    }

    /**
     * recursive part pf DFS algorithm
     */
    private boolean findPathsUtilUsingDFSRecursion(T current, T end,
                                                     Set<T> visited,
                                                     List<T> localPathList) {

        if (logger.isTraceEnabled()) {
            logger.trace("current: " + current + ", end: " + end + " visited: " + visited + ", localPathList: " + localPathList);
        }

        // Mark the current node
        visited.add(current);

        if (current.equals(end)) {
            return true;
        }

        // Recur for all the vertices adjacent to current vertex
        for (T i :  getAdjacentVertices(current)) {
            if (!visited.contains(i)) {
                // store current vertex in path
                localPathList.add(i);
                boolean result = findPathsUtilUsingDFSRecursion(i, end, visited, localPathList);
                if (result) {
                    return true;
                }

                // Wrong way. Remove current vertex from path
                localPathList.remove(i);
            }
        }

        return false;
    }

    /**
     * Converts path as list of vertices to path as list of edges
     * @param verticesPath - path as list of vertices
     * @return path as list of edges
     */
    private List<Edge<T>> convertToEdgesPath(List<T> verticesPath) {
        if (verticesPath == null || verticesPath.isEmpty()) {
            return Collections.emptyList();
        }

        int len = verticesPath.size() - 1;
        Stream<Edge<T>> edgeStream = StreamUtils.zip(
                verticesPath.stream().limit(len),
                verticesPath.stream().skip(1),
                (v1, v2) -> new SimpleEdge(v1, v2));

        return edgeStream.collect(Collectors.toList());
    }

    public Set<T> getVertices() {
        return vertices;
    }

    public Set<SimpleEdge<T>> getEdges() {
        return edges;
    }

    protected int getVerticesCount() {
        return vertices.size();
    }

    protected int getEdgesCount() {
        return edges.size();
    }
}
