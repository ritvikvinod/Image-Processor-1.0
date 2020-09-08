package controller.commands.create;

import java.awt.Color;

import controller.commands.CreateImageCommand;
import model.create.CreateImage;

/**
 * Command for generating the image of a rainbow containing horizontal stripes.
 */
public class RainbowHorizontal implements CreateImageCommand {
  private final int stripeHeight;
  private final int imageWidth;

  /**
   * Constructor for initializing the width and height of the stripes.
   *
   * @param stripeHeight height of the stripes.
   * @param imageWidth   width of the stripes.
   */
  public RainbowHorizontal(int stripeHeight, int imageWidth) {
    this.stripeHeight = stripeHeight;
    this.imageWidth = imageWidth;
  }


  @Override
  public void run(CreateImage model) {
    model.setDimensions(imageWidth, stripeHeight * 7);
    int stripeThickness = 0;
    Color[] c = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue,
        new Color(128, 0, 128), new Color(75, 0, 130)};
    for (int i = 0; i < 7; i++) {
      model.rectangle(0, stripeThickness, imageWidth, stripeHeight, c[i]);
      stripeThickness = stripeThickness + stripeHeight;
    }
  }
}
