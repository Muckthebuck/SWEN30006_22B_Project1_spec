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

        int modifiedSlowDown = easySlowDown;

        if (easySlowDown == 0) {
            return 0;
        }
        switch(difficulty){
            case easy:
                break;
            case medium:
                modifiedSlowDown = easySlowDown - 1;
                break;
            case madness:
                int origin = (easySlowDown>1)?easySlowDown - 2: 0;
                modifiedSlowDown = ThreadLocalRandom.current().nextInt(origin, easySlowDown + 1);
                break;
        }
//        System.out.println("Speed is " + modifiedSlowDown);
        return modifiedSlowDown;
    }

    public void modifiedRotate(Shape currentBlock) {
        if (this.difficulty != Difficulty.madness) {
            ((Shape) currentBlock).rotate();
        }
    }
}
