package org.simplegraph;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Implementation for Simple Directed Graph
 * @param <T> - user defined vertex type
 */
public class SimpleDigraph<T> extends AbstractSimpleGraph<T> {

    @Override
    protected boolean isEdgeExists(T source, T target) {
        if (!getVertices().contains(source) || !getVertices().contains(target)) {
            return false;
        }
        return getEdges().contains(new SimpleEdge<T>(source, target));
    }

    protected List<T> getAdjacentVertices(T vertex) {
        if (!getVertices().contains(vertex)) {
            throw new IllegalArgumentException("Graph doesn't contain vertex " + vertex);
        }
        return getEdges().stream()
                .filter(e -> e.getV1().equals(vertex))
                .map(e -> e.getV2())
                .collect(Collectors.toList());
    }

}
