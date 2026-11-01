package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.application.domain.model.Player;
import software.ulpgc.katas.application.ports.in.PlayerDeserializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public record PlayerLoader() implements PlayerDeserializer {

    @Override
    public List<Player> deserialize(Reader source) throws IOException {
        List<Player> playersList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(source)){
            reader.readLine();
            String player;
            while ((player = reader.readLine()) != null) {
                if (player.isBlank()) continue;

                Player parsedPlayer = toPlayer(player);
                if (parsedPlayer != null) {
                    playersList.add(parsedPlayer);
                }
            }
        }
        return playersList;
    }

    private Player toPlayer(String line){
        String[] player = line.split(",");

        if(errorLine(player, line)) return null;

        try {
            return new Player(
                    player[0].trim(),
                    player[1].trim(),
                    Double.parseDouble(player[2].trim()),
                    Double.parseDouble(player[3].trim()),
                    Integer.parseInt(player[4].trim())
            );
        } catch (NumberFormatException e) {
            System.err.println("Error de formato en la fila: " + line);
            return null;
        }

    }

    private boolean errorLine(String[] player, String line) {
        if(player.length < 5) {
            System.err.println("Fila ignorada por falta de columnas: " + line);
            return true;
        }
        return false;
    }
}
