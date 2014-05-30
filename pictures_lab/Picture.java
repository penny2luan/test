import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("flower1.jpg");
    Picture flower2 = new Picture("flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  /**
   * Method to replace blue pixels with pixels from the second picture.
   * @param second picture to replace blue pixels with
   */
  public void chromakey(Picture second)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  Pixel[][] pixels2 = second.getPixels2D();
	  for(int row = 0; row < pixels.length; row++)
		  for(int col = 0; col < pixels[0].length; col++)
			  if(pixels[row][col].getColor().getBlue() >= 200)
				  pixels[row][col].setColor(pixels2[row][col].getColor());
  }
  
  /**
   * method to encode a message into the picture.
   * @param message the message to be encoded into the picture
   */
  public void encode(String message)
  {
	  Pixel[][] pixels = this.getPixels2D();
	  int col = 0;
	  for(int i = 0; i < message.length(); i++)
	  {
		  pixels[0][col].setBlue((int) message.charAt(i));
		  col += 5;
	  }
	  pixels[0][col].setBlue(254);
	  //convert string to ints or char
	  //set every 5th pixel alpha value to char / int value
  }
  
  /**
   * method to return the encoded string in the photo
   * @return the encoded string from the encode() method
   */
  public String decode()
  {
	  Pixel[][] pixels = this.getPixels2D();
	  String decoded = new String();
	  int col = 0;
	  boolean complete = false;
	  while(!complete)
	  {
		  int next = pixels[0][col].getBlue();
		  if(next != 254 && col <= pixels.length)
		  {
				decoded += (char) next;
				col += 5;
		  }
		  else
			  complete = true;
	  }
	  return decoded;
  }
  
  /**
   * change an image to sepia
   */
  public void sepia()
  {
	  int sepiaIntensity = 25;
	  Pixel[][] pixels = this.getPixels2D();
	  for(int row = 0; row < pixels.length; row++)
		  for(int col = 0; col < pixels[0].length; col++)
		  {
			  int r = pixels[row][col].getRed();
			  int b = pixels[row][col].getBlue();
			  int g = pixels[row][col].getGreen();
			  int gry = (r + b + g) / 3;
			  r = g = b = gry;
			  r = r + (sepiaIntensity * 2);
			  g = g + sepiaIntensity;
			  if (r>255) r=255;
			  if (g>255) g=255;
			  
			  b -= sepiaIntensity;
			  if (b<0) b=0;
			  if (b>255) b=255;
			  pixels[row][col].updatePicture(pixels[row][col].getAlpha(), r, g, b);
		  }
  }
  

  /**
   * changes an image to sepia
   * @param intensity the intensity of the sepia; 0 gives a black and white image. 
   * the higher the intensity, the more yellow the image appears.
   * Effect appears best at intensity around 25.
   * @param darkness changes the darkness of the image. Must be greater than or equal to 1.
   * Sepia works best at darkness around 3
   */
  public void sepia(int intensity, int darkness)
  {
	  int sepiaIntensity;
	  if(intensity >= 0)
		  sepiaIntensity = intensity;
	  else sepiaIntensity = 3;
	  Pixel[][] pixels = this.getPixels2D();
	  for(int row = 0; row < pixels.length; row++)
		  for(int col = 0; col < pixels[0].length; col++)
		  {
			  int r = pixels[row][col].getRed();
			  int b = pixels[row][col].getBlue();
			  int g = pixels[row][col].getGreen();
			  int gry;
			  if(darkness >= 1)
				  gry = (r + b + g) / darkness;
			  else 	gry = (r + b + g) / 3;
			  r = g = b = gry; //sets to black and white
			  r += (sepiaIntensity * 2);
			  g += sepiaIntensity;
			  if (r>255) r=255;
			  if (g>255) g=255;
			  b -= sepiaIntensity;
			  if (b<0) b=0;
			  if (b>255) b=255;
			  pixels[row][col].updatePicture(pixels[row][col].getAlpha(), r, g, b);
		  }
  }
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture desert = new Picture("Desert.jpg");
    Picture koala = new Picture("Koala.jpg");
//    beach.explore();
//    beach.zeroBlue();
//    desert.chromakey(koala);
//    desert.sepia(25, 3);
    desert.encode("ABCDEFGHIJKLMNOPasdfgwe6");
    System.out.println(desert.decode());

    desert.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
