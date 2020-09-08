package controller.commands.process;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for blurring an image. This command calls the applyFilter method offered by the
 * ProcessImage model.
 */
public class BlurImage implements ProcessImageCommand {

  @Override
  public void run(ProcessImage model) {
    try {
      double[][] filter = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
      model.applyFilter(filter);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("File has to be loaded before processing.");
    }

  }
}

