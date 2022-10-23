package com.example.amazonapi.controller;

import com.example.amazonapi.model.Pokemon;
import com.example.amazonapi.repository.PokedexRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private PokedexRepository repository;

    private PokemonController(PokedexRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public Flux<Pokemon> getAllPokemons(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pokemon>> getPokemons(@PathVariable String id){
        return repository.findById(id)
                .map(Pokemon -> ResponseEntity.ok(Pokemon))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Pokemon> savePokemons(@RequestBody Pokemon pokemon){
        pokemon.setId(null);
        return repository.save(pokemon);
    }

    @PutMapping("{id}")
    public Mono<ResponseEntity<Pokemon>> updatePokemons(@PathVariable String id, @RequestBody Pokemon pokemon){
        return repository.findById(id)
                .flatMap(existingPokemon ->{
                    existingPokemon.setName(pokemon.getName());
                    existingPokemon.setCategory(pokemon.getCategory());
                    existingPokemon.setHability(pokemon.getHability());
                    existingPokemon.setWeight(pokemon.getWeight());
                    return repository.save(existingPokemon);
                 })
                .map(updatePokemons -> ResponseEntity.ok(updatePokemons))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Object>> deletePokemons(@PathVariable(value = "id") String id){
        return repository.findById(id)
                .flatMap(
                        existingPokemon -> repository.delete(existingPokemon)
                        .then(
                                Mono.just(
                                        ResponseEntity.noContent().build()
                                )
                        )
                )
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public Mono<Void> deleteAllPokemons(){
        return repository.deleteAll();
    }

}
