package software.ulpgc.katas.application.ports.out;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public interface DataSerializer {
    void serializeData(Map<String, Double> salaryPerPoint, OutputStream output) throws IOException;
}
