package com.example.amazonapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Pokemon {

    @Id
    private String id;
    private String name;
    private String category;
    private String hability;
    private Double weight;

    public Pokemon(String id, String name, String category, String hability, Double weight) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.hability = hability;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getHability() {
        return hability;
    }

    public void setHability(String hability) {
        this.hability = hability;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pokemon pokemon)) return false;
        return id.equals(pokemon.id) && name.equals(pokemon.name) && category.equals(pokemon.category) && hability.equals(pokemon.hability) && weight.equals(pokemon.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, hability, weight);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", hability='" + hability + '\'' +
                ", weight=" + weight +
                '}';
    }
}
