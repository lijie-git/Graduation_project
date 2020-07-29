package consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class ThreadPool {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    private ThreadPool() {
    }

    private static ExecutorService executorService;

    private static void init() {
        if (executorService == null) {
            synchronized (ThreadPool.class) {
                if (executorService == null) {
                    executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), Runtime.getRuntime().availableProcessors() * 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(512),
                            new ThreadPoolExecutor.DiscardPolicy());
                }
            }
        }
    }

    public static void submit(Runnable runnable) {
        if (executorService == null) {
            init();
        }
        executorService.submit(runnable);
    }

    public static void execute(Runnable runnable) {
        if (executorService == null) {
            init();
        }
        executorService.execute(runnable);
    }

    public static <T> Future<T> submit(Callable<T> callable) {
        if (executorService == null) {
            init();
        }
        Future<T> future = executorService.submit(callable);
        return future;
    }
}
