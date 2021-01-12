package ru.izebit.reactive.spring.demo;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author <a href="mailto:izebit@gmail.com">Artem Konovalov</a> <br/>
 * Date: 10.01.2021
 */

@Configuration
public class AppConfiguration {

    @Autowired
    public void prepare(ReactiveMongoOperations mongoOperations,
                        ChatRepository chatRepository) {
        mongoOperations.createCollection("messages",
                CollectionOptions.empty()
                        .maxDocuments(1_000)
                        .size(1024 * 8)
                        .capped()).block();
        chatRepository
                .insert(List.of(
                        Message.builder()
                                .author("Artem")
                                .creationDate(LocalDateTime.now())
                                .id(1)
                                .text("hello reactive world!")
                                .topic("general")
                                .build()
                ))

                .blockLast();
    }


    @Bean(initMethod = "start", destroyMethod = "stop")
    @Lazy(value = false)
    @SneakyThrows
    public MongodExecutable localMongo(@Value("${spring.data.mongodb.host}") String host,
                                       @Value("${spring.data.mongodb.port}") int port) {
        return MongodStarter
                .getDefaultInstance()
                .prepare(new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                        .net(new Net(host, port, Network.localhostIsIPv6()))
                        .build());
    }
}
