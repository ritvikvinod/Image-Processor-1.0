package controller.commands.create;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.commands.CreateImageCommand;
import model.create.CreateImage;

/**
 * Command for creating the flag of France of user defined size.
 */
public class FranceFlag implements CreateImageCommand {
  private final int height;

  /**
   * Constructor for initializing the height of the flag.
   *
   * @param height height of the flag.
   */
  public FranceFlag(int height) {
    this.height = height;
  }


  @Override
  public void run(CreateImage model) throws IOException {
    Double width = height * 1.5;
    model.setDimensions(width.intValue(), height);
    model.rectangle(0, 0, (width.intValue() / 3), height, new Color(0, 85, 164));
    model.rectangle(width.intValue() / 3, 0, (width.intValue() / 3), height,
            new Color(255, 255, 255));
    BufferedImage image = model.rectangle(2 * width.intValue() / 3, 0, (width.intValue() / 3),
            height, new Color(239, 65, 53));
    ImageIO.write(image, "png", new File("FranceFlag.png"));
  }
}
