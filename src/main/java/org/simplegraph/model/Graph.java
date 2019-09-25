package org.simplegraph.model;

import java.util.List;

/**
 * Base interface to represent Graph with basic operations
 * @param <T> - user specified type as graph vertex
 */
public interface Graph<T> {
    /**
     * adds vertex to the graph
     * @param t - vertex
     * @return true if vertex is added successfully, false if vertex is already present
     */
    boolean addVertex(T t);

    /**
     * adds edge (represented as two vertices) to the graph
     * @param v1 - edge's vertex
     * @param v2 - edge's vertex
     * @return true if edge is added successfully, false if edge is already present
     */
    boolean addEdge(T v1, T v2);

    /**
     * returns a list of edges between 2 vertices (path doesn’t have to be optimal)
     * @param start - initial vertex
     * @param end - final vertex
     * @return
     */
    List<Edge<T>> getPath(T start, T end);
}
