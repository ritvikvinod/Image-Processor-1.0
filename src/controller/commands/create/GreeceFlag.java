package controller.commands.create;

import java.awt.Color;

import controller.commands.CreateImageCommand;
import model.create.CreateImage;

/**
 * Command for creating the flag of Greece of user defined size.
 */
public class GreeceFlag implements CreateImageCommand {

  private final int height;

  /**
   * Constructor for initializing the height of the flag.
   *
   * @param height height of the flag.
   */
  public GreeceFlag(int height) {
    this.height = height;
  }


  @Override
  public void run(CreateImage model) {
    Double width = (height * 3) / 2.0;
    model.setDimensions(width.intValue(), height);

    int stripeThickness = 0;
    Color color;
    for (int i = 0; i < 9; i++) {
      if (i % 2 == 0) {
        color = new Color(13, 94, 175);
      } else {
        color = new Color(255, 255, 255);
      }
      model.rectangle(0, stripeThickness, width.intValue(), height / 9, color);
      stripeThickness += height / 9;
    }
    int squareSize = height / 9 * 5;
    model.square(0, 0, squareSize, new Color(13, 94, 175));
    Double crossStart = (squareSize) * 0.4;
    Double crossWidth = (squareSize) * 0.2;
    model.cross(crossStart.intValue(), 0, crossWidth.intValue(), squareSize,
            new Color(255, 255, 255));
  }
}
