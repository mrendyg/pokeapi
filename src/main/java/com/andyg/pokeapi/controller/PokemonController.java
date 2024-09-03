package com.andyg.pokeapi.controller;

import com.andyg.pokeapi.persistence.entity.pokemons.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/pokemon")
@ResponseStatus(HttpStatus.OK)
public class PokemonController {

    @Autowired
    private final RestTemplate restTemplate;


    public PokemonController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public PokemonEntity getPokemon(@PathVariable Long id) {
        String url = "https://pokeapi.co/api/v2/pokemon/" + id;
        PokemonEntity pokemon = restTemplate.getForObject(url, PokemonEntity.class);

        if (pokemon != null) {
            // Filtrar las estadísticas para incluir solo ataque y defensa
            List<Stat> filteredStats = pokemon.getStats().stream()
                    .filter(stat -> "attack".equalsIgnoreCase(stat.getStat().getName()) ||
                            "defense".equalsIgnoreCase(stat.getStat().getName()))
                    .toList(); // Usar .toList() para obtener la lista filtrada
            pokemon.setStats(filteredStats);

            // Obtener y filtrar los movimientos por power
            List<Move> moves = pokemon.getMoves();
            List<Move> movesWithPower = new ArrayList<>();

            // Consultar la API para obtener el valor de power de cada movimiento
            for (Move move : moves) {
                String moveUrl = move.getMove().getUrl();
                MoveDetails moveDetails = restTemplate.getForObject(moveUrl, MoveDetails.class);

                if (moveDetails != null && moveDetails.getPower() != null) {
                    move.setPower(moveDetails.getPower());
                    movesWithPower.add(move);
                }
            }

            // Ordenar por power y tomar los 3 con mayor power
            List<Move> topMoves = movesWithPower.stream()
                    .sorted(Comparator.comparingInt(Move::getPower).reversed())
                    .limit(3)
                    .collect(Collectors.toList());

            // Reemplazar la lista de movimientos en el Pokémon con los movimientos filtrados
            pokemon.setMoves(topMoves);
        }

        return pokemon;
    }




    @GetMapping("/generation/{id}")
    public Page<PokemonSpeciesEntity> getPokemonByGeneration(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        String url = "https://pokeapi.co/api/v2/generation/" + id;
        GenerationEntity response = restTemplate.getForObject(url, GenerationEntity.class);

        if (response == null || response.getPokemon_species() == null) {
            return Page.empty();
        }

        List<PokemonSpeciesEntity> allPokemonSpecies = response.getPokemon_species();
        Pageable pageable = PageRequest.of(page, size);

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allPokemonSpecies.size());

        List<PokemonSpeciesEntity> pagedList = allPokemonSpecies.subList(start, end);

        return new PageImpl<>(pagedList, pageable, allPokemonSpecies.size());
    }



}
