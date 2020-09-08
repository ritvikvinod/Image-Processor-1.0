package controller.commands;

import java.io.IOException;

import model.process.ProcessImage;

/**
 * This interface represents the set of commands for processing an existing image. This interface is
 * implemented by the following commands:
 * <ul>
 * <li>BlurImage</li>
 * <li>DitherImage</li>
 * <li>GreyScaleImage</li>
 * <li>LoadImage</li>
 * <li>MosaicImage</li>
 * <li>SaveImage</li>
 * <li>SepiaImage</li>
 * <li>SharpenImage</li>
 * </ul>
 */
public interface ProcessImageCommand {

  /**
   * Runs the appropriate command for processing an existing image. These commands call the
   * ProcessImage model to process an image in the specified way. Details of what each of the
   * commands do are specified in README.md
   *
   * @param model The ProcessImage model object that is used to process an image.
   * @throws IOException Thrown when image read/write fails.
   */
  public void run(ProcessImage model) throws IOException;
}
