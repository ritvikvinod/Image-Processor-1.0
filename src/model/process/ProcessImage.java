package model.process;

import java.awt.image.BufferedImage;

/**
 * Represents an interface of the application which contains methods to perform operations on
 * existing images. Currently the interface allows applying filters on an image and transforming the
 * colors of an image. An image is considered as a sequence of pixels stored as a three dimensional
 * organization of numbers with rows=height, columns=width and depth=3. With 8-bit channels, each
 * value is between 0 and 255.
 * <li>All the methods mutates the existing image after applying the operations</li>
 */
public interface ProcessImage {

  /**
   * Sets this model object to the given BufferedImage object. Model will create an imageArray from
   * this BufferedImage object.
   *
   * @param image BufferedImage object of the given image.
   */
  void setImage(BufferedImage image);

  /**
   * Returns the image represented by this model object as a BufferedImage object.
   *
   * @return Image in BufferedImage type.
   */
  BufferedImage getImage();

  /**
   * A basic operation in many image processing algorithms is filtering. A filter has a “kernel,”
   * which is a 2D array of numbers, having odd dimensions (3x3, 5x5, etc.). Given a pixel in the
   * image and a channel, the result of the filter can be computed for that pixel and channel. Many
   * image processing operations can be framed in terms of filtering (i.e. they are a matter of
   * designing an appropriate kernel and filtering the image with it).
   * <li>Clamping is applied if either of the new individual RGB values of every computed pixel is
   * outside the range 0-255. </li>
   *
   * @param filter Kernel to be applied to the image.
   * @return Updated image after applying the filter stored as BufferedImage type.
   * @throws IllegalArgumentException if the filter has either side of even dimensions.
   */
  BufferedImage applyFilter(double[][] filter) throws IllegalArgumentException;

  /**
   * A color transformation modifies the color of a pixel based on its own color. Consider a pixel
   * with color (r,g,b). A color transformation results in the new color of this pixel to be
   * (r’,g’,b’) such that each of them are dependent only on the values (r,g,b).
   * <p>
   * This is a method to apply linear color transformation. A linear color transformation is a color
   * transformation in which the final red, green and blue values of a pixel are linear combinations
   * of its initial red, green and blue values.
   * </p>
   * <li>Similar to filtering, clamping may be necessary after a color transformation to save and
   * display an image properly.</li>
   *
   * @param matrix Matrix which is used for changing the colors of every pixel.
   * @return Updated image after applying the filter stored as BufferedImage type.
   * @throws IllegalArgumentException if the matrix is not of correct dimensions.
   */
  BufferedImage colorTransform(double[][] matrix) throws IllegalArgumentException;

  /**
   * Method for giving an image a “stained glass window” effect. They create pictures by joining
   * smaller irregularly-shaped pieces of stained glass. An image is “broken down” into such stained
   * glass pieces, by choosing a set of points in the image (called seeds). Each pixel in the image
   * is then paired to the seed that is closest to  it (by distance). This creates a cluster of
   * pixels for each seed. Then the color of each pixel  in the image is replaced with the average
   * color of its cluster. The seeds are chosen randomly (i.e. choose random pixel locations from
   * the image).
   *
   * @param seeds Number of seeds for mosaic.
   * @return Updated image after applying mosaicing stored as BufferedImage type.
   */
  BufferedImage mosaic(int seeds);

  /**
   * The operation of breaking down an image that has many colors into an image that is made of dots
   * from just a few colors is known as dithering. One example is to compress an image: reduce all
   * colors in an image to just a few colors so that storing them requires less space. This is known
   * as an “indexed color scheme”, and is used by GIF images. The Floyd-Steinberg algorithm is used
   * to create dithered “black and white” images. That is, our images will only have two colors:
   * black and white.
   *
   * @return Updated image after applying dithering stored as BufferedImage type.
   */
  BufferedImage dithering();
}
