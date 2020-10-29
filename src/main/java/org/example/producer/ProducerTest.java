package org.example.producer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.util.Properties;

public class ProducerTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.58.135:9092");

        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks","1");
        props.put("retries",3);
        props.put("batch.size", 16384);
        props.put("linger.ms",5);
        //一开是发送消息失败是应为此处缓冲区设置为5导致消息未发送
        props.put("buffer.memory",33554432);
        props.put("max.block.ms",3000);
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10 ; i++) {
            producer.send(new ProducerRecord<String, String>("gptest", Integer.toString(i), Integer.toString(i)));
            System.out.println("key---" + Integer.toString(i) + "value----" + Integer.toString(i) );
        }
        producer.close();
    }

}
