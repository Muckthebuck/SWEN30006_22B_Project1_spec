package src;

import ch.aplu.jgamegrid.Location;

import java.util.ArrayList;
import java.util.Random;

public class ShapeFormat {
    private static final int NROT = 4;
    public static enum ShapeIndex{
        I(new Location[][]{
            {new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1))},
            {new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0))},
            {new Location(new Location(1, 0)), new Location(new Location(0, 1)), new Location(new Location(1, 0)), new Location(new Location(0, 1))},
            {new Location(new Location(2, 0)), new Location(new Location(0, 2)), new Location(new Location(2, 0)), new Location(new Location(0, 2))}
        }),
        J(new Location[][]{
            {new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)), new Location(new Location(0, 1)) },
            {new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0))},
            {new Location(new Location(1, 0)), new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1))},
            {new Location(new Location(1, 1)), new Location(new Location(-1, 1)), new Location(new Location(-1, -1)), new Location(new Location(1, -1)) }

        }),
        L(new Location[][]{
                {new Location(new Location(1, 0)), new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1))},
                {new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0))},
                {new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)), new Location(new Location(0, 1))},
                {new Location(new Location(-1, 1)), new Location(new Location(-1, -1)),  new Location(new Location(1, -1)), new Location(new Location(1, 1))}
        }),
        O(new Location[][]{
            {new Location(new Location(1, 0)), new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1))},
            {new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0))},
            {new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)), new Location(new Location(0, 1))},
            {new Location(new Location(-1, 1)), new Location(new Location(-1, -1)),  new Location(new Location(1, -1)), new Location(new Location(1, 1))}
        }),
        S(new Location[][]{
                {new Location(new Location(1, 0)), new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1)) },
                {new Location(new Location(0, 0)), new Location(new Location(0, 0)),new Location(new Location(0, 0)), new Location(new Location(0, 0)) },
                {new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)) },
                {new Location(new Location(-1, 1)), new Location(new Location(-1, -1)), new Location(new Location(1, -1)), new Location(new Location(1, 1)) }
        }),
        T(new Location[][]{
                {new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)), new Location(new Location(0, 1)) },
                {new Location(new Location(0, 0)), new Location(new Location(0, 0)),new Location(new Location(0, 0)), new Location(new Location(0, 0)) },
                {new Location(new Location(1, 0)), new Location(new Location(1, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1))},
                {new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)) }
        }),
        Z(new Location[][]{
                {new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)), new Location(new Location(0, 1)) },
                {new Location(new Location(0, 0)), new Location(new Location(0, 0)),new Location(new Location(0, 0)), new Location(new Location(0, 0)) },
                {new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0))},
                {new Location(new Location(1, 1)), new Location(new Location(-1, 1)), new Location(new Location(-1, -1)), new Location(new Location(1, -1)) }
        });

        private final Location[][] loc;

        private ShapeIndex(Location[][] loc){
            this.loc = loc;
        }

        public Location[][] getLocation(){
            return loc;
        }

        public static ShapeIndex getRandomDirection(){
            Random random = new Random();
            return values()[random.nextInt(values().length)];
        }

    }


}
