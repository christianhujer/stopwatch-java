package com.nelkinda.stopwatch;

import java.time.Duration;

/** Result of measuring the duration of a {@link java.util.concurrent.Callable}.
 * @param <T> Return type of the {@code callable}
 * @author <a href="mailto:Christian.Hujer@nelkinda.com">Christian Hujer</a>
 */
public interface TimerResult<T> {

    /** Returns the time the {@code callable} took to execute.
     * @return execution time of the {@code callable} in nanoseconds.
     */
    Duration getDuration();

    /** Returns the result of the {@code callable}.
     * You should call {@link #getException()} first before inspecting the result.
     * @return The result of the {@code callable}.
     */
    T getResult();

    /** Returns the exception thrown by the {@code callable}.
     * @return The exception thrown by the {@code callable}.
     */
    Exception getException();
}
