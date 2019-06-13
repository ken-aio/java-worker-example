package sample;

import java.net.URISyntaxException;
import java.util.Arrays;

import net.greghaines.jesque.Job;
import net.greghaines.jesque.client.Client;
import net.greghaines.jesque.client.ClientImpl;

public class SampleEnqueuer {

    public static void main(String args[]) throws URISyntaxException {
        // Add a job to the queue
        final Job job = new Job("SampleJob", 10);
        final Client client = new ClientImpl(RedisConfig.createConfig());
        client.enqueue("foo", job);
        client.end();
    }
}
