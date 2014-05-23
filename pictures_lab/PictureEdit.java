import java.awt.Color;
import java.awt.Image;


public class PictureEdit extends Picture{
	
	  public void chromakey(Picture second)
	  {
		  Pixel[][] pixels = this.getPixels2D();
		  Pixel[][] pixels2 = second.getPixels2D();
		  for(int row = 0; row < pixels.length; row++)
			  for(int col = 0; col < pixels[0].length; col++)
			  {
				  if(pixels[row][col].getColor().equals(Color.BLUE))
					  pixels[row][col].setColor(Color.GRAY);
			  }
	  }
	
	
//	 /**
//	  * Method to get an image from the picture
//	  * @return  the buffered image since it is an image
//	  */
//	 public Image getImage()
//	 {
//	   return bufferedImage;
//	 }
//	 
//	 /**
//	  * Method to return the pixel value as an int for the given x and y location
//	  * @param x the x coordinate of the pixel
//	  * @param y the y coordinate of the pixel
//	  * @return the pixel value as an integer (alpha, red, green, blue)
//	  */
//	 public int getBasicPixel(int x, int y)
//	 {
//	    return bufferedImage.getRGB(x,y);
//	 }
//	    
//	 /** 
//	  * Method to set the value of a pixel in the picture from an int
//	  * @param x the x coordinate of the pixel
//	  * @param y the y coordinate of the pixel
//	  * @param rgb the new rgb value of the pixel (alpha, red, green, blue)
//	  */     
//	 public void setBasicPixel(int x, int y, int rgb)
//	 {
//	   bufferedImage.setRGB(x,y,rgb);
//	 }

}
