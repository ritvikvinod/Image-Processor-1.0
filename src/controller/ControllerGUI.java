package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.*;

import controller.commands.utility.LoadImage;
import controller.commands.utility.SaveImage;
import model.create.CreateImage;
import model.process.ProcessImage;
import view.GUIView;

/**
 * This class represents the controller for this program that takes displays a graphical user
 * interface. This controller takes in the model and GUIView objects as input. The inputs are sent
 * as the commands specified in the README.md file from GUIView.
 */
public class ControllerGUI extends AbstractController {

  GUIView GUIView;

  /**
   * Parameterized constructor.
   *
   * @param processImage Model object for the ProcessImage model.
   * @param createImage  Model object for the CreateImage model.
   * @param GUIView      GUIView object for the GUIView.
   */
  public ControllerGUI(ProcessImage processImage, CreateImage createImage, GUIView GUIView) {
    this.processImage = processImage;
    this.createImage = createImage;
    processCommands = assignProcessMap();
    createCommands = assignCreateMap();
    this.GUIView = GUIView;
    this.GUIView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.GUIView.setVisible(true);
    this.GUIView.setFeatures(this);

  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void loadImage(String fileName) {
    LoadImage loadImage = new LoadImage(fileName);
    try {
      loadImage.run(processImage);
      GUIView.displayImage(processImage.getImage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveImage(String fileName) {
    SaveImage saveImage = new SaveImage(fileName);
    try {
      saveImage.run(processImage);
      GUIView.displayImage(processImage.getImage());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  protected void updateDisplayImage(BufferedImage image) {
    GUIView.displayImage(image);
  }
}
