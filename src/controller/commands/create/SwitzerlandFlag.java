package controller.commands.create;

import java.awt.Color;

import controller.commands.CreateImageCommand;
import model.create.CreateImage;

/**
 * Class for generating the flag of Switzerland of user defined size.
 */
public class SwitzerlandFlag implements CreateImageCommand {

  private final int height;

  /**
   * Constuctor for initializing the height of the flag.
   *
   * @param height height of the flag.
   */
  public SwitzerlandFlag(int height) {
    this.height = height;
  }

  @Override
  public void run(CreateImage model) {
    model.setDimensions(height, height);
    Double heightSmallestProportion = height / 32.0;
    model.rectangle(0, 0, height, height, new Color(255, 0, 0));
    model.cross(heightSmallestProportion.intValue() * 13,
            heightSmallestProportion.intValue() * 6, heightSmallestProportion.intValue() * 6,
            heightSmallestProportion.intValue() * 20, new Color(255, 255, 255));
  }
}
