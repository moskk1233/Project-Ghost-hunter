public class Ghost {
  private int x;
  private int y;
  private int health;
  private int level;
  private boolean isDead;

  static final int GHOST_OFFSET_X = 5;
  static final int GHOST_OFFSET_Y = 10;

  Ghost(int health) {
    this.x = 0;
    this.y = 0;
    this.health = health;
    this.isDead = false;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getHealth() {
    return this.health;
  }

  public int getLevel() {
    return this.level;
  }

  public boolean isDead() {
    return this.isDead;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setDead(boolean dead) {
    this.isDead = dead;
  }
}
