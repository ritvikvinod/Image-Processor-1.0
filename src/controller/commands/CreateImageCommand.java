package controller.commands;

import java.io.IOException;

import model.create.CreateImage;

/**
 * This interface represents the set of commands for creating a custom image. This interface is
 * implemented by the following commands:
 * <ul>
 * <li>Checkerboard</li>
 * <li>FranceFlag</li>
 * <li>GreeceFlag</li>
 * <li>SwitzerlandFlag</li>
 * <li>RainbowHorizontal</li>
 * <li>RainbowVertical</li>
 * </ul>
 */
public interface CreateImageCommand {

  /**
   * Runs the appropriate command for creating a custom image. These commands call the CreateImage
   * model to create a new image is the specified way. Details of what each of the commands do are
   * specified in README.md
   *
   * @param model The CreateImage model object that is used to create a new image.
   * @throws IOException Thrown when image read/write fails.
   */
  public void run(CreateImage model) throws IOException;
}
