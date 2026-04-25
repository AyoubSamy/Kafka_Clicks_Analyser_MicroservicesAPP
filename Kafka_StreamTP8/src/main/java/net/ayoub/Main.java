package net.ayoub;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
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
        props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 1000);
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

        StreamsBuilder builder = new StreamsBuilder(); // creation de la topologie

        KStream<String,String> ClicksStrem = builder.stream("click");

        ClicksStrem
                // 1. Extraire la clé à partir de la valeur "userId,click"
                .selectKey((k, v) -> v.split(",")[0])
                // 2. Grouper par cette nouvelle clé
                .groupByKey(Grouped.with(Serdes.String(), Serdes.String()))
                .count()
                .toStream()
                .mapValues(value-> String.valueOf(value))
                // 3. Envoyer vers le topic de sortie
                .to("click-counts", Produced.with(Serdes.String(), Serdes.String()));



//
//        clickCounts
//                .toStream() // 1. Convertit la Table en Flux (userId, total)
//                .mapValues(value -> String.valueOf(value)) // On transforme le chiffre 2 en texte "2"
//                .to("click-counts", Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

        // Graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}