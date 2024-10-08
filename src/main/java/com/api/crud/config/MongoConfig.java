package com.api.crud.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private int mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Bean
    public MongoClient mongoClient() {
        // Crear un URI de conexión basado en los valores del properties
        String mongoUri = "mongodb://" + mongoHost + ":" + mongoPort;
        return new MongoClient(new MongoClientURI(mongoUri));
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        // Usar el nombre de la base de datos del properties
        return mongoClient().getDatabase(mongoDatabase);
    }
}