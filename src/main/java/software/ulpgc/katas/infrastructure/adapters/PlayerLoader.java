package software.ulpgc.katas.infrastructure.adapters;

import software.ulpgc.katas.application.domain.model.Player;
import software.ulpgc.katas.application.ports.in.PlayerDeserializer;

import java.io.Reader;
import java.util.List;

public class PlayerLoader implements PlayerDeserializer {
    @Override
    public List<Player> deserialize(Reader source) {
        return List.of();
    }
}
