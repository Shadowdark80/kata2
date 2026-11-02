package software.ulpgc.katas.infrastructure.adapters;

import java.io.*;
import java.nio.charset.StandardCharsets;

public record ReaderFactory() {

    public static Reader createFromFile(File file) throws FileNotFoundException {
        fileNotFoundError(file);
        return new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
    }

    private static Reader createFromMemory(String memoryData) {
        return new StringReader(memoryData);
    }

    private static void fileNotFoundError(File file) throws FileNotFoundException {
        if(file.exists()){
            throw new FileNotFoundException("El archivo no existe" + file.getAbsolutePath());
        }
    }
}
