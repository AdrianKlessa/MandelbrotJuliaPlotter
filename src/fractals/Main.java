package fractals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ImaginaryNumber C = new ImaginaryNumber(0.3,0.6);
		
		
		
		
		ImaginaryNumber C = new ImaginaryNumber(0.08,0.12);
		BufferedImage outputBufferedImage = Tools.generateBlackAndWhiteJulia(-2, 2, 2, -2, 16000, 16000, 100, C);
			
		
		File outputJPG = new File("output.jpg");
		try {
			ImageIO.write(outputBufferedImage,"jpg",outputJPG);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
