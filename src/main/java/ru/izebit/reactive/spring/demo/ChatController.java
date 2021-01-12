package ru.izebit.reactive.spring.demo;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;

/**
 * @author <a href="mailto:izebit@gmail.com">Artem Konovalov</a> <br/>
 * Date: 10.01.2021
 */
@Controller
@AllArgsConstructor
public class ChatController {
    private final MessageService messageService;

    @GetMapping
    public String index() {
        return "index.html";
    }

    @PostMapping(path = "/chats/{topic}", consumes = APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Void>> store(@RequestBody Message message, @PathVariable("topic") String topic) {
        message.setCreationDate(LocalDateTime.now());
        message.setTopic(topic);
        message.setId(System.currentTimeMillis());

        return messageService.save(message)
                .thenReturn(ResponseEntity.ok().<Void>build())
                .onErrorReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping(value = "/chats/{topic}", produces = APPLICATION_NDJSON_VALUE)
    public Flux<Message> getAllByTopic(@PathVariable("topic") String topic) {
        return messageService.getAllByTopic(topic);
    }

}
