package controller.commands.utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for loading an image. This command loads an image from the given file path, converts
 * it into BufferedImage type and passes it over to the model.
 */
public class LoadImage implements ProcessImageCommand {

  private final String fileName;

  /**
   * Constructor for getting the file path.
   * @param fileName the file name of the image to be loaded as String.
   */
  public LoadImage(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void run(ProcessImage model) throws IOException {
    File file = new File(fileName);
    BufferedImage image = ImageIO.read(file);
    model.setImage(image);
  }
}
