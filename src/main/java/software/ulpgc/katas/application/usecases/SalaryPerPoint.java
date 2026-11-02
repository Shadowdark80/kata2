package software.ulpgc.katas.application.usecases;

import software.ulpgc.katas.application.domain.model.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SalaryPerPoint {

    public Map<String, Double> earningsPerPoint(List<Player> players){
        Map<String, Double> earningByPlayer = new LinkedHashMap<>();

        for (Player p : players){
            earningByPlayer.put(p.name(), calculateEarningPerPoint(p, totalSeasonPoints(p)));
        }
        return earningByPlayer;
    }

    private double calculateEarningPerPoint(Player player, double totalSeasonPoints){
        return player.salary() / totalSeasonPoints;
    }

    private double totalSeasonPoints(Player player) {
        return player.gamesPlayed() * player.pointPerGame();
    }

}

