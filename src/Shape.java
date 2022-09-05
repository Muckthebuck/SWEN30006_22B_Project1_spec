// I.java
package src;

import ch.aplu.jgamegrid.*;
import src.utility.PropertiesLoader;

import java.awt.*;
import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


class Shape extends Actor {

    private final int blockId;
    private final String blockName;
    private Location[][] r;
    private static final int NROT = 4;
    protected Tetris tetris;
    private boolean isStarting = true;
    private int rotId = 0;
    private int nb;
    protected ArrayList<TetroBlock> blocks = new ArrayList<TetroBlock>();
    private Actor nextTetrisBlock = null;
    private String autoBlockMove = "";
    private int autoBlockIndex = 0;

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
                {new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0)), new Location(new Location(0, 0))},
                {new Location(new Location(1, 0)), new Location(new Location(1, 0)), new Location(new Location(1, 0)), new Location(new Location(1, 0))},
                {new Location(new Location(1, 1)), new Location(new Location(1, 1)), new Location(new Location(1, 1)), new Location(new Location(1, 1))},
                {new Location(new Location(0, 1)), new Location(new Location(0, 1)), new Location(new Location(0, 1)), new Location(new Location(0, 1))},
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
                {new Location(new Location(1, 0)), new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1))},
                {new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)) }
        }),
        Z(new Location[][]{
                {new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0)), new Location(new Location(0, 1)) },
                {new Location(new Location(0, 0)), new Location(new Location(0, 0)),new Location(new Location(0, 0)), new Location(new Location(0, 0)) },
                {new Location(new Location(0, 1)), new Location(new Location(-1, 0)), new Location(new Location(0, -1)), new Location(new Location(1, 0))},
                {new Location(new Location(1, 1)), new Location(new Location(-1, 1)), new Location(new Location(-1, -1)), new Location(new Location(1, -1)) }
        }),
        Plus(new Location[][]{
                {new Location(new Location(-1,0)), new Location(new Location(0,-1)), new Location(new Location(1,0)), new Location(new Location(0,1))},
                {new Location(new Location(0,1)), new Location(new Location(-1,0)), new Location(new Location(0,-1)), new Location(new Location(1,0))},
                {new Location(new Location(0,0)), new Location(new Location(0,0)), new Location(new Location(0,0)), new Location(new Location(0,0))},
                {new Location(new Location(1,0)), new Location(new Location(0,1)), new Location(new Location(-1,0)), new Location(new Location(0,-1))},
                {new Location(new Location(0,-1)), new Location(new Location(1,0)), new Location(new Location(0,1)), new Location(new Location(-1,0))},
        }),
        P(new Location[][]{
                {new Location(new Location(-1,-1)), new Location(new Location(1,-1)), new Location(new Location(1,1)), new Location(new Location(-1,1))},
                {new Location(new Location(0,-1)), new Location(new Location(1,0)), new Location(new Location(0,1)), new Location(new Location(-1,0))},
                {new Location(new Location(-1,0)), new Location(new Location(0,-1)), new Location(new Location(1,0)), new Location(new Location(0,1))},
                {new Location(new Location(0,0)), new Location(new Location(0,0)), new Location(new Location(0,0)), new Location(new Location(0,0))},
                {new Location(new Location(-1,1)), new Location(new Location(-1,-1)), new Location(new Location(1,-1)), new Location(new Location(1,1))},
        }),
        Q(new Location[][]{
                {new Location(new Location(-1,-1)), new Location(new Location(1,-1)), new Location(new Location(1,1)), new Location(new Location(-1,1))},
                {new Location(new Location(0,-1)), new Location(new Location(1,0)), new Location(new Location(0,1)), new Location(new Location(-1,0))},
                {new Location(new Location(-1,0)), new Location(new Location(0,-1)), new Location(new Location(1,0)), new Location(new Location(0,1))},
                {new Location(new Location(0,0)), new Location(new Location(0,0)), new Location(new Location(0,0)), new Location(new Location(0,0))},
                {new Location(new Location(0,1)), new Location(new Location(-1,0)), new Location(new Location(0,-1)), new Location(new Location(1,0))},
        });

        private final Location[][] loc;
        private static final int N_EASY_SHAPES = 7;

        private ShapeIndex(Location[][] loc){
            this.loc = loc;
        }

        public Location[][] getLocation(){
            return loc;
        }

        public static ShapeIndex getRandomBlock(String difficulty){
            Random random = new Random();
            if (difficulty == "easy") {
                return values()[random.nextInt(N_EASY_SHAPES)];
            }
            return values()[random.nextInt(values().length)];
        }

    }


    Shape(Tetris tetris, ShapeIndex blockName) {
        super();
        this.blockName = blockName.toString();
        this.tetris = tetris;
        // rotId 0
        this.blockId = blockName.ordinal();
        int spriteId = (this.blockId>ShapeIndex.N_EASY_SHAPES - 1)? ThreadLocalRandom.current().nextInt(0, ShapeIndex.N_EASY_SHAPES) : this.blockId;
        this.r = blockName.getLocation();
        for (Location[] locations : r) {
            blocks.add(new TetroBlock(spriteId, locations));
        }

    }

    public String toString() {
        return "For testing, do not change: Block: " + blockName + ". Location: " + blocks + ". Rotation: " + rotId;
    }

    public void setAutoBlockMove(String autoBlockMove) {
        this.autoBlockMove = autoBlockMove;
    }

    // The game is called in a run loop, this method for a block is called every 1/30 seconds as the starting point
    public void act() {
        if (isStarting) {
            for (TetroBlock a : blocks) {
                Location loc =
                        new Location(getX() + a.getRelLoc(0).x, getY() + a.getRelLoc(0).y);
                gameGrid.addActor(a, loc);
            }
            isStarting = false;
            nb = 0;
        } else if (nb >= blocks.size() && canAutoPlay()) {
            autoMove();
        } else {
            setDirection(90);
            if (nb == 1)
                nextTetrisBlock = tetris.createRandomTetrisBlock();
            if (!advance()) {
                if (nb == 0)  // Game is over when tetrisBlock cannot fall down
                    tetris.gameOver();
                else {
                    setActEnabled(false);
                    gameGrid.addActor(nextTetrisBlock, new Location(6, 0));
                    tetris.setCurrentTetrisBlock(nextTetrisBlock);
                }
            }
            nb++;
        }
    }

    // Based on the input in the properties file, the block can move automatically
    private void autoMove() {
        String moveString = autoBlockMove.substring(autoBlockIndex, autoBlockIndex + 1);
        switch (moveString) {
            case "L":
                left();
                break;
            case "R":
                right();
                break;
            case "T":
                rotate();
                break;
            case "D":
                drop();
                break;
        }

        autoBlockIndex++;
    }

    // Cechk if the block can be played automatically based on the properties file
    private boolean canAutoPlay() {
        if (autoBlockMove != null && !autoBlockMove.equals("")) {
            if (autoBlockMove.length() > autoBlockIndex) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    void display(GameGrid gg, Location location) {
        for (TetroBlock a : blocks) {
            Location loc =
                    new Location(location.x + a.getRelLoc(0).x, location.y + a.getRelLoc(0).y);
            gg.addActor(a, loc);
        }
    }

    // Actual actions on the block: move the block left, right, drop and rotate the block
    void left() {
        if (isStarting)
            return;
        setDirection(180);
        advance();
    }

    void right() {
        if (isStarting)
            return;
        setDirection(0);
        advance();
    }

    void rotate() {
        if (isStarting)
            return;

        int oldRotId = rotId; // Save it
        rotId++;
        if (rotId == 4)
            rotId = 0;

        if (canRotate(rotId)) {
            for (TetroBlock a : blocks) {
                Location loc = new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
                a.setLocation(loc);
            }
        } else
            rotId = oldRotId;  // Restore

    }

    private boolean canRotate(int rotId) {
        // Check for every rotated tetroBlock within the tetrisBlock
        for (TetroBlock a : blocks) {
            Location loc =
                    new Location(getX() + a.getRelLoc(rotId).x, getY() + a.getRelLoc(rotId).y);
            if (!gameGrid.isInGrid(loc))  // outside grid->not permitted
                return false;
            TetroBlock block =
                    (TetroBlock) (gameGrid.getOneActorAt(loc, TetroBlock.class));
            if (blocks.contains(block))  // in same tetrisBlock->skip
                break;
            if (block != null)  // Another tetroBlock->not permitted
                return false;
        }
        return true;
    }

    void drop() {
        if (isStarting)
            return;
        setSlowDown(0);
    }

    // Logic to check if the block has been removed (as winning a line) or drop to the bottom
    private boolean advance() {
        boolean canMove = false;
        for (TetroBlock a : blocks) {
            if (!a.isRemoved()) {
                canMove = true;
            }
        }
        for (TetroBlock a : blocks) {
            if (a.isRemoved())
                continue;
            if (!gameGrid.isInGrid(a.getNextMoveLocation())) {
                canMove = false;
                break;
            }
        }

        for (TetroBlock a : blocks) {
            if (a.isRemoved())
                continue;
            TetroBlock block =
                    (TetroBlock) (gameGrid.getOneActorAt(a.getNextMoveLocation(),
                            TetroBlock.class));
            if (block != null && !blocks.contains(block)) {
                canMove = false;
                break;
            }
        }

        if (canMove) {
            move();
            return true;
        }
        return false;
    }

    // Override Actor.setDirection()
    public void setDirection(double dir) {
        super.setDirection(dir);
        for (TetroBlock a : blocks)
            a.setDirection(dir);
    }

    // Override Actor.move()
    public void move() {
        if (isRemoved())
            return;
        super.move();
        for (TetroBlock a : blocks) {
            if (a.isRemoved())
                break;
            a.move();
        }
    }

    // Override Actor.removeSelf()
    public void removeSelf() {
        super.removeSelf();
        for (TetroBlock a : blocks)
            a.removeSelf();
    }

    public static Location[][] find_all_rotation(Location[] oneRot){
        Location[][] r = new Location[oneRot.length][NROT];
        for(int i =0; i< oneRot.length; i++){
            for(int j=0; j<NROT; j++){
                if(j==0){
                    r[i][j] = oneRot[i];
                }else{
                    r[i][j] = new Location(-r[i][j-1].y, r[i][j-1].x);
                }
            }
        }
        System.out.println("{");
        for(Location[] row: r){
            System.out.print("{");
            for(Location column: row){
                System.out.printf("new Location(new Location(%d,%d)), ", column.x, column.y);
            }
            System.out.println("},");
        }
        System.out.println("}");
        return r;
    }

    public static void main(String args[]) {
        Location[] plus = {new Location(-1,0), new Location(0,1), new Location(0, 0), new Location(1, 0), new Location(0,-1)};
        Location[] p = {new Location(-1,-1), new Location(0,-1), new Location(-1,0), new Location(0,0), new Location(-1,1)};
        Location[] q = {new Location(-1,-1), new Location(0,-1), new Location(-1,0), new Location(0,0), new Location(0,1)};
        Location[][] a = find_all_rotation( q);
    }


}
