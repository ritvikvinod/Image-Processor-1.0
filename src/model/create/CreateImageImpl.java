package model.create;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 * Class for implementing the CreateImage interface. This class implements the rectangle, cross and
 * square methods. Constructs a buffered image by taking dimensions as input in the constructor.
 */
public class CreateImageImpl implements CreateImage {

  private BufferedImage image;

  @Override
  public void setDimensions(int width, int height) {
    this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
  }

  @Override
  public BufferedImage getImage() {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newImage.setRGB(x, y, image.getRGB(x, y));
      }
    }
    return newImage;
  }

  @Override
  public BufferedImage rectangle(int x, int y, int width, int height, Color c) {
    Graphics2D g = image.createGraphics();
    g.setColor(c);
    g.fillRect(x, y, width, height);
    return image;
  }

  @Override
  public BufferedImage cross(int x, int y, int width, int height, Color c) {
    Graphics2D g = image.createGraphics();
    g.setColor(c);
    g.fillRect(x, y, width, height);
    g.fillRect(y, x, height, width);
    return image;
  }

  @Override
  public BufferedImage square(int x, int y, int side, Color c) {
    Graphics2D g = image.createGraphics();
    g.setColor(c);
    g.fillRect(x, y, side, side);
    return image;
  }
}
