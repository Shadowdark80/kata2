package software.ulpgc.katas.application.usecases;

import software.ulpgc.katas.application.domain.model.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DeflaterOutputStream;

public class SalaryPerPoint {
    public Map<String, Double> earningsPerPoint(List<Player> players){
        Map<String, Double> earningByPlayer = new LinkedHashMap<>();

        for (Player p : players) {
            earningByPlayer.put(p.name(), calculateEarningsPerPoint(p, totalPointsOnSeason(p)));
        }
        return earningByPlayer;
    }

    public double calculateEarningsPerPoint(Player player, double totalSeasonPoints) {
        return player.salary() / player.pointsPerGame();
    }

    public double totalPointsOnSeason(Player player) {
        return player.pointsPerGame() * player.gamesPlayed();
    }
}
