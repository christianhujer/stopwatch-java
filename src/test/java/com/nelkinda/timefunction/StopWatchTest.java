package com.nelkinda.timefunction;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static java.lang.Thread.sleep;
import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;

class StopWatchTest {

    private static void assertElapsedAtLeastMilliseconds(final Duration duration, final int minimumMilliseconds) {
        final Duration remainingTime = duration.minus(ofMillis(minimumMilliseconds));
        assertFalse(remainingTime.isNegative());
        assertFalse(remainingTime.isZero());
        System.err.println(remainingTime);
    }

    private void snooze(final int milliseconds) {
        try {
            sleep(milliseconds);
        } catch (final InterruptedException e) {
            throw new AssertionError(e);
        }
    }

    @Test
    void timeRunnable10milliseconds() {
        final Duration duration = StopWatch.duration(() -> snooze(10));
        assertElapsedAtLeastMilliseconds(duration, 10);
    }

    @Test
    void timeRunnableNanoseconds() {
        final Duration duration = StopWatch.duration(() -> snooze(0));
        assertElapsedAtLeastMilliseconds(duration, 0);
    }

    @Test
    void timeCallableNanoseconds() {
        final TimerResult<String> timerResult = StopWatch.duration(() -> "foo");
        assertElapsedAtLeastMilliseconds(timerResult.getDuration(), 0);
        assertEquals("foo", timerResult.getResult());
        assertNull(timerResult.getException());
    }

    @Test
    void timeCallableThrowing() {
        final Exception exception = new Exception();
        final TimerResult<String> timerResult = StopWatch.duration(() -> {
            throw exception;
        });
        assertElapsedAtLeastMilliseconds(timerResult.getDuration(), 0);
        assertEquals(exception, timerResult.getException());
        assertNull(timerResult.getResult());
    }
}
