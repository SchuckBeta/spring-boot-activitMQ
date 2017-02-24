package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:spring-activeMQ.xml"})
public class ConfigXml {

}
