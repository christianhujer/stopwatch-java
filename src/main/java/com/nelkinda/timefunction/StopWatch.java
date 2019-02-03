package com.nelkinda.timefunction;

import java.time.Duration;
import java.util.concurrent.Callable;

import static java.lang.System.nanoTime;
import static java.util.concurrent.Executors.callable;

/** A simple StopWatch in Java to measure the duration that something takes.
 * The StopWatch measures time in nanoseconds.
 * @author <a href="mailto:Christian.Hujer@nelkinda.com">Christian Hujer</a>
 */
public enum StopWatch {
    ;

    /** Measures the time it takes to run the specified {@code runnable}.
     * @param runnable Runnable of which to measure the execution time.
     * @return Duration it took to execute {@code runnable}, in nanoseconds.
     */
    public static Duration duration(final Runnable runnable) {
        return duration(callable(runnable)).getDuration();
    }

    /** Measures the time it takes to call the specified {@code callable}.
     * @param callable Callable of which to measure the execution time.
     * @param <V> Return type of the {@code callable}.
     * @return TimerResult to access the duration, result, and exception of the callable.
     */
    public static <V> TimerResult<V> duration(final Callable<V> callable) {
        final long start = nanoTime();
        V result = null;
        Exception exception = null;
        try {
            result = callable.call();
        } catch (final Exception e) {
            exception = e;
        }
        return new TimerResultImpl<>(nanoTime() - start, result, exception);
    }
}
