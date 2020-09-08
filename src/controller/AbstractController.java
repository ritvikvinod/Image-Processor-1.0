package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

import controller.commands.CreateImageCommand;
import controller.commands.ProcessImageCommand;
import controller.commands.create.Checkerboard;
import controller.commands.create.FranceFlag;
import controller.commands.create.GreeceFlag;
import controller.commands.create.RainbowHorizontal;
import controller.commands.create.RainbowVertical;
import controller.commands.create.SwitzerlandFlag;
import controller.commands.process.BlurImage;
import controller.commands.process.DitherImage;
import controller.commands.process.GreyScaleImage;
import controller.commands.process.MosaicImage;
import controller.commands.process.SepiaImage;
import controller.commands.process.SharpenImage;
import controller.commands.utility.LoadImage;
import controller.commands.utility.SaveImage;
import model.create.CreateImage;
import model.process.ProcessImage;

/**
 * This is the abstract controller class for the Controller interface. It abstracts common method
 * between ControllerGUI and ControllerBatchCommands.
 */
abstract class AbstractController implements Controller {

  ProcessImage processImage;
  CreateImage createImage;
  Map<String, Function<Scanner, ProcessImageCommand>> processCommands;
  Map<String, Function<Scanner, CreateImageCommand>> createCommands;

  @Override
  public void readInput(Scanner scanner) {
    while (scanner.hasNext()) {
      String inputString = scanner.next();
      parseInputCommands(inputString, scanner);
    }
  }

  protected Map<String, Function<Scanner, ProcessImageCommand>> assignProcessMap() {
    Map<String, Function<Scanner, ProcessImageCommand>> processCommands = new HashMap<>();
    processCommands.put("load", s -> new LoadImage(s.next()));
    processCommands.put("exit", s -> new LoadImage(s.next()));
    processCommands.put("blur", s -> new BlurImage());
    processCommands.put("save", s -> new SaveImage(s.next()));
    processCommands.put("greyscale", s -> new GreyScaleImage());
    processCommands.put("dither", s -> {
      new GreyScaleImage();
      return new DitherImage();
    });
    processCommands.put("mosaic", s -> new MosaicImage(s.nextInt()));
    processCommands.put("sepia", s -> new SepiaImage());
    processCommands.put("sharpen", s -> new SharpenImage());
    return processCommands;
  }

  protected Map<String, Function<Scanner, CreateImageCommand>> assignCreateMap() {
    Map<String, Function<Scanner, CreateImageCommand>> createCommands = new HashMap<>();
    createCommands.put("checkerboard", s -> new Checkerboard(s.nextInt()));
    createCommands.put("flag-france", s -> new FranceFlag(s.nextInt()));
    createCommands.put("flag-greece", s -> new GreeceFlag(s.nextInt()));
    createCommands.put("flag-switzerland", s -> new SwitzerlandFlag(s.nextInt()));
    createCommands.put("rainbow-horizontal", s -> new RainbowHorizontal(s.nextInt(), s.nextInt()));
    createCommands.put("rainbow-vertical", s -> new RainbowVertical(s.nextInt(), s.nextInt()));
    return createCommands;
  }

  protected void parseInputCommands(String inputString, Scanner scanner) {
    ProcessImageCommand c;
    Function<Scanner, ProcessImageCommand> cmd = processCommands.getOrDefault(inputString, null);
    if (cmd == null) {
      parseCreateImageCommands(inputString, scanner);
    } else {
      c = cmd.apply(scanner);
      try {
        c.run(processImage);
        updateDisplayImage(processImage.getImage());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  protected void parseCreateImageCommands(String inputString, Scanner scanner) {
    CreateImageCommand d;
    Function<Scanner, CreateImageCommand> cmd1 = createCommands.getOrDefault(inputString, null);
    if (cmd1 == null) {
      throw new IllegalArgumentException("Not a valid command.");
    } else {
      d = cmd1.apply(scanner);
      try {
        d.run(createImage);
        processImage.setImage(createImage.getImage());
        updateDisplayImage(processImage.getImage());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  protected abstract void updateDisplayImage(BufferedImage image);
}
