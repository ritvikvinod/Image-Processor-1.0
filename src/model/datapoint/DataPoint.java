package model.datapoint;

/**
 * This represents a 2-D point in a 2-D space. It consists of the x coordinate and the y coordinate
 * and getter methods for getting the values of x and y. A datapoint (x,y) cannot be changed once
 * set.
 */
public final class DataPoint {
  private final int x;
  private final int y;

  /**
   * Constructor for initializing the values of x and y.
   *
   * @param x x coordinate.
   * @param y y coordinate.
   */
  public DataPoint(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Getter method for getting the x coordinate.
   *
   * @return x value.
   */
  public int getX() {
    return this.x;
  }

  /**
   * Getter method for getting the y coordinate.
   *
   * @return y value.
   */
  public int getY() {
    return this.y;
  }
}
