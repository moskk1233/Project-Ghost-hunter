public class Ghost {
  private int x;
  private int y;
  private int health;
  private int maxHealth;
  private int level;
  private boolean isDead;
  private int exp;

  static final int GHOST_OFFSET_X = 5;
  static final int GHOST_OFFSET_Y = 10;

  Ghost() {
    this.x = 0;
    this.y = 0;
    this.level = 1;
    this.isDead = false;
    this.health = 100 * this.level;
    this.maxHealth = health;
    this.exp = this.level * 10;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getExp() {
    return this.exp;
  }

  public int getMaxHealth() {
    return this.maxHealth;
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

  public void setMaxHealth(int maxHealth) {
    this.maxHealth = maxHealth;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setDead(boolean dead) {
    this.isDead = dead;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }
}
