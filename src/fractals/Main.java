package fractals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		// ImaginaryNumber C = new ImaginaryNumber(0.3,0.6);
		
		
		
		
		ImaginaryNumber C = new ImaginaryNumber(0.285,0.01);
		// BufferedImage outputBufferedImage = Tools.generateBlackAndWhiteJulia(-2, 2, 2, -2, 16000, 16000, 100, C);
		BufferedImage outputBufferedImage = Tools.generateMandelbrot(18000,12000,100);
		
		File outputPNG = new File("output.png");
		try {
			ImageIO.write(outputBufferedImage,"png",outputPNG);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
