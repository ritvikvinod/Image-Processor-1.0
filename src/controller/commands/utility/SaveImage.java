package controller.commands.utility;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for saving an image. It gets the processed image from the model and writes it into the
 * file path given by the user.
 */
public class SaveImage implements ProcessImageCommand {

  private final String fileName;

  /**
   * Constructor for getting the file path where the final image has to be written.
   *
   * @param fileName file path of the destination as a String.
   */
  public SaveImage(String fileName) {
    this.fileName = fileName;
  }

  @Override
  public void run(ProcessImage model) throws IOException {
    try {
      ImageIO.write(model.getImage(), "png", new File(fileName));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("File has to be loaded before processing.");
    }

  }
}
