package org.example.consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.58.135:9092");
        props.put("group.id","gp-test-group");
        props.put("enable.auto.commit","true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset","earliest");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("gptest"));
        try {
            while(true) {
                ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofMillis(1000));
                consumerRecords.forEach(record -> {
                   System.out.println("offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value() + ", partition=" + record.partition());
               });
            }
        } finally {
            consumer.close();
        }
    }
}
