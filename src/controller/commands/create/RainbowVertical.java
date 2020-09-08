package controller.commands.create;

import java.awt.Color;

import controller.commands.CreateImageCommand;
import model.create.CreateImage;

/**
 * Command for generating the image of a rainbow with vertical stripes.
 */
public class RainbowVertical implements CreateImageCommand {

  private final int stripeWidth;
  private final int imageHeight;

  /**
   * Constructor for initialing the width and height of the stripes.
   *
   * @param stripeWidth width of the stripes.
   * @param imageHeight height of the stripes.
   */
  public RainbowVertical(int stripeWidth, int imageHeight) {
    this.stripeWidth = stripeWidth;
    this.imageHeight = imageHeight;
  }


  @Override
  public void run(CreateImage model) {
    model.setDimensions(stripeWidth * 7, imageHeight);
    int k1 = 0;
    Color[] c1 = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue,
        new Color(128, 0, 128), new Color(75, 0, 130)};
    for (int i = 0; i < 7; i++) {
      model.rectangle(k1, 0, stripeWidth, imageHeight, c1[i]);
      k1 = k1 + stripeWidth;
    }
  }
}
