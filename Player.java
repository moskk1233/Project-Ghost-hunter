public class Player {
  private int crossX;
  private int crossY;
  private int damage;
  private int level;
  private int exp;

  Player() {
    this.crossX = 0;
    this.crossY = 0;
    this.damage = 10;
    this.level = 1;
    this.exp = 0;
  }

  public int getX() {
    return this.crossX;
  }

  public int getY() {
    return this.crossY;
  }

  public int getLevel() {
    return this.level;
  }

  public int getExp() {
    return this.exp;
  }

  public void setX(int x) {
    this.crossX = x;
  }

  public void setY(int y) {
    this.crossY = y;
  }

  public int getDamage() {
    return this.damage;
  }

  public int getMaxExp() {
    return 100 * this.level;
  }

  public void setDamage(int damage) {
    this.damage = damage;
  }

  public void setExp(int exp) {
    this.exp = exp;
  }

  public void addExp(int exp) {
    this.exp += exp;
    if (this.exp >= 100 * this.level) {
      this.levelUp();
    }
  }

  public void levelUp() {
    this.level++;
    this.damage += 10;
    this.exp = 0;
  }
}
