package org.simplegraph;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation for Simple Graph (Undirected)
 * @param <T> - user defined vertex type
 */
public class SimpleUndirectedGraph<T> extends AbstractSimpleGraph<T> {
    @Override
    protected boolean isEdgeExists(T source, T target) {
        if (!getVertices().contains(source) || !getVertices().contains(target)) {
            return false;
        }
        return getEdges().contains(new SimpleEdge<T>(source, target)) || getEdges().contains(new SimpleEdge<T>(target, source));
    }

    @Override
    protected List<T> getAdjacentVertices(T vertex) {
        if (!getVertices().contains(vertex)) {
            throw new IllegalArgumentException("Graph doesn't contain vertex " + vertex);
        }
        return getEdges().stream()
                .filter(e ->vertex.equals( e.getV1()) || vertex.equals(e.getV2()))
                .map(e ->vertex.equals(e.getV1()) ? e.getV2() : e.getV1())
                .collect(Collectors.toList());
    }
}
