package controller.commands.process;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for getting the greyscale of an image. This command calls the colorTransform method
 * offered by the ProcessImage model.
 */
public class GreyScaleImage implements ProcessImageCommand {

  @Override
  public void run(ProcessImage model) {
    try {
      double[][] matrix = {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
          {0.2126, 0.7152, 0.0722}};
      model.colorTransform(matrix);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("File has to be loaded before processing.");
    }
  }
}
