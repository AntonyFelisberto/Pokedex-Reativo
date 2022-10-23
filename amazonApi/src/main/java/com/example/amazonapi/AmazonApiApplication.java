package com.example.amazonapi;

import com.example.amazonapi.model.Pokemon;
import com.example.amazonapi.repository.PokedexRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class AmazonApiApplication{

    private PokedexRepository repositoryAplication;

    public static void main(String[] args) {
        SpringApplication.run(AmazonApiApplication.class, args);
    }

    @Bean
    CommandLineRunner init(ReactiveMongoOperations operations,
                           PokedexRepository repository){
        return args -> {
            Flux<Pokemon> pokedexFlux = Flux.just(
                        new Pokemon(null, "Bulbassauro", "Semente", "OverGrow", 6.09),
                        new Pokemon(null, "Charizard", "Fogo", "Blaze", 90.05),
                        new Pokemon(null, "Caterpie", "Minhoca", "Poeira do Escudo", 2.09),
                        new Pokemon(null, "Blastoise", "Marisco", "Torrente", 6.09),
                        new Pokemon(null, "Kratos", "Laminas", "Power", 1.70),
                        new Pokemon(null, "Batman", "Eu sou o batman", "Eu sou o batman", 1.70)
                    ).flatMap(repository::save);
                    pokedexFlux
                            .thenMany(repository.findAll())
                            .subscribe();

        };
    }

}
