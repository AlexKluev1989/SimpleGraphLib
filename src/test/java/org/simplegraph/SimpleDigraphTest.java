package org.simplegraph;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SimpleDigraphTest extends AbstractSimpleGraphTest {
    @Override
    AbstractSimpleGraph<Integer> createGraphInstance() {
        return new SimpleDigraph<>();
    }

    @ParameterizedTest
    @CsvSource({"1, 2", "1, 3"})
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
    @CsvSource({"4, 1", "5, 3"})
    @Override
    void testNonexistentPath(Integer startV, Integer endV) {
        super.testNonexistentPath(startV, endV);
    }
}