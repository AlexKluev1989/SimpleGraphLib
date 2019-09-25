package org.simplegraph;

import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.simplegraph.model.Edge;
import org.simplegraph.model.Graph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Basic test class with tests not graph type specific.
 */
public abstract class AbstractSimpleGraphTest {
    protected Logger logger = Logger.getLogger(getClass());

    /**
     * Create and return test graph as:
     * <pre>
     * 1 ---> 2 ---> 6 ---> 7
     *  \    /
     *   \ /               5
     *    v
     *    3 ---> 4
     *</pre>
     * Edge direction (or indirection) depends on graph implementation
     * @return test graph
     */
    AbstractSimpleGraph<Integer> createTestGraph() {
        AbstractSimpleGraph<Integer> graph = createGraphInstance();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 6);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(6, 7);
        graph.addVertex(5);
        return graph;
    }

    abstract AbstractSimpleGraph<Integer> createGraphInstance();

    @Test
    void addNewVertex() {
        AbstractSimpleGraph<Integer> graph = createTestGraph();
        int currentCount = graph.getVerticesCount();
        boolean result = graph.addVertex(Integer.MAX_VALUE);
        assertTrue(result);
        assertEquals(currentCount + 1, graph.getVerticesCount());
    }

    @Test
    void addAlreadyExistedVertex() {
        AbstractSimpleGraph<Integer> graph = createTestGraph();
        int currentCount = graph.getVerticesCount();
        graph.addVertex(1);
        boolean result = graph.addVertex(1);
        assertFalse(result);
        assertEquals(currentCount, graph.getVerticesCount());
    }

    @Test
    void addEdgeWithExistedVertices() {
        AbstractSimpleGraph<Integer> graph = createTestGraph();
        int oldEdgeCount = graph.getEdgesCount();
        int oldVerticesCount = graph.getVerticesCount();
        graph.addEdge(5, 7);
        assertEquals(oldEdgeCount + 1, graph.getEdgesCount());
        assertEquals(oldVerticesCount, graph.getVerticesCount());
    }

    @Test
    void addEdgeWithOneNewVertex() {
        AbstractSimpleGraph<Integer> graph = createTestGraph();
        int oldEdgeCount = graph.getEdgesCount();
        int oldVerticesCount = graph.getVerticesCount();
        graph.addEdge(1, Integer.MAX_VALUE);
        assertEquals(oldEdgeCount + 1, graph.getEdgesCount());
        assertEquals(oldVerticesCount + 1, graph.getVerticesCount());
    }

    @Test
    void addEdgeWithBothNewVertices() {
        AbstractSimpleGraph<Integer> graph = createTestGraph();
        int oldEdgeCount = graph.getEdgesCount();
        int oldVerticesCount = graph.getVerticesCount();
        graph.addEdge(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(oldEdgeCount + 1, graph.getEdgesCount());
        assertEquals(oldVerticesCount + 2, graph.getVerticesCount());
    }

    void addAlreadyExistedEdge(Integer v1, Integer v2) {
        AbstractSimpleGraph<Integer> graph = createTestGraph();
        int oldEdgeCount = graph.getEdgesCount();
        int oldVerticesCount = graph.getVerticesCount();
        boolean result = graph.addEdge(v1, v2);
        assertFalse(result);
        assertEquals(oldEdgeCount, graph.getEdgesCount());
        assertEquals(oldVerticesCount, graph.getVerticesCount());
    }

    void testExistedPath(Integer startV, Integer endV) {
        assertNotEquals(startV, endV);

        Graph<Integer> graph = createTestGraph();
        List<Edge<Integer>> path = graph.getPath(startV, endV);
        assertFalse(path.isEmpty());
        assertEquals(startV, path.get(0).getV1());
        assertEquals(endV, path.get(path.size() - 1).getV2());
        logger.info("Found path: " + path);
    }

    void testNonexistentPath(Integer startV, Integer endV) {
        Graph<Integer> graph = createTestGraph();
        List<Edge<Integer>> path = graph.getPath(startV, endV);
        assertTrue(path.isEmpty());
    }
}
