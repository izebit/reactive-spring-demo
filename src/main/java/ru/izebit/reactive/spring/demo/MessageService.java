package ru.izebit.reactive.spring.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author <a href="mailto:izebit@gmail.com">Artem Konovalov</a> <br/>
 * Date: 10.01.2021
 */
@Service
@RequiredArgsConstructor
public class MessageService {
    private final ChatRepository chatRepository;

    public Flux<Message> getAllByTopic(String topic) {
        return chatRepository.findAllByTopic(topic);
    }

    public Mono<Message> save(final Message message) {
        return chatRepository.save(message);
    }
}
