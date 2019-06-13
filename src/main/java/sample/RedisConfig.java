package sample;

import java.net.URI;
import java.net.URISyntaxException;

import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;

public class RedisConfig {

    public static Config createConfig() throws URISyntaxException {
        final ConfigBuilder configBuilder = new ConfigBuilder();
        URI redisUrl = new URI(System.getProperty("REDIS_PROVIDER", "127.0.0.1"));
        String redisHost = redisUrl.getHost();
        int redisPort = redisUrl.getPort();
        String redisUserInfo = redisUrl.getUserInfo();
        if (redisHost != null) {
            configBuilder.withHost(redisHost);
        }
        if (redisPort > -1) {
            configBuilder.withPort(redisPort);
        }
        if (redisUserInfo != null) {
            configBuilder.withPassword(redisUserInfo.split(":", 2)[1]);
        }
        return configBuilder.build();
    }
}
