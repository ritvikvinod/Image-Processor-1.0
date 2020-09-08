package model.process;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import model.datapoint.DataPoint;

/**
 * Class for implementing the ProcessImage interface. This class implements applyFilter,
 * colorTranform, dithering and mosaicing methods. Takes a buffered image as input in constructor
 * and constructs an image array.
 */
public class ProcessImageImpl implements ProcessImage {

  private Color[][] imageArray;

  @Override
  public void setImage(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    Color[][] rgbArray = new Color[width][height];
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        rgbArray[x][y] = new Color(image.getRGB(x, y));
      }
    }
    this.imageArray = rgbArray;
  }

  @Override
  public BufferedImage getImage() {
    BufferedImage newImage = new BufferedImage(this.imageArray.length,
            this.imageArray[0].length, BufferedImage.TYPE_INT_RGB);
    for (int c = 0; c < this.imageArray[0].length; c++) {
      for (int r = 0; r < this.imageArray.length; r++) {
        newImage.setRGB(r, c, this.imageArray[r][c].getRGB());
      }
    }
    return newImage;
  }

  @Override
  public BufferedImage applyFilter(double[][] filter) throws IllegalArgumentException {
    int height = imageArray[0].length;
    int width = imageArray.length;
    if (filter.length % 2 == 0 || filter[0].length % 2 == 0) {
      throw new IllegalArgumentException("Dimensions have to be odd.");
    }
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        newImage.setRGB(x, y, getNewColors(y, x, filter).getRGB());
      }
    }
    setImageArray(newImage);
    return newImage;
  }

  private void setImageArray(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        this.imageArray[x][y] = new Color(image.getRGB(x, y));
      }
    }
  }

  private Color getNewColors(int y, int x, double[][] filter) {
    double red = 0;
    double green = 0;
    double blue = 0;
    int col;
    int row = x - 1;
    for (int r = 0; r < filter.length; r++, row++) {
      col = y - 1;
      for (int c = 0; c < filter[0].length; c++, col++) {
        if (row > 0 && col > 0 && row < imageArray.length && col < imageArray[0].length) {
          red += imageArray[row][col].getRed() * filter[r][c];
          green += imageArray[row][col].getGreen() * filter[r][c];
          blue += imageArray[row][col].getBlue() * filter[r][c];
        }
      }
    }
    return clamper((int) red, (int) green, (int) blue);
  }

  @Override
  public BufferedImage colorTransform(double[][] matrix) throws IllegalArgumentException {
    int height = imageArray[0].length;
    int width = imageArray.length;
    if (matrix.length != 3 || matrix[0].length != 3) {
      throw new IllegalArgumentException("Matrix should be of 3x3 dimensions only.");
    }
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int r = 0; r < imageArray.length; r++) {
      for (int c = 0; c < imageArray[0].length; c++) {
        Color pix = imageArray[r][c];
        double red = matrix[0][0] * pix.getRed() + matrix[0][1] * pix.getGreen() +
                matrix[0][2] * pix.getBlue();
        double green = matrix[1][0] * pix.getRed() + matrix[1][1] * pix.getGreen() +
                matrix[1][2] * pix.getBlue();
        double blue = matrix[2][0] * pix.getRed() + matrix[2][1] * pix.getGreen() +
                matrix[2][2] * pix.getBlue();

        imageArray[r][c] = clamper((int) red, (int) green, (int) blue);
        newImage.setRGB(r, c, imageArray[r][c].getRGB());
      }
    }
    setImageArray(newImage);
    return newImage;
  }

  private Color clamper(int red, int green, int blue) {
    red = red > 255 ? 255 : Math.max(red, 0);
    green = green > 255 ? 255 : Math.max(green, 0);
    blue = blue > 255 ? 255 : Math.max(blue, 0);
    return new Color(red, green, blue);
  }

  @Override
  public BufferedImage dithering() {
    int width = imageArray.length;
    int height = imageArray[0].length;
    floydSteinbergAlgorithm(width, height);
    return setBufferedImage(width, height);
  }

  private void floydSteinbergAlgorithm(int width, int height) {
    for (int c = 0; c < height; c++) {
      for (int r = 0; r < width; r++) {
        Color pixel = imageArray[r][c];
        int oldColor = pixel.getRed();
        int newColor = ((255 - pixel.getRed()) > (pixel.getRed() - 0)) ? 0 : 255;
        int errorColor = oldColor - newColor;
        imageArray[r][c] = new Color(newColor, newColor, newColor);

        if (c != height - 1) {
          assignNewDitheringValue(r, c + 1, (7.0 / 16.0) * errorColor);
        }
        if (c != height - 1 && r != width - 1) {
          assignNewDitheringValue(r + 1, c + 1, (1.0 / 16.0) * errorColor);
        }
        if (c != 0 && r != width - 1) {
          assignNewDitheringValue(r + 1, c - 1, (3.0 / 16.0) * errorColor);
        }
        if (r != width - 1) {
          assignNewDitheringValue(r + 1, c, (5.0 / 16.0) * errorColor);
        }
      }
    }
  }

  private void assignNewDitheringValue(int x, int y, double addValue) {
    Color pixel = imageArray[x][y];
    double color = pixel.getRed() + addValue;
    imageArray[x][y] = clamper((int) color, (int) color, (int) color);
  }

  private BufferedImage setBufferedImage(int width, int height) {
    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int c = 0; c < height; c++) {
      for (int r = 0; r < width; r++) {
        newImage.setRGB(r, c, imageArray[r][c].getRGB());
      }
    }
    return newImage;
  }

  @Override
  public BufferedImage mosaic(int seeds) {
    int height = imageArray[0].length;
    int width = imageArray.length;
    Random rand = new Random();

    BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    List<DataPoint> seedList = new ArrayList<>();
    List<Color> seedAverageColor;

    for (int i = 1; i <= seeds; i++) {
      DataPoint p = new DataPoint(rand.nextInt(width), rand.nextInt(height));
      seedList.add(p);
    }

    HashMap<DataPoint, Integer> allSeedGroups = new LinkedHashMap<>();
    for (int c = 0; c < width; c++) {
      for (int r = 0; r < height; r++) {
        DataPoint point = new DataPoint(c, r);
        allSeedGroups.put(point, assignSeed(seedList, point));
      }
    }
    seedAverageColor = assignAverageColors(allSeedGroups, seeds);
    Iterator seedIt = allSeedGroups.keySet().iterator();

    for (int c = 0; c < width; c++) {
      for (int r = 0; r < height; r++) {
        DataPoint pair = (DataPoint) seedIt.next();
        Color colorVal = seedAverageColor.get(allSeedGroups.get(pair));
        newImage.setRGB(c, r, colorVal.getRGB());
      }
    }
    setImageArray(newImage);
    return newImage;
  }

  private int assignSeed(List<DataPoint> seedList, DataPoint p) {
    List<Double> allDistances = seedList.stream().map(b ->
            calcDistance(b, p)).collect(Collectors.toList());
    return allDistances.indexOf(Collections.min(allDistances));
  }

  private double calcDistance(DataPoint a, DataPoint b) {
    int x1 = a.getX();
    int y1 = a.getY();
    int x2 = b.getX();
    int y2 = b.getY();
    double xsquare = Math.pow((x2 - x1), 2);
    double ysquare = Math.pow((y2 - y1), 2);
    return Math.sqrt(xsquare + ysquare);
  }

  private List<Color> assignAverageColors(Map<DataPoint, Integer> allClusters, int seeds) {
    List<Color> averageClusterColor = new ArrayList<>();
    for (int cluster = 0; cluster < seeds; cluster++) {
      double redSum = 0;
      double greenSum = 0;
      double blueSum = 0;
      int clusterTotal = 0;
      for (Map.Entry<DataPoint, Integer> point : allClusters.entrySet()) {
        if (point.getValue().equals(cluster)) {
          redSum += imageArray[point.getKey().getX()][point.getKey().getY()].getRed();
          greenSum += imageArray[point.getKey().getX()][point.getKey().getY()].getGreen();
          blueSum += imageArray[point.getKey().getX()][point.getKey().getY()].getBlue();

          clusterTotal++;
        }
      }
      if (clusterTotal == 0) {
        clusterTotal++;
      }
      int redAvg = (int) (redSum / clusterTotal);
      int greenAvg = (int) (greenSum / clusterTotal);
      int blueAvg = (int) (blueSum / clusterTotal);
      averageClusterColor.add(clamper(redAvg, greenAvg, blueAvg));
    }
    return averageClusterColor;
  }

}