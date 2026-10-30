package software.ulpgc.katas.application.ports.in;

import software.ulpgc.katas.application.domain.model.Player;

import java.io.Reader;
import java.util.List;

public interface PlayerDeserializer {
    List<Player> deserialize(Reader source);
}
