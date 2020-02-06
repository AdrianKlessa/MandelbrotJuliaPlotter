package fractals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Main {
	
	static boolean pngMode=false;
	static ColorEnum color = ColorEnum.RED;
	static boolean terminate=false;
	public static void main(String[] args) throws Exception {
		// ImaginaryNumber C = new ImaginaryNumber(0.3,0.6);
		Scanner keyboardScanner = new Scanner(System.in);
		String userInput;
		int inputInt;
		int horizontalRes;
		int verticalRes;
		double realPart;
		double imaginaryPart;
		File outputFile;
		String fileName;
		BufferedImage outputBufferedImage;
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
			System.out.println("5 - Exit");
			inputInt=keyboardScanner.nextInt();
			switch(inputInt) {
			case 1:
				System.out.println("Please provide the horizontal reslution");
				horizontalRes=keyboardScanner.nextInt();
				verticalRes = horizontalRes/3*2;
				outputBufferedImage = Tools.generateMandelbrot(horizontalRes,verticalRes,100);
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
				ImaginaryNumber C = new ImaginaryNumber(realPart,imaginaryPart);
				outputBufferedImage = Tools.generateBlackAndWhiteJulia(-2,2,2,-2,horizontalRes,verticalRes,100,C);
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
				terminate=true;
			}
			Tools.clearConsole();
			
		}
		
		/*
		
		ImaginaryNumber C = new ImaginaryNumber(0.285,0.01);
		// BufferedImage outputBufferedImage = Tools.generateBlackAndWhiteJulia(-2, 2, 2, -2, 16000, 16000, 100, C);
		BufferedImage outputBufferedImage = Tools.generateMandelbrot(18000,12000,100);
		
		File outputPNG = new File("output.png");
		try {
			ImageIO.write(outputBufferedImage,"png",outputPNG);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		*/
		
		
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
