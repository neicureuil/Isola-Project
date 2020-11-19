package fr.isola.game.players;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

public class IaPlayer extends Player {

    public IaPlayer() {
        super();
    }

    public void move(Player opponent, boolean[][] map) {
        AbstractMap.SimpleEntry<Integer, Integer> tmpCoords = getMostSafePlace(this, opponent, map);

        setX(tmpCoords.getKey());
        setY(tmpCoords.getValue());
    }

    public void destroy(Player opponent, boolean[][] map) {
        AbstractMap.SimpleEntry<Integer, Integer> tmpCoords = getMostSafePlace(opponent, this, map);
        map[tmpCoords.getKey()][tmpCoords.getValue()] = false;
    }

    private AbstractMap.SimpleEntry<Integer, Integer> getMostSafePlace(Player p1, Player p2, boolean[][] map) {
        int sizeX = map.length;
        int sizeY = map[0].length;

        int x_min = (p1.getX()-1 < 0) ? 0 : p1.getX()-1;
        int x_max = (p1.getX()+1 >= sizeX) ? sizeX-1 : p1.getX()+1;

        int y_min = (p1.getY()-1 < 0) ? 0 : p1.getY()-1;
        int y_max = (p1.getY()+1 >= sizeY) ? sizeY-1 : p1.getY()+1;

        Map<AbstractMap.SimpleEntry<Integer, Integer>, Integer> safes = new HashMap<AbstractMap.SimpleEntry<Integer, Integer>, Integer>();

        for(int j=y_min; j<= y_max; j++) {
            for(int i=x_min; i<= x_max; i++) {
                if(i != p1.getX() || j!=p1.getY()) {
                    int nbSafe = -1;
                    if (map[i][j] && (p2.getX() != i || p2.getY() != j)) {

                        int x_min_2 = (i-1 < 0) ? 0 : i-1;
                        int x_max_2 = (i+1 >= sizeX) ? sizeX-1 : i+1;

                        int y_min_2 = (j-1 < 0) ? 0 : j-1;
                        int y_max_2 = (j+1 >= sizeY) ? sizeY-1 : j+1;

                        for(int j2=y_min_2; j2<y_max_2; j2++)
                            for(int i2=x_min_2; i2<x_max_2; i2++)
                                if(map[i2][j2] && (p2.getX() != i || p2.getY() != j))
                                    nbSafe++;


                    }
                    safes.put(new AbstractMap.SimpleEntry<Integer, Integer>(i, j), nbSafe);
                }
            }
        }

        int tmpVal = -1;
        AbstractMap.SimpleEntry<Integer, Integer> tmpCoords = null;

        for(AbstractMap.SimpleEntry<Integer, Integer> coords : safes.keySet()) {
            if(safes.get(coords) > tmpVal) {
                tmpVal = safes.get(coords);
                tmpCoords = coords;
            }
        }

        return tmpCoords;
    }
}
