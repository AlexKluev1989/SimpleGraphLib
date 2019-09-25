package org.simplegraph;

import org.simplegraph.model.Edge;

import java.util.Objects;

/**
 * Edge implementation as pair of two connected vertices
 * @param <T> - user defined vertex type
 */
public class SimpleEdge<T> implements Edge<T> {
    private final T v1;
    private final T v2;

    public SimpleEdge(T v1, T v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public T getV1() {
        return v1;
    }

    public T getV2() {
        return v2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleEdge<?> edge = (SimpleEdge<?>) o;
        return Objects.equals(v1, edge.v1) &&
                Objects.equals(v2, edge.v2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(v1, v2);
    }

    @Override
    public String toString() {
        return "(" + v1 + ", " + v2 + ')';
    }
}
