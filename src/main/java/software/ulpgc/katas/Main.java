package software.ulpgc.katas;

import software.ulpgc.katas.application.domain.model.Player;
import software.ulpgc.katas.application.ports.in.PlayerDeserializer;
import software.ulpgc.katas.application.ports.out.DataSerializer;
import software.ulpgc.katas.application.usecases.SalaryPerPoint;
import software.ulpgc.katas.infrastructure.adapters.CsvStreamSerializer;
import software.ulpgc.katas.infrastructure.adapters.PlayerLoader;
import software.ulpgc.katas.infrastructure.adapters.ReaderFactory;
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
        DataSerializer csvSerializer = new CsvStreamSerializer();

        File file = new File("nba_players_full_stats.csv");
        System.out.println("\n-- 1. Prueba con archivo local --");

        try (Reader csvReader = ReaderFactory.createFromFile(file)){
            List<Player> players = deserializer.deserialize(csvReader);
            Map<String, Double> earnings = salaryPerPoint.earningsPerPoint(players);

            serializer.serializeData(earnings, System.out);

            File outputFile = new File("nba_salary_per_point.csv");
            try (FileOutputStream fileOutputStream = new FileOutputStream(outputFile)){
                csvSerializer.serializeData(earnings, fileOutputStream);
                System.out.println("Archivo CSV generado con éxito: " + outputFile.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("\n-- 2. Prueba con origen de memoria --");
        String memoryStream = "name,team,pointsPerGame,salary,gamesPlayed\n" +
                              "Lebron James,Lakers,25,47000000,70\n" +
                              "Stephen Curry,Warriors,28,40000000,65";

        try (Reader memoryReader = ReaderFactory.createFromMemory(memoryStream)){
            serializer.serializeData(
                    salaryPerPoint.earningsPerPoint(
                            deserializer.deserialize(memoryReader)),
                            System.out
                            );
        } catch (IOException e) {
            throw new RuntimeException("Error al generar el archivo de salida", e);
        }
    }
}
