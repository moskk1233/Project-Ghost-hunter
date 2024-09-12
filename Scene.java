import java.util.Random;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Scene extends JPanel implements MouseMotionListener, MouseListener {
  Ghost[] ghosts;
  Player player;
  int ghostAmount;

  Image background;
  Image ghostImage;
  Image playerCrosshair;
  
  Font font = new Font("Tahoma", Font.PLAIN, 50);
  Timer timer = new Timer();

  private void newWave() {
    for (int i = 0; i< this.ghostAmount; i++) {
      Random rng = new Random();
      this.ghosts[i] = new Ghost();
      Ghost ghost = this.ghosts[i];
      
      ghost.setX(rng.nextInt(1000));
      ghost.setY(rng.nextInt(563));
      ghost.setDead(false);
      ghost.setLevel(this.player.getLevel());
      GhostMovement task = new GhostMovement(this.ghosts[i], this);
      timer.scheduleAtFixedRate(task, 0, 100);
    }
  }
  
  Scene() {

    this.ghostAmount = 5;
    this.ghosts = new Ghost[this.ghostAmount];
    this.player = new Player();

    for (int i = 0; i< this.ghostAmount; i++) {
      Random rng = new Random();

      this.ghosts[i] = new Ghost();

      this.ghosts[i].setX(rng.nextInt(1000));
      this.ghosts[i].setY(rng.nextInt(563));
      GhostMovement task = new GhostMovement(this.ghosts[i], this);
      timer.scheduleAtFixedRate(task, 0, 100);
    }

    this.setSize(1000, 563);

    this.background = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "background.jpg");
    this.ghostImage = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "ghost.png");
    this.playerCrosshair = Toolkit.getDefaultToolkit().createImage(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "sight.gif");

    this.addMouseMotionListener(this);
    this.addMouseListener(this);
  }

  @Override
  public void paint(Graphics g) {
    // draw background
    g.drawImage(this.background, 0, 0, this);
    
    // draw game name
    g.setFont(font);
    g.setColor(Color.YELLOW);
    g.drawString("Ghost Hunter", 650, 75);
    g.drawRect(625, 25, 350, 60);

    // draw player exp bar
    int playerExpBar = (int)Math.floor((float)this.player.getExp() / this.player.getMaxExp() * this.getWidth());
    g.drawString("Level: " + this.player.getLevel(), 0, 450);
    g.fillRect(0, 500, playerExpBar, 10);

    // draw ghosts
    for (int i = 0; i < this.ghostAmount; i++) {
      Ghost ghost = this.ghosts[i];
      
      
      if (!ghost.isDead()) {
        if (ghost.getHealth() <= 0) {
          this.player.addExp(ghost.getExp());
          ghost.setDead(true);
        }
        g.drawImage(this.ghostImage, ghost.getX(), ghost.getY(), this);
      }
    }
    
    // draw ghost hitbox
    g.setFont(this.font.deriveFont(10F));
    for (int i = 0; i < this.ghostAmount; i++) {
      Ghost ghost = this.ghosts[i];
      int ghostHealth = (int)Math.floor((float)ghost.getHealth() / ghost.getMaxHealth() * 100);
      
      if (!ghost.isDead()) {
        g.drawRect(ghost.getX() + Ghost.GHOST_OFFSET_X, ghost.getY() + Ghost.GHOST_OFFSET_Y, 90, 80);
        g.drawString("x:" + ghost.getX() + " y:" + ghost.getY() + " level: " + ghost.getLevel(), ghost.getX(), ghost.getY() + 5);
        g.fillRect(ghost.getX(), ghost.getY() + 100, ghostHealth, 10);
      }
    }

    // ghosts crosshair
    g.drawImage(this.playerCrosshair, this.player.getX(), this.player.getY(), 50, 50, this);
    

    // game over
    if (isGameOver(this.ghosts)) {
      this.newWave();
      
      // g.setColor(Color.WHITE);
      // g.setFont(font.deriveFont(50F));
      // g.drawString("Game Over", 375, 300);
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    try {
      File initialFile = new File(System.getProperty("user.dir") + File.separator + "assets" + File.separator + "gun.wav");
      AudioInputStream stream = AudioSystem.getAudioInputStream(initialFile);
      AudioFormat format = stream.getFormat();
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip clip = (Clip)AudioSystem.getLine(info);
      clip.open(stream);
      clip.start();
    } catch(Exception err) {
      System.out.println(err.getMessage());
    }
    
    for (int i = 0; i < this.ghostAmount; i++) {
      Ghost ghost = this.ghosts[i];

      if (ghost.isDead()) continue;

      if (isTouchHitBox(e.getX(), e.getY(), ghost.getX(), ghost.getY())) {
        ghost.setHealth(ghost.getHealth() - this.player.getDamage());
      }
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {
    this.player.setX(e.getX() - 25);
    this.player.setY(e.getY() - 25);
    this.repaint();
  }

  boolean isGameOver(Ghost[] ghosts) {
    for (Ghost ghost : ghosts) {
      if (!ghost.isDead()) {
        return false;
      }
    }
    return true;
  }

  boolean isTouchHitBox(int x, int y, int ghostX, int ghostY) {
    if (x >= ghostX + Ghost.GHOST_OFFSET_X 
      && x <= ghostX + 100
      && y >= ghostY + Ghost.GHOST_OFFSET_Y
      && y <= ghostY + 100
    ) {
      return true;
    } else {
      return false;
    }
  }
}