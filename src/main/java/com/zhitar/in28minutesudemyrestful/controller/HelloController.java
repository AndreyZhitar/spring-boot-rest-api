package com.zhitar.in28minutesudemyrestful.controller;

import com.zhitar.in28minutesudemyrestful.domain.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.text.MessageFormat;

@RestController
@RequestMapping("hello-world")
@AllArgsConstructor
@Slf4j
public class HelloController {

    private final MessageSource messageSource;
    private final LocaleResolver localeResolver;

    @GetMapping
    public String helloWorld() {
        return "Hello, World!!!";
    }
    @GetMapping(value = "bean", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE})
    public ResponseEntity<?> helloWorldBean(@RequestHeader(name = "Accept") String accept) {
        Message message = new Message("Hello, World!!!");
        if (MediaType.TEXT_PLAIN_VALUE.equalsIgnoreCase(accept)) {
            return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(message.toString());
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping("path-var/{name}")
    public ResponseEntity<Message> helloWorldPath(@PathVariable String name) {
        return ResponseEntity.ok(new Message(MessageFormat.format("Hello, {0}", name)));
    }

    @GetMapping("internationalized")
    public ResponseEntity<String> helloWorldInternationalized() {
        log.info("Message source {}", messageSource);
        log.info("Locale resolver {}", localeResolver);
        return ResponseEntity.ok(messageSource.getMessage(
                "good.morning.message",
                null, LocaleContextHolder.getLocale()));
    }
}
