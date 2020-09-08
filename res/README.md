# Assignment8
This application offers operations that can be used for image processing. Many applications use
color images. A good number of these provide a way to change their appearance in different ways.
They do this by editing the colors of individual dots in the image (called pixels). Whatever the
file format (jpg, png, bmp, etc.) an image is basically a sequence of pixels. Each pixel has a
position in the image (row, column) and a color. For a color image, the pixel’s color is stored
using three numbers: red, green, blue. Any color is a combination of these three base colors. This
application works using the same principle.

## Operations

This application provides several operations for both processing an image and generating new images as well as other utility
commands. The operations provided are given below-

### Processing an image:

* Blur an image.
* Sharpen an image.
* Grey-scale equivalent of an image.
* Sepia equivalent of an image.
* Dithering an image.
* Mosaic equivalent of an image.

### Generating an image:

* Generating flags.
* Generating image with rainbow stripes.
* Generating image with checkerboard pattern.

### Utility commands:
* Load an image.
* Save an image.

## Format of Commands:

* <b>load input.png</b>: Loads an image input.png. To do any processing operation, this command has to be called first.
* <b>save input.png</b> : Creates an image input.png. This is usually done after calling any processing/creation 
operations. It cannot be called if no image has been loaded earlier or no image has been created.
* <b>blur</b> : Does the blurring operation on an already loaded/created image.
* <b>sharpen</b> : Does the sharpening operation on an already loaded/created image.
* <b>greyscale</b> : Does the greyscale operation on an already loaded/created image.
* <b>sepia</b> : Does the sepia operation on an already loaded/created image.
* <b>dither</b> : Does the dither operation on an already loaded/created image.
* <b>mosaic 1000</b> : Does the mosaic operation on an already loaded/created image using number of
seeds as 1000.
* <b>checkerboard 50</b> : Creates a checkerboard with each square having a side of size 50.
* <b>flag-france 300</b> : Creates the flag of France with height 300.
* <b>flag-greece 300</b> : Creates the flag of Greece with height 300.
* <b>flag-switzerland 300</b> : Creates the flag of Switzerland with height 300.
* <b>rainbow-horizontal 50 300</b> : Creates a rainbow with horizontal stripes with the height of each 
stripe as 50 and the width of the image as 300.
* <b>rainbow-vertical 50 300</b> : Creates a rainbow with vertical stripes with the width of each 
stripe as 50 and the height of the image as 300.

While running the jar file from the command line, the entire path of the input file has to be given as a argument.
The input file should contain the entire source/destination path of the file while loading and saving an image.

## How to use each operation-

### Blur an image:
Blurring an image is probably the simplest example of filtering. We can use the given 3x3 filter.
Blurring can be done by applying this filter to every channel of every pixel to produce the output
image. This filtering operation is provided by the model in our application. Hence, we would call
the applyFilter method with the corresponding filter for blurring an image.

### Sharpening an image:
Image sharpening is in some ways, the opposite of blurring. Sharpening accentuates edges (the
boundaries between regions of high contrast), thereby giving the image a “sharper” look. Sharpening
is done using the given 5x5 filter. Sharpening can be done by applying this filter to every channel
of every pixel to produce the output image. This filtering operation is provided by the model in
our application. Hence, we would call the applyFilter method with the corresponding filter for
sharpening an image.

### Grey-scale of an image:
A grey-scale image is composed only of shades of grey (if the red, green and blue values are the
same, it is a shade of grey). A simple way to get the grey-scale equivalen of an image is to use the
given color transformation: 𝑟′=𝑔′=𝑏′=0.2126𝑟+0.7152𝑔+0.0722𝑏. Grey-scale can be done by applying this
transform to every channel of every pixel to produce the output image. This color transformation
operation is provided by the model in our application. Hence, we would call the colorTransform
method with the corresponding filter for getting the grey-scale of an image.

### Sepia of an image:
Photographs taken in the 19th and early 20th century had a characteristic reddish brown tone. This
is referred to as a sepia tone. This conversion to sepia equivalent of an image is is done using
the given color transformation. Sepia can be done by applying this transform to every channel of
every pixel to produce the output image. This color transformation operation is provided by the
model in our application. Hence, we would call the colorTransform method with the corresponding
filter for getting the sepia of an image.

### Generating flags:
Currently, the model offers operations to create rectangle, square and cross in an image. We make
use of these methods to generate flags.

