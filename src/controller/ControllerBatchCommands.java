package controller;

import java.awt.image.BufferedImage;

import model.create.CreateImage;
import model.process.ProcessImage;

/**
 * This class represents the controller for this program that takes in batch commands through a
 * file. This controller takes in a scanner input. The inputs needs to be the commands that are
 * specified in the README.md file.
 */
public class ControllerBatchCommands extends AbstractController {

  /**
   * Parameterized constructor.
   *
   * @param processImage Model object for the ProcessImage model.
   * @param createImage  Model object for the CreateImage model.
   */
  public ControllerBatchCommands(ProcessImage processImage, CreateImage createImage) {
    this.processImage = processImage;
    this.createImage = createImage;
    processCommands = assignProcessMap();
    createCommands = assignCreateMap();
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void loadImage(String fileName) {
  }

  @Override
  public void saveImage(String fileName) {
  }

  protected void updateDisplayImage(BufferedImage image) {
  }
}
