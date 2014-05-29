import java.awt.Color;
import java.awt.Image;


public class PictureEdit extends Picture{
	
	  public void chromakey(Picture second)
	  {
		  Pixel[][] pixels = this.getPixels2D();
		  Pixel[][] pixels2 = second.getPixels2D();
		  for(int row = 0; row < pixels.length; row++)
			  for(int col = 0; col < pixels[0].length; col++)
				  if(pixels[row][col].getColor().getBlue() >= 200)
					  pixels[row][col].setColor(pixels2[row][col].getColor());
	  }

}
