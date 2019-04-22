package com.mdove.android.base.network.threadpool;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by MDove on 2019/4/21.
 */
public class SSThreadPoolProvider {


    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_SECONDS = 10;

    public static final ExecutorService IMMEDIATE_EXECUTOR = new SSThreadPoolExecutor(0, Integer.MAX_VALUE,
            0L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>(),
            new SSThreadFactory("SS-immediate", SSThreadPriority.HIGH));

    public static final ExecutorService API_EXECUTOR = new SSThreadPoolExecutor(3, 3,
            KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new SSThreadFactory("SS-api"));

    public static final ExecutorService COMMON_EXECUTOR = new SSThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(),
            new SSThreadFactory("SS-common"));

    public static final ExecutorService BACKGROUND_EXECUTOR = Executors.newSingleThreadExecutor(new SSThreadFactory("SS-low", SSThreadPriority.LOW));

    private static final HandlerThread SINGLE_THREAD = new HandlerThread("SS-global-single-thread");

    static {
        SINGLE_THREAD.start();
    }

    private static Handler THREAD_HANDLER = new Handler(SINGLE_THREAD.getLooper());
    public static final Handler MAIN_HANDLER = new Handler(Looper.getMainLooper());

    public static ExecutorService getCommonExecutor() {
        return COMMON_EXECUTOR;
    }

    public static Future<?> immediateSubmit(Runnable r) {
        if (r != null) {
            return IMMEDIATE_EXECUTOR.submit(r);
        }

        return null;
    }

    public static Future<?> commonSubmit(Runnable r) {
        if (r != null) {
            return COMMON_EXECUTOR.submit(r);
        }

        return null;
    }

    public static Future<?> backgroundSubmit(Runnable r) {
        if (r != null) {
            return BACKGROUND_EXECUTOR.submit(r);
        }

        return null;
    }

    public static Future<?> apiSubmit(Runnable r) {
        if (r != null) {
            return API_EXECUTOR.submit(r);
        }

        return null;
    }

    public static Looper getThreadLooper() {
        return SINGLE_THREAD.getLooper();
    }

    public static void runInMain(Runnable r) {
        MAIN_HANDLER.post(r);
    }

    public static Handler getThreadHandler() {
        return THREAD_HANDLER;
    }
}
