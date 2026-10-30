package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.application.domain.model.Player;
import software.ulpgc.katas.application.ports.in.PlayerDeserializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public record PlayerLoader() implements PlayerDeserializer {

    @Override
    public List<Player> deserialize(Reader source) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(source)){
            return reader.lines()
                    .skip(1)
                    .filter(line -> !line.isBlank())
                    .map(this::toPlayer)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Player toPlayer(String line){
        String[] player = line.split(",");

        return new Player(
                player[0].trim(),
                player[1].trim(),
                Double.parseDouble(player[2].trim()),
                Double.parseDouble(player[3].trim()),
                Integer.parseInt(player[4].trim())
        );
    }
}
