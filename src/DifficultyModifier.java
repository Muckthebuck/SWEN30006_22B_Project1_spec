package src;

import java.util.concurrent.ThreadLocalRandom;

public class DifficultyModifier {

    private Difficulty difficulty;

    private enum Difficulty{
        easy("easy"), medium("medium"), madness("madness");
        private final String lvl;

        private Difficulty(String lvl){this.lvl = lvl;}

        public String getlvl(){
            return lvl;
        }
    }

    public DifficultyModifier(String difficulty) {
        this.difficulty = Difficulty.valueOf(difficulty);
    }

    public String getDifficulty() {return this.difficulty.getlvl();}

    public int setModifiedSpeed(int easySlowDown) {
        String difficulty = this.getDifficulty();
        int modifiedSlowDown = easySlowDown;

        if (easySlowDown == 0) {
            return 0;
        }

        if (difficulty == "medium") {
            modifiedSlowDown = easySlowDown - 1;
        } else if (difficulty == "madness") {
            if (easySlowDown > 1) {
                modifiedSlowDown = ThreadLocalRandom.current().nextInt(easySlowDown - 2, easySlowDown + 1);
            } else if (easySlowDown == 1) {
                modifiedSlowDown = ThreadLocalRandom.current().nextInt(0, easySlowDown + 1);
            }
        }

//        System.out.println("Speed is " + modifiedSlowDown);
        return modifiedSlowDown;
    }

    public void modifiedRotate(Shape currentBlock) {
        if (this.getDifficulty() != "madness") {
            ((Shape) currentBlock).rotate();
        }
    }
}
