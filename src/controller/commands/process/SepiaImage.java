package controller.commands.process;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for getting the sepia of an image. This command calls the colorTransform method offered
 * by the ProcessImage model.
 */
public class SepiaImage implements ProcessImageCommand {

  @Override
  public void run(ProcessImage model) {
    try {
      double[][] matrix = {{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}};
      model.colorTransform(matrix);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("File has to be loaded before processing.");
    }

  }
}
