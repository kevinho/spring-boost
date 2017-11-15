package com.ihuanqu.springkit;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by kvh on 2017/8/24.
 */
@Slf4j
public class RedisQueue implements Runnable {

    public interface QueueMessageConsumer {
        void onReceiveMessage(String key, String value);
    }

    private JedisPool jedisPool;
    private String key;
    private String processKey;
    private boolean running;
    private QueueHelper helper;
    private final static String PROCESS_KEY_PREFIX = ":proq";
    private final Executor executor = Executors.newCachedThreadPool();

    public RedisQueue(JedisPool jedisPool, String key) {
        this.jedisPool = jedisPool;
        this.key = key;
        this.processKey = key + PROCESS_KEY_PREFIX;
        helper = new QueueHelper();
    }

    synchronized public RedisQueue startConsumer() {
        if (this.running) {
            return this;
        }
        running = true;
        Thread thread = new Thread(this);
        thread.start();
        return this;
    }

    synchronized public void stopConsumer() {
        running = false;
    }

    private List<QueueMessageConsumer> consumers = new ArrayList<>();

    public void registerConsumer(QueueMessageConsumer consumer) {
        if (consumers.indexOf(consumer) < 0) {
            consumers.add(consumer);
        }
    }

    public void sendMessage(String value) {
        helper.lpush(key, value);
    }

    public void markFinish(String value) {
        helper.lrem(processKey, value);
    }

    @Override
    public void run() {
        while (running) {
            String value = helper.brpoplpush(key, processKey, 5);

            log.debug("key:{} value:{}", key, value);

            //post this msg in thread pool
            executor.execute(() -> {
                if (value != null && consumers.size() > 0) {
                    consumers.forEach(listener -> {
                        listener.onReceiveMessage(key, value);
                    });
                }
            });
        }
    }

    private class QueueHelper {
        void lpush(String key, String value) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.lpush(key, value);
            }
        }

        String brpoplpush(String src, String dest, int timeout) {
            try (Jedis jedis = jedisPool.getResource()) {
                return jedis.brpoplpush(src, dest, timeout);
            }
        }

        void lrem(String key, String value) {
            try (Jedis jedis = jedisPool.getResource()) {
                jedis.lrem(key, 1, value);
            }
        }
    }
}
