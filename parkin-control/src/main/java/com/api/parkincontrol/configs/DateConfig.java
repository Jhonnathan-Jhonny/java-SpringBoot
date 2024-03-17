package com.api.parkincontrol.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

@Configuration
public class DateConfig {

    //Colocando no padrão iso-8601UTC
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";//T -> para separar, Z-> para dizer o formato UTC
    //Com essas caracteristicas pode-se fazer a diferença dessas horas no offSet
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    //Como ObjectMapper é uma classe de fora da aplicação,
    // para o spring  entenda as configuração feitas e torne isso um BEAN, utiliza-se as anotações
    @Bean
    @Primary //Para priorizar, caso seja gerado outros BEANS para ObjectMapper
    public ObjectMapper objectMapper() {//Quando é feito serializações utiliza o ObjectMapper
        JavaTimeModule module = new JavaTimeModule();
        module.addSerializer(LOCAL_DATETIME_SERIALIZER);
        return new ObjectMapper()
                .registerModule(module);
    }
}
