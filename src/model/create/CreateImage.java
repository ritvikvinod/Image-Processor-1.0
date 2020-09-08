package model.create;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Interface for creating an image. While many images are photographs, some images can be generated
 * by a computer. These images often have a structure that makes this generation possible.
 * Generating an image is as simple as specifying the red, green and blue values for each pixel in
 * the image. This interface assumes that every image is a combination of simple and complex shapes.
 * It contains methods for generating images by manipulating the shapes and sized of various
 * shapes.
 */
public interface CreateImage {

  void setDimensions(int width, int height);

  BufferedImage getImage();

  /**
   * Method for creating a rectangle in the image.
   *
   * @param x      the x-coordinate of the lower left corner.
   * @param y      the y-coordinate of the lower left corner.
   * @param width  the width of the rectangle.
   * @param height the heiht of the rectangle.
   * @param c      the color of the rectangle.
   * @return the rectangle of BufferedImage type.
   */
  BufferedImage rectangle(int x, int y, int width, int height, Color c);

  /**
   * Method for creating a cross in the image.
   *
   * @param x      x-coordinate of the lower left corner of the vertical rectangle.
   * @param y      y-coordinate of the lower left corner of the vertical rectangle.
   * @param width  width of the vertical rectangle.
   * @param height height of the vertical rectangle.
   * @param c      Color of the cross.
   * @return the cross of BufferedImage type.
   */
  BufferedImage cross(int x, int y, int width, int height, Color c);

  /**
   * Method for creating a square in the image.
   *
   * @param x    x-coordinate fo the lower left corner.
   * @param y    y-coordinate of the lower left corner.
   * @param side length of every side.
   * @param c    Color of the square.
   * @return the square of BufferedImage type.
   */
  BufferedImage square(int x, int y, int side, Color c);
}
