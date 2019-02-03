package com.nelkinda.stopwatch;

import java.time.Duration;

import static java.time.Duration.ofNanos;

class TimerResultImpl<T> implements TimerResult<T> {
    private final Duration duration;
    private final T result;
    private final Exception exception;

    TimerResultImpl(final long nanoseconds, final T result, final Exception exception) {
        this.duration = ofNanos(nanoseconds);
        this.result = result;
        this.exception = exception;
    }

    @Override
    public Duration getDuration() {
        return duration;
    }

    @Override
    public T getResult() {
        return result;
    }

    @Override
    public Exception getException() {
        return exception;
    }
}
