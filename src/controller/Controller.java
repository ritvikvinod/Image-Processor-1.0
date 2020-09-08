package controller;

import java.io.IOException;
import java.util.Scanner;

/**
 * This interface represents all the operations on controller. These operations are usually called
 * by the Driver or the View.java for callback.
 */
public interface Controller {

  /**
   * Terminates the graphical user interface and exits the program.
   */
  void exitProgram();

  /**
   * Loads an image passed as input. This method will call the LoadImage command.
   *
   * @param fileName File path of the image to be loaded.
   */
  public void loadImage(String fileName);

  /**
   * Saves an image passed as input. This method will call the SaveImage command.
   *
   * @param fileName File path of the image to be saved.
   */
  public void saveImage(String fileName);

  /**
   * Uses the scanner passed as argument to constructor. This method will parse each command and
   * their arguments from the scanner. These commands need to be in the format specified in
   * README.md file.
   *
   * @param scanner Scanner object that contains the commands to be executed.
   * @throws IOException Thrown when image read/write fails.
   */
  public void readInput(Scanner scanner);
}
