import java.util.Random;
import java.util.TimerTask;

public class GhostMovement extends TimerTask {

  private Ghost ghost;
  private Scene scene;
  
  GhostMovement(Ghost ghost, Scene scene) {
    this.ghost = ghost;
    this.scene = scene;
  }
  
  @Override
  public void run() {
      Random rng = new Random();
      ghost.setX(ghost.getX() + rng.nextInt(-20, 20));
      ghost.setY(ghost.getY() + rng.nextInt(-20, 20));

      if (ghost.getX() > 900) {
        ghost.setX(900);
      } else if (ghost.getX() < 100) {
        ghost.setX(100);
      }

      if (ghost.getY() > 400) {
        ghost.setY(400);
      } else if (ghost.getY() < 10) {
        ghost.setY(10);
      }

      scene.repaint();
  }
  
}