This is being done in the FranceFlag, GreeceFlag and SwitzerlandFlag commands. These command takes in the 
height of the image that needs to created from Driver and calls the corresponding methods in the model. 
Once we have the height, we can get the width and the dimensions of every shape inside as per the 
prescribed proportions of each flag.

* France: We create three vertical rectangles for the France flag (the blue, white and red stripes).
We do this by calling the rectangle methods offered by the CreateImage model.
* Greece: We create nine horizontal stripes alternating between white and blue. This is done by
calling the rectangle method with the appropriate color. After this, we create a square in the upper
right side using the square method, and then finally create a cross inside this square using the
cross method.
* Switzerland: We fill the entire image with red initially. This is done using the rectangle method.
After this, we create a cross of the prescribed proportions at the center using the cross method.

### Generating checkerboard:
Currently, the model offers operations to create square in an image. We make use of this method to
generate a checkerboard pattern. 

This is being done in the Checkerboard command. This command takes in the size of each side of a square in the checkerboard image that needs to created and calls the
corresponding methods in the model. Once we have the square size, we can construct the board as it
of size 8x8.

For creating this board, we successively call the square method offered by CreateImage model 64
times.

### Generating rainbow:
Currently, the model offers operations to create rectangle in an image. We make use of this method
to generate a rainbow pattern.

This is being done in the RainbowHorizontal and RainbowVertical commands. These controller.commands take 
in the width/height of a stripe and the other dimension for the whole
image image that needs to created and calls the corresponding methods in the model.

For creating this board, we successively call the rectangle method offered by CreateImage model 7
times with the appropriate color.

## Operations being offered by model:

### Process Image:

* applyFilter: Takes in a 2D array containing the filter to perform image filtering.
* colorTransform: Takes in 2D array containing the transform matrix to perform color transformation.
* mosaic: Takes in the number of seeds to perform the mosaic operation.
* dithering: Performs the dithering operation on the image.
* setImage: Takes in a BufferedImage as parameter to set as the image for the model object.
* getImage: Returns the BufferedImage corresponding to the model object.

### Create Image:

* rectangle: Creates a rectangle of specified size, position and color in an image.
* square: Creates a square of specified size, position and color in an image.
* cross: Creates a cross of specified size, position and color in an image.
* setDimensions: Takes in width and height to set the dimensions of the image to be created.
* getImage: Returns the BufferedImage corresponding to the model object.

## Completed sections of program:

* Blur an image.
* Sharpen an image.
* Grey-scale equivalent of an image.
* Sepia equivalent of an image.
* Generating flag of France.
* Generating flag of Switzerland.
* Generating flag of Greece.
* Generating image with vertical rainbow stripes.
* Generating image with horizontal rainbow stripes.
* Generating image with checkerboard pattern.
* Dithering an image.
* Mosaic equivalent of an image.
* Controller
* Taking input from a file.
* Create a JAR file.

## Changes to the model in Assignment 8:

The overall model design from Assignment 7 stays unchanged. The only major change is that the model for 
ProcessImage was moved into a process package and the model for CreateImage was moved in to a create 
package. 

The operations that were implemented as part of Assignment 7 stays unchanged as well. We haven't changed the
signatures of the methods. However, in ProcessImageImpl, the implementations of applyFilter and colorTransform
were altered so that the model object gets mutated.

We have removed the constructors that were previously used in Assignment 7 for ProcessImage and CreateImage
as they are no longer needed. Instead we use setImage and setDimensions methods of the respective classes. 

To the ProcessImage interface and ProcessImageImpl class, we have added methods for dithering, mosaic, 
setImage and getImage.

To the CreateImage interface and CreateImageImpl class, we have added methods for setDimensions and getImage.

## Controller:

The controller added as part of Assignment 8 takes a scanner object as input. In this case, only DriverFileInput
uses this controller. This driver scans the input file and passes the corresponding scanner object. The controller 
will then parse through each command given in the input file.

## Citations for images:

https://pixabay.com/photos/landscape-autumn-green-reported-4612528/
https://pixabay.com/photos/landscape-trees-sky-nature-GUIView-4615517/

All images and videos on Pixabay are released under the Pixabay License, which is based on 
Creative Commons' public domain deed CC0. Thus, Pixabay's images and videos may be used freely
for almost any application – also commercially and in printed format. Attribution is appreciated, 
but not required.