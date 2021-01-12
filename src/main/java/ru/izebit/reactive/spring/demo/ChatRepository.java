package ru.izebit.reactive.spring.demo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.Tailable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * @author <a href="mailto:izebit@gmail.com">Artem Konovalov</a> <br/>
 * Date: 11.01.2021
 */
@Repository
public interface ChatRepository extends ReactiveMongoRepository<Message, Long> {

    @Tailable
    Flux<Message> findAllByTopic(String topic);
}
