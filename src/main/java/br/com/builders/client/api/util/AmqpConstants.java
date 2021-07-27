package br.com.builders.client.api.util;

import static org.apache.logging.log4j.util.Strings.EMPTY;

public class AmqpConstants {
    public static final String AMQP_DEFAULT_DIRECT_EXCHANGE = EMPTY;
    public static final String AMQP_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String AMQP_DEAD_LETTER_ROUNTING_KEY = "x-dead-letter-routing-key";
    public static final String AMQP_QUEUE_MODE = "x-queue-mode";
    public static final String AMQP_QUEUE_MESSAGE_TTL = "x-message-ttl";
    public static final String AMQP_QUEUE_MESSAGE_DEATH_COUNT = "count";
    public static final String AMQP_QUEUE_MODE_LAZY_VALUE = "lazy";
}