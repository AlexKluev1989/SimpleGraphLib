package org.simplegraph.util;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Stream utils
 */
public class StreamUtils {

    /**
     * Method 'zipped', combined two streams together by smaller's length
     * @param leftStream - one of the streams
     * @param rightStream - another stream to combine with
     * @param combiner - BiFunction to combine element from leftStream with element from rightStream
     * @param <L> - left stream type
     * @param <R> - right stream type
     * @param <T> - result stream type
     * @return
     */
    public static <L, R, T> Stream<T> zip(Stream<L> leftStream, Stream<R> rightStream, BiFunction<L, R, T> combiner) {
        Spliterator<L> lefts = leftStream.spliterator();
        Spliterator<R> rights = rightStream.spliterator();
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<T>(Long.min(lefts.estimateSize(), rights.estimateSize()), lefts.characteristics() & rights.characteristics()) {
            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                return lefts.tryAdvance(left->rights.tryAdvance(right->action.accept(combiner.apply(left, right))));
            }
        }, leftStream.isParallel() || rightStream.isParallel());
    }
}
