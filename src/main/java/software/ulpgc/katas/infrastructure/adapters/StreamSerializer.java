package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.application.ports.out.DataSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public record StreamSerializer() implements DataSerializer {
    @Override
    public void serializeData(Map<String, Double> salaryPerPoint, OutputStream output) {
        try {
            writeLine("Ganancias por punto , ", output);
            for (Map.Entry<String, Double> entry : salaryPerPoint.entrySet()){
                writeLine(formatLine(entry.getKey(), entry.getValue()), output);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatLine(String playerName, Double earningPerPoint) {
        return playerName + " -> " + String.format("%.2f", earningPerPoint) + "$";
    }

    private void writeLine(String text, OutputStream output) throws IOException {
        String line = text + System.lineSeparator();
        output.write(line.getBytes(StandardCharsets.UTF_8));
    }
}
