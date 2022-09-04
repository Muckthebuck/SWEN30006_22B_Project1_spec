// TetroBlock.java
package src;
import ch.aplu.jgamegrid.*;
import java.util.concurrent.ThreadLocalRandom;

public class TetroBlock extends Actor
{
  private Location[] relLoc;
  // Display a single square in a Tetris piece
  public TetroBlock(int spriteId, Location[] relLoc)
  {
    super("sprites/tetroblock" + spriteId + ".gif");
    this.relLoc = new Location[relLoc.length];
    this.relLoc = relLoc.clone();
    /*System.out.println(relLoc);*/
  }

  public Location getRelLoc(int rotId)
  {
    return relLoc[rotId];
  }

  /**
   * Do not change this method. For testing and marking purpose
   * @return
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    if (this.gameGrid != null) {
      sb.append(this.getLocation());
    }
    return sb.toString();
  }
}
