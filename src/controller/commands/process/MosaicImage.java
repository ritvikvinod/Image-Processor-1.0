package controller.commands.process;

import controller.commands.ProcessImageCommand;
import model.process.ProcessImage;

/**
 * Command for getting the mosaic of an image. This command calls the mosaic method offered by the
 * ProcessImage model.
 */
public class MosaicImage implements ProcessImageCommand {

  int seeds;

  /**
   * Constructor for getting the number of seeds to mosaic an image.
   *
   * @param seeds The number of seeds.
   */
  public MosaicImage(int seeds) {
    this.seeds = seeds;
  }

  @Override
  public void run(ProcessImage model) {
    try {
      model.mosaic(seeds);
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("File has to be loaded before processing.");
    }
  }
}
