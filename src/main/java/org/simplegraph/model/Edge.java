package org.simplegraph.model;

/**
 * Interface to represent edge as two vertices container
 * @param <T>
 */
public interface Edge<T> {
    T getV1();
    T getV2();
}
