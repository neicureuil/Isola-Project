package fr.isola.game.players;

public interface IaPlayer {

    public Point move(Player opponent, boolean[][] map);
    public Point destroy(Player opponent, boolean[][] map);

}
