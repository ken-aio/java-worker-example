package sample;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.greghaines.jesque.Config;
import net.greghaines.jesque.worker.MapBasedJobFactory;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerImpl;

public class SampleWoker {

    static final String PID = java.lang.management.ManagementFactory.getRuntimeMXBean().getName().split("@")[0];

    public static void main(String args[]) throws URISyntaxException {
        // Configuration
        final Config config = RedisConfig.createConfig();

        // Start a worker to run jobs from the queue
        Map<String, Class<?>> jobTypes = new HashMap<>();
        jobTypes.put("SampleJob", SampleJob.class);
        MapBasedJobFactory jobFactory = new MapBasedJobFactory(jobTypes);

        List<String> targetQueues = Arrays.asList("foo");
        final Worker worker = new WorkerImpl(config, targetQueues, jobFactory);

        final Thread workerThread = new Thread(worker);
        workerThread.start();
        System.out.println("start job woker: " + PID);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("shutdown process is running now...");
            worker.end(true);
            try {
                workerThread.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("shutdown done");
        }));

        // Enqueue more jobs, etc.
    }
}
