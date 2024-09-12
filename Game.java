import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class Game {
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Game");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(1000, 563);

      Scene scene = new Scene();
      
      frame.add(scene);
      frame.setVisible(true);
    });
  }
}