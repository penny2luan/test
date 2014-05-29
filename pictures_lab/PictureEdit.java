import java.awt.Color;
import java.awt.Image;


public class PictureEdit extends Picture{
	
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
	   * Method to change an image to sepia
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
	   * Method to change an image to sepia
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

}
