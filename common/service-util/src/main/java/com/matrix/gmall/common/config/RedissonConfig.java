package com.matrix.gmall.common.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * redisson配置信息
 */
@Data
@Configuration
@ConfigurationProperties("spring.redis") // 读取配置文件以spring.redis开头的 相当于每个的@Value()的操作
public class RedissonConfig {

    private String host;

    private String addresses;

    private String password;

    private String port;

    private int timeout = 3000;
    private int connectionPoolSize = 64;
    private int connectionMinimumIdleSize=10;
    private int pingConnectionInterval = 60000;
    private static String ADDRESS_PREFIX = "redis://";

    /**
     * 将当前的RedisClient注入到Spring容器中
     * 如果其他地方也需要的话 那么直接@Autowired就可以了
     *
     * 自动装配
     */
    @Bean
    RedissonClient redissonSingle() {
        //  创建一个对象
        Config config = new Config();
        //  判断地址是否为空
        if(StringUtils.isEmpty(host)){
            throw new RuntimeException("host is  empty");
        }
        //  配置服务 单节点 配置集群是config.useClusterServers() 上线之后再用集群
        SingleServerConfig serverConfig = config.useSingleServer()
                // redis://127.0.0.1:7181
                .setAddress(ADDRESS_PREFIX + this.host + ":" + port)
                .setTimeout(this.timeout)
                .setPingConnectionInterval(pingConnectionInterval)
                .setConnectionPoolSize(this.connectionPoolSize)
                .setConnectionMinimumIdleSize(this.connectionMinimumIdleSize);
        //  是否需要密码 redis.conf
        if(!StringUtils.isEmpty(this.password)) {
            serverConfig.setPassword(this.password);
        }
        // RedissonClient redisson = Redisson.create(config);
        return Redisson.create(config);
    }
}