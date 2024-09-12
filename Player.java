public class Player {
  private int crossX;
  private int crossY;

  Player() {
    this.crossX = 0;
    this.crossY = 0;
  }

  public int getX() {
    return this.crossX;
  }

  public int getY() {
    return this.crossY;
  }

  public void setX(int x) {
    this.crossX = x;
  }

  public void setY(int y) {
    this.crossY = y;
  }
}
