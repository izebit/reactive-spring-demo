/**
 * @author <a href="mailto:izebit@gmail.com">Artem Konovalov</a> <br/>
 * Date: 10.01.2021
 */
open module spring.reactive.app {
    exports ru.izebit.reactive.spring.demo;

    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires spring.boot;
    requires spring.boot.starter;
    requires spring.boot.autoconfigure;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.datatype.jsr310;

    requires spring.webflux;
    requires org.reactivestreams;
    requires reactor.core;

    requires spring.data.mongodb;

    requires lombok;
    requires de.flapdoodle.embed.mongo;
    requires de.flapdoodle.embed.process;
}