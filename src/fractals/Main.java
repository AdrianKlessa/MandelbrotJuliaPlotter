package fractals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class Main {
	
	static boolean pngMode=true;
	static ColorEnum color = ColorEnum.RED;
	static boolean terminate=false;
	public static void main(String[] args) throws Exception {
		// ImaginaryNumber C = new ImaginaryNumber(0.3,0.6);
		Scanner keyboardScanner = new Scanner(System.in);
		int inputInt;
		int horizontalRes;
		int verticalRes;
		int howMany;
		double realPart;
		double imaginaryPart;
		File outputFile;
		String fileName;
		BufferedImage outputBufferedImage;
		ImaginaryNumber C;
		
		keyboardScanner.useLocale(Locale.US);
		while(!terminate) {
			System.out.println("Welcome to the Julia and Mandelbrot set drawer!");
			System.out.println("Current color scheme: "+color);
			if(pngMode) {
				System.out.println("Currently chosen output file format: PNG");
			}else {
				System.out.println("Currently chosen output file format: JPG");
			}
			System.out.println("Please input one of the following numbers to proceed: ");
			System.out.println("1 - Draw the Mandelbrot set");
			System.out.println("2 - Draw a specified Julia set");
			System.out.println("3 - Change the color scheme");
			System.out.println("4 - Change the output file format");
			System.out.println("5 - Create multiple random fractals (single-threaded)");
			System.out.println("6 - Create multiple random fractals (multi-threaded)");
			System.out.println("7 - Exit");
			inputInt=keyboardScanner.nextInt();
			switch(inputInt) {
			case 1:
				System.out.println("Please provide the horizontal reslution");
				horizontalRes=keyboardScanner.nextInt();
				verticalRes = horizontalRes/3*2;
				// outputBufferedImage = Tools.generateMandelbrot(horizontalRes,verticalRes,100);
				outputBufferedImage = Tools.generateMandelbrot(horizontalRes, verticalRes, 100, color);
				if(pngMode) {
					outputFile = new File("Mandelbrot.png");
					ImageIO.write(outputBufferedImage,"png",outputFile);
				}else {
					outputFile = new File("Mandelbrot.jpg");
					ImageIO.write(outputBufferedImage,"jpg",outputFile);
				}
				break;
			case 2:
				System.out.println("Please provide the horizontal resolution");
				horizontalRes = keyboardScanner.nextInt();
				verticalRes=horizontalRes;
				System.out.println("Please provide the real part of the complex number");
				realPart=keyboardScanner.nextDouble();
				System.out.println("Please provide the imaginary part of the complex number");
				imaginaryPart = keyboardScanner.nextDouble();
				C = new ImaginaryNumber(realPart,imaginaryPart);
				outputBufferedImage = Tools.generateJulia(-2,2,2,-2,horizontalRes,verticalRes,100,C, color);
				if(pngMode) {
					fileName = realPart+"+"+imaginaryPart+"i"+".png";
					outputFile = new File(fileName);
					ImageIO.write(outputBufferedImage,"png",outputFile);
					
				}else {
					fileName = realPart+"+"+imaginaryPart+"i"+".jpg";
					outputFile = new File(fileName);
					ImageIO.write(outputBufferedImage,"jpg",outputFile);
				}
				
				break;
			case 3: 
				switchColors();
				break;
			case 4:
				pngMode=!pngMode;
				break;
				
				
			case 5:
				System.out.println("How many random fractals to generate?");
				howMany=keyboardScanner.nextInt();
				Tools.generateMultipleRandomJulia(howMany, pngMode, color);
				break;
			case 6:
				System.out.println("How many random fractals to generate?");
				howMany=keyboardScanner.nextInt();
				Tools.generateMultipleRandomJuliaMultithreaded(howMany, pngMode, color, 8);
				break;
			case 7:
				terminate=true;
				keyboardScanner.close();
				break;
			}
			Tools.clearConsole();
			
		}
		
		
	}
	
	
	
	static void switchColors() {
		if(color==ColorEnum.RED) {
			color=ColorEnum.BLUE;
		}else if(color==ColorEnum.BLUE) {
			color=ColorEnum.GREEN;
		}else if(color==ColorEnum.GREEN) {
			color=ColorEnum.RED;
		}
		
	}

}
