package fr.isola.game.players;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public interface IaPlayer {

    public Point move(Player opponent, boolean[][] map);
    public Point destroy(Player opponent, boolean[][] map);

}
