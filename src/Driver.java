import controller.Controller;
import controller.ControllerBatchCommands;
import controller.ControllerGUI;
import model.create.CreateImage;
import model.create.CreateImageImpl;
import model.process.ProcessImage;
import model.process.ProcessImageImpl;
import view.GUIView;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class acts as the driver of the entire program. It contains the main method. Currently, it
 * supports user giving batch commands through a file and opening a graphical user interface
 * ("-script filePath" and "-interactive"). Any other commands throw an exception.
 */
public class Driver {

  /**
   * The main method for this program.
   *
   * @param args Arguments passed to the program.
   */
  public static void main(String[] args) {
    if (args[0].equals("-script")) {
      try {
        File file = new File(args[1]);
        Scanner scanner = new Scanner(file);

        ProcessImage processImage = new ProcessImageImpl();
        CreateImage createImage = new CreateImageImpl();

        Controller controller = new ControllerBatchCommands(processImage, createImage);
        controller.readInput(scanner);
      } catch (IOException e) {
        System.out.println("File Not Found.");
      }
    } else if (args[0].equals("-interactive")) {
      GUIView.setDefaultLookAndFeelDecorated(true);
      GUIView frame = new GUIView("Image Processor 1.0");

      ProcessImage processImage = new ProcessImageImpl();
      CreateImage createImage = new CreateImageImpl();

      Controller controllerGUI = new ControllerGUI(processImage, createImage, frame);
    } else {
      throw new IllegalArgumentException("Not a valid command");
    }
  }
}

