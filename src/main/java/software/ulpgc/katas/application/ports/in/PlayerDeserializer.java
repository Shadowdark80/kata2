package software.ulpgc.katas.application.ports.in;

import software.ulpgc.katas.application.domain.model.Player;

import java.io.InputStream;
import java.util.List;

public interface PlayerDeserializer {
    List<Player> deserializer(InputStream source);
}
