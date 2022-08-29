// TetroBlock.java
package src;
import ch.aplu.jgamegrid.*;

public class TetroBlock extends Actor
{
  private Location[] relLoc = new Location[4];
  // Display a single square in a Tetris piece
  public TetroBlock(int blockId, Location[] relLoc)
  {
    super("sprites/tetroblock" + blockId + ".gif");

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
