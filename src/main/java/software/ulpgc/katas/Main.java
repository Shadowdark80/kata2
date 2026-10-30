package software.ulpgc.katas;

import software.ulpgc.katas.application.domain.model.Player;
import software.ulpgc.katas.application.ports.in.PlayerDeserializer;
import software.ulpgc.katas.application.ports.out.DataSerializer;
import software.ulpgc.katas.application.usecases.SalaryPerPoint;
import software.ulpgc.katas.infrastructure.adapters.PlayerLoader;
import software.ulpgc.katas.infrastructure.adapters.StreamSerializer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Main {
    static void main() throws FileNotFoundException {
        PlayerDeserializer deserializer = new PlayerLoader();
        SalaryPerPoint salaryPerPoint = new SalaryPerPoint();
        DataSerializer serializer = new StreamSerializer();

        File file = new File("nba_players_full_stats.csv");

        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)){
            List<Player> players = deserializer.deserialize(reader);

            Map<String, Double> earnings = salaryPerPoint.earningsPerPoint(players);

            serializer.serializeData(earnings, System.out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
