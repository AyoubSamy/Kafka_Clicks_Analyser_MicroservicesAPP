package net.ayoub;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("application.id", "kafka-streams-app");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("default.key.serde", "org.apache.kafka.common.serialization.Serdes$StringSerde");
        props.put("default.value.serde", "org.apache.kafka.common.serialization.Serdes$StringSerde");

        StreamsBuilder builder = new StreamsBuilder();

        KStream<String,String> ClicksStrem = builder.stream("click");


        KGroupedStream<String,String> groupedStream = ClicksStrem.groupBy((key, value) ->value.split(",")[0] );
        KTable<String, Long> clickCounts = groupedStream.count(
                Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("clicks-store")
        );

        clickCounts
                .toStream() // 1. Convertit la Table en Flux (userId, total)
                .to("click-counts", Produced.with(Serdes.String(), Serdes.Long())); // 2. Envoie vers Kafka

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        // Graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}