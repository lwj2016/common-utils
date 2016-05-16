package com.lwj.utils.time;

import java.util.concurrent.BlockingQueue;

/**
 * Created by lwj on 16/5/13.
 * Des:
 */
public enum TimeUnit {

    MILLISECONDS {
        public long toMillis(long d) {
            return d;
        }

        public long toSeconds(long d) {
            return d / (C3 / C2);
        }

        public long toMinutes(long d) {
            return d / (C4 / C2);
        }

        public long toHours(long d) {
            return d / (C5 / C2);
        }

        public long toDays(long d) {
            return d / (C6 / C2);
        }

        public long convert(long d, TimeUnit u) {
            return u.toMillis(d);
        }

        int excessNanos(long d, long m) {
            return 0;
        }
    },
    SECONDS {
        public long toMillis(long d) {
            return x(d, C3 / C2, MAX / (C3 / C2));
        }

        public long toSeconds(long d) {
            return d;
        }

        public long toMinutes(long d) {
            return d / (C4 / C3);
        }

        public long toHours(long d) {
            return d / (C5 / C3);
        }

        public long toDays(long d) {
            return d / (C6 / C3);
        }

        public long convert(long d, TimeUnit u) {
            return u.toSeconds(d);
        }

        int excessNanos(long d, long m) {
            return 0;
        }
    },
    MINUTES {
        public long toMillis(long d) {
            return x(d, C4 / C2, MAX / (C4 / C2));
        }

        public long toSeconds(long d) {
            return x(d, C4 / C3, MAX / (C4 / C3));
        }

        public long toMinutes(long d) {
            return d;
        }

        public long toHours(long d) {
            return d / (C5 / C4);
        }

        public long toDays(long d) {
            return d / (C6 / C4);
        }

        public long convert(long d, TimeUnit u) {
            return u.toMinutes(d);
        }

        int excessNanos(long d, long m) {
            return 0;
        }
    },
    HOURS {
        public long toMillis(long d) {
            return x(d, C5 / C2, MAX / (C5 / C2));
        }

        public long toSeconds(long d) {
            return x(d, C5 / C3, MAX / (C5 / C3));
        }

        public long toMinutes(long d) {
            return x(d, C5 / C4, MAX / (C5 / C4));
        }

        public long toHours(long d) {
            return d;
        }

        public long toDays(long d) {
            return d / (C6 / C5);
        }

        public long convert(long d, TimeUnit u) {
            return u.toHours(d);
        }

        int excessNanos(long d, long m) {
            return 0;
        }
    },
    DAYS {
        public long toMillis(long d) {
            return x(d, C6 / C2, MAX / (C6 / C2));
        }

        public long toSeconds(long d) {
            return x(d, C6 / C3, MAX / (C6 / C3));
        }

        public long toMinutes(long d) {
            return x(d, C6 / C4, MAX / (C6 / C4));
        }

        public long toHours(long d) {
            return x(d, C6 / C5, MAX / (C6 / C5));
        }

        public long toDays(long d) {
            return d;
        }

        public long convert(long d, TimeUnit u) {
            return u.toDays(d);
        }

        int excessNanos(long d, long m) {
            return 0;
        }
    };


    static final long MAX = Long.MAX_VALUE;

    /**
     * Scale d by m, checking for overflow.
     * This has a short name to make above code more readable.
     */
    static long x(long d, long m, long over) {
        if (d > over) return Long.MAX_VALUE;
        if (d < -over) return Long.MIN_VALUE;
        return d * m;
    }

    // To maintain full signature compatibility with 1.5, and to improve the
    // clarity of the generated javadoc (see 6287639: Abstract methods in
    // enum classes should not be listed as abstract), method convert
    // etc. are not declared abstract but otherwise act as abstract methods.

    /**
     * Convert the given time duration in the given unit to this
     * unit.  Conversions from finer to coarser granularities
     * truncate, so lose precision. For example converting
     * {@code 999} milliseconds to seconds results in
     * {@code 0}. Conversions from coarser to finer granularities
     * with arguments that would numerically overflow saturate to
     * {@code Long.MIN_VALUE} if negative or {@code Long.MAX_VALUE}
     * if positive.
     * <p>
     * <p>For example, to convert 10 minutes to milliseconds, use:
     * {@code TimeUnit.MILLISECONDS.convert(10L, TimeUnit.MINUTES)}
     *
     * @param sourceDuration the time duration in the given {@code sourceUnit}
     * @param sourceUnit     the unit of the {@code sourceDuration} argument
     * @return the converted duration in this unit,
     * or {@code Long.MIN_VALUE} if conversion would negatively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     */
    public long convert(long sourceDuration, TimeUnit sourceUnit) {
        throw new AbstractMethodError();
    }

