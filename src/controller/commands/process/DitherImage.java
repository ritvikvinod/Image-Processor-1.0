package controller.commands.process;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for dithering an image. This command calls the dithering method offered by the
 * ProcessImage model.
 */
public class DitherImage implements ProcessImageCommand {

  @Override
  public void run(ProcessImage model) {
    try {
      model.dithering();
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("File has to be loaded before processing.");
    }
  }
}
