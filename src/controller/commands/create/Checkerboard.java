package controller.commands.create;

import java.awt.Color;

import controller.commands.CreateImageCommand;
import model.create.CreateImage;

/**
 * Command for creating a checkerboard image of black and white tiles of user defined size. It
 * creates a board of 8x8 number of tiles.
 */
public class Checkerboard implements CreateImageCommand {

  private final int squareSize;

  /**
   * Constructor for initializing the side of the square in a checkerboard.
   *
   * @param squareSize Side of the square.
   */
  public Checkerboard(int squareSize) {
    this.squareSize = squareSize;
  }

  @Override
  public void run(CreateImage model) {
    model.setDimensions(squareSize * 8, squareSize * 8);
    int squaresInARowIterator = 0;
    int squaresInAColumnIterator = 0;
    Color color = Color.black;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < 8; j++) {
        if (color == Color.white) {
          color = Color.black;
        } else {
          color = Color.white;
        }
        model.square(squaresInARowIterator, squaresInAColumnIterator, squareSize, color);
        squaresInAColumnIterator = squaresInAColumnIterator + squareSize;
      }
      squaresInARowIterator = squaresInARowIterator + squareSize;
      squaresInAColumnIterator = 0;
      if (color == Color.black) {
        color = Color.white;
      } else {
        color = Color.black;
      }
    }
  }
}
