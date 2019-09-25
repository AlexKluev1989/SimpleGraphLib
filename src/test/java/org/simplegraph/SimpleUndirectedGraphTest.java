package org.simplegraph;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class SimpleUndirectedGraphTest extends AbstractSimpleGraphTest {
    @Override
    AbstractSimpleGraph<Integer> createGraphInstance() {
        return new SimpleUndirectedGraph<>();
    }

    @ParameterizedTest
    @CsvSource({"2, 1", "3, 1"})
    @Override
    void addAlreadyExistedEdge(Integer startV, Integer endV) {
        super.addAlreadyExistedEdge(startV, endV);
    }

    @ParameterizedTest
    @CsvSource({"1, 4", "1, 3"})
    @Override
    void testExistedPath(Integer startV, Integer endV) {
        super.testExistedPath(startV, endV);
    }

    @ParameterizedTest
    @CsvSource({"1, 5", "5, 3"})
    @Override
    void testNonexistentPath(Integer startV, Integer endV) {
        super.testNonexistentPath(startV, endV);
    }

}