    /**
     * Equivalent to {@code NANOSECONDS.convert(duration, this)}.
     *
     * @param duration the duration
     * @return the converted duration,
     * or {@code Long.MIN_VALUE} if conversion would negatively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @see #convert
     */
    public long toNanos(long duration) {
        throw new AbstractMethodError();
    }

    /**
     * Equivalent to {@code MICROSECONDS.convert(duration, this)}.
     *
     * @param duration the duration
     * @return the converted duration,
     * or {@code Long.MIN_VALUE} if conversion would negatively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @see #convert
     */
    public long toMicros(long duration) {
        throw new AbstractMethodError();
    }

    /**
     * Equivalent to {@code MILLISECONDS.convert(duration, this)}.
     *
     * @param duration the duration
     * @return the converted duration,
     * or {@code Long.MIN_VALUE} if conversion would negatively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @see #convert
     */
    public long toMillis(long duration) {
        throw new AbstractMethodError();
    }

    /**
     * Equivalent to {@code SECONDS.convert(duration, this)}.
     *
     * @param duration the duration
     * @return the converted duration,
     * or {@code Long.MIN_VALUE} if conversion would negatively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @see #convert
     */
    public long toSeconds(long duration) {
        throw new AbstractMethodError();
    }

    /**
     * Equivalent to {@code MINUTES.convert(duration, this)}.
     *
     * @param duration the duration
     * @return the converted duration,
     * or {@code Long.MIN_VALUE} if conversion would negatively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @see #convert
     * @since 1.6
     */
    public long toMinutes(long duration) {
        throw new AbstractMethodError();
    }

    /**
     * Equivalent to {@code HOURS.convert(duration, this)}.
     *
     * @param duration the duration
     * @return the converted duration,
     * or {@code Long.MIN_VALUE} if conversion would negatively
     * overflow, or {@code Long.MAX_VALUE} if it would positively overflow.
     * @see #convert
     * @since 1.6
     */
    public long toHours(long duration) {
        throw new AbstractMethodError();
    }

    /**
     * Equivalent to {@code DAYS.convert(duration, this)}.
     *
     * @param duration the duration
     * @return the converted duration
     * @see #convert
     * @since 1.6
     */
    public long toDays(long duration) {
        throw new AbstractMethodError();
    }

    /**
     * Utility to compute the excess-nanosecond argument to wait,
     * sleep, join.
     *
     * @param d the duration
     * @param m the number of milliseconds
     * @return the number of nanoseconds
     */
    abstract int excessNanos(long d, long m);

    /**
     * Performs a timed {@link Object#wait(long, int) Object.wait}
     * using this time unit.
     * This is a convenience method that converts timeout arguments
     * into the form required by the {@code Object.wait} method.
     * <p>
     * <p>For example, you could implement a blocking {@code poll}
     * method (see {@link BlockingQueue#poll BlockingQueue.poll})
     * using:
     * <p>
     * <pre> {@code
     * public synchronized Object poll(long timeout, TimeUnit unit)
     *     throws InterruptedException {
     *   while (empty) {
     *     unit.timedWait(this, timeout);
     *     ...
     *   }
     * }}</pre>
     *
     * @param obj     the object to wait on
     * @param timeout the maximum time to wait. If less than
     *                or equal to zero, do not wait at all.
     * @throws InterruptedException if interrupted while waiting
     */
    public void timedWait(Object obj, long timeout)
            throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNanos(timeout, ms);
            obj.wait(ms, ns);
        }
    }

    /**
     * Performs a timed {@link Thread#join(long, int) Thread.join}
     * using this time unit.
     * This is a convenience method that converts time arguments into the
     * form required by the {@code Thread.join} method.
     *
     * @param thread  the thread to wait for
     * @param timeout the maximum time to wait. If less than
     *                or equal to zero, do not wait at all.
     * @throws InterruptedException if interrupted while waiting
     */
    public void timedJoin(Thread thread, long timeout)
            throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNanos(timeout, ms);
            thread.join(ms, ns);
        }
    }

    /**
     * Performs a {@link Thread#sleep(long, int) Thread.sleep} using
     * this time unit.
     * This is a convenience method that converts time arguments into the
     * form required by the {@code Thread.sleep} method.
     *
     * @param timeout the minimum time to sleep. If less than
     *                or equal to zero, do not sleep at all.
     * @throws InterruptedException if interrupted while sleeping
     */
    public void sleep(long timeout) throws InterruptedException {
        if (timeout > 0) {
            long ms = toMillis(timeout);
            int ns = excessNanos(timeout, ms);
            Thread.sleep(ms, ns);
        }
    }

    public static final long C2 = 1L;
    public static final long C3 = C2 * 1000L;
    public static final long C4 = C3 * 60L;
    public static final long C5 = C4 * 60L;
    public static final long C6 = C5 * 24L;
}