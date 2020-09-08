package controller.commands.process;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for sharpening an image. This command calls the applyFilter method offered by the
 * ProcessImage model.
 */
public class SharpenImage implements ProcessImageCommand {

  @Override
  public void run(ProcessImage model) {
    try {
      double[][] filter = {{-0.125, -0.125, -0.125, -0.125, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, 0.25, 1, 0.25, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125}, {-0.125, -0.125, -0.125, -0.125, -0.125}};
      model.applyFilter(filter);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("File has to be loaded before processing.");
    }
  }
}
