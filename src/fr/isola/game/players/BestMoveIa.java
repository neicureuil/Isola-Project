package fr.isola.game.players;

import java.util.HashMap;
import java.util.Map;

public class BestMoveIa extends Player implements IaPlayer {

    public BestMoveIa() {
        super();
    }

    @Override
    public Point move(Player opponent, boolean[][] map) {
        Point pts = getMostSafePlace(this, opponent, map);
        return (pts == null) ? new Point(0,0) : pts;
    }

    @Override
    public Point destroy(Player opponent, boolean[][] map) {
        Point pts = getMostSafePlace(opponent, this, map);
        return (pts == null) ? new Point(0,0) : pts;
    }

    private Point getMostSafePlace(Player p1, Player p2, boolean[][] map) {
        int sizeX = map.length;
        int sizeY = map[0].length;

        int x_min = (p1.getX()-1 < 0) ? 0 : p1.getX()-1;
        int x_max = (p1.getX()+1 >= sizeX) ? sizeX-1 : p1.getX()+1;

        int y_min = (p1.getY()-1 < 0) ? 0 : p1.getY()-1;
        int y_max = (p1.getY()+1 >= sizeY) ? sizeY-1 : p1.getY()+1;

        Map<Point, Integer> safes = new HashMap<Point, Integer>();

        for(int j=y_min; j<= y_max; j++) {
            for(int i=x_min; i<= x_max; i++) {
                if(i != p1.getX() || j!=p1.getY()) {
                    int nbSafe = -1;
                    if (map[i][j] && (p2.getX() != i || p2.getY() != j)) {
                        int x_min_2 = (i-1 < 0) ? 0 : i-1;
                        int x_max_2 = (i+1 >= sizeX) ? sizeX-1 : i+1;

                        int y_min_2 = (j-1 < 0) ? 0 : j-1;
                        int y_max_2 = (j+1 >= sizeY) ? sizeY-1 : j+1;

                        for(int j2=y_min_2; j2<=y_max_2; j2++)
                            for(int i2=x_min_2; i2<=x_max_2; i2++)
                                if(map[i2][j2] && (p2.getX() != i || p2.getY() != j))
                                    nbSafe++;


                    }
                    safes.put(new Point(i, j), nbSafe);
                }
            }
        }

        int tmpVal = -1;
        Point tmpCoords = null;

        for(Point coords : safes.keySet()) {
            if(safes.get(coords) > tmpVal) {
                tmpVal = safes.get(coords);
                tmpCoords = coords;
            }
        }

        return tmpCoords;
    }
}
