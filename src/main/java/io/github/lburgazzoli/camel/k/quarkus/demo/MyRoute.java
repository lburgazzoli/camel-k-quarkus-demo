package io.github.lburgazzoli.camel.k.quarkus.demo;

import org.apache.camel.BindToRegistry;
import org.apache.camel.Processor;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.RouteBuilder;

public class MyRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:tick?period=1s")
            .process("myProcessor")
            .to("log:demo");
    }

    @BindToRegistry
    public static Processor myProcessor(
            @PropertyInject("my.processor.message") String message) {

        return e -> e.getMessage().setBody(message.toUpperCase());
    }
}