package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.application.ports.out.DataSerializer;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Map;

public record CsvStreamSerializer() implements DataSerializer {
    @Override
    public void serializeData(Map<String, Double> salaryPerPoint, OutputStream outputStream) throws IOException {
        outputStreamNull(outputStream);
        try {
            writeLine("playerName" + "," + "earningsPerPoint", outputStream);
            dataWriteLineError(salaryPerPoint, outputStream);
            for (Map.Entry<String, Double> entry : salaryPerPoint.entrySet()){
                writeLine(formatLine(entry.getKey(), entry.getValue()), outputStream);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al escrinir en el flujo de salida", e);
        }
    }

    private String formatLine(String player, Double earningPerPoint) {
        return String.format(Locale.US, "%s" + "," + "%.2f", player, earningPerPoint);
    }

    private void dataWriteLineError(Map<String, Double> salaryPerPoint, OutputStream outputStream) throws IOException {
        if (salaryPerPoint == null || salaryPerPoint.isEmpty()){
            writeLine("No hay datos disponibles", outputStream);
        }
    }

    private void writeLine(String text, OutputStream output) throws IOException {
        String line = text + System.lineSeparator();
        output.write(line.getBytes(StandardCharsets.UTF_8));
    }

    private void outputStreamNull(OutputStream o) {
        if (o == null){
            throw new IllegalArgumentException("Error al escribir en el flujo de salida");
        }
    }
}
