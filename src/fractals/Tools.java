package fractals;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
public class Tools {
	static int RGBtoInt(int red, int green, int blue) {
		int rgb=red;
		rgb = (rgb << 8) +green;
		rgb = (rgb << 8) +blue;
		return rgb;
	}
	public static int iteratePoint(ImaginaryNumber Z, ImaginaryNumber C, int iterations) {
		ImaginaryNumber temp=Z;
		
		double R=C.calculateR();
		
		for(int i=0;i<iterations;i++) {

			temp=temp.square();
			temp=temp.add(C);
			if(temp.getNorm()>R) {
				return i;
			}
		}
		
		return -1;
	}
	
	private static int getPointIntensity(int goesToInfinity) {
		
		if(goesToInfinity*3>255) {
			return 255;
		}else {
			return goesToInfinity*3;
		}
	}
	
	public static BufferedImage generateJulia(double leftX,double topY, double rightX, double bottomY, int pixelsX, int pixelsY, int iterations, ImaginaryNumber C, ColorEnum colorValue) {
		Instant start = Instant.now();
		BufferedImage image = new BufferedImage(pixelsX,pixelsY,BufferedImage.TYPE_INT_RGB);
		
		double distanceX = Math.abs(rightX-leftX);
		double distanceY=Math.abs(topY-bottomY);
		double jumpsX=distanceX/pixelsX;
		double jumpsY=distanceY/pixelsY;
		ImaginaryNumber currentNumber;
		System.out.println("JumpsX: "+jumpsX);
		System.out.println("JumpsY: "+jumpsY);
		int intWhite = RGBtoInt(255,255,255);
		
		for(int y =0;y<pixelsY;y++) {
			for(int x=0;x<pixelsX;x++) {
				double currentReal = leftX+(jumpsX*x);
				double currentImaginary = topY-(jumpsY*y);
				currentNumber=new ImaginaryNumber(currentReal,currentImaginary);
				int goesToInfinity = iteratePoint(currentNumber,C,iterations);
				if(goesToInfinity!=-1) {
					int imageColor = getIntColor(goesToInfinity, colorValue);
					image.setRGB(x,y,imageColor);
				}else {
					image.setRGB(x, y, intWhite);
				}
				
				
			}
		}
		Instant finish = Instant.now();
		System.out.println("Time it took to generate image: "+Duration.between(start, finish).toMillis());
		return image;
	}
	
	public static BufferedImage generateMandelbrot(int pixelsX, int pixelsY, int iterations, ColorEnum colorValue) {
		Instant start = Instant.now();
		BufferedImage image = new BufferedImage(pixelsX,pixelsY,BufferedImage.TYPE_INT_RGB);
		double leftX = -3;
		double rightX=3;
		double topY=2;
		double bottomY=-2;
		
		double distanceX = Math.abs(rightX-leftX);
		double distanceY = Math.abs(topY-bottomY);
		double jumpsX=distanceX/pixelsX;
		double jumpsY=distanceY/pixelsY;
		int intWhite = RGBtoInt(255,255,255);
		ImaginaryNumber zero = new ImaginaryNumber(0,0);
		for(int y=0; y<pixelsY; y++) {
			for(int x=0; x<pixelsX; x++) {
				double currentReal = leftX+(jumpsX*x);
				double currentImaginary = topY-(jumpsY*y);
				ImaginaryNumber currentNumber=new ImaginaryNumber(currentReal,currentImaginary);
				int goesToInfinity = iteratePoint(zero,currentNumber,iterations);
				if(goesToInfinity!=-1) {
					int imageColor = getIntColor(goesToInfinity, colorValue);
					image.setRGB(x,y,imageColor);
				}else {
					image.setRGB(x, y, intWhite);
				}
			}
		}
		
		Instant finish = Instant.now();
		System.out.println("Time it took to generate image: "+Duration.between(start, finish).toMillis());
		return image;
	}
	
	
	public static int getIntColor(int goesToInfinity, ColorEnum colorValue) {
		
		if(goesToInfinity==-1) {
			return 0;
		}
		
		int keyIntensity = getPointIntensity(goesToInfinity);
		switch(colorValue) {
		case RED:
			return RGBtoInt(keyIntensity,0,0);
		case GREEN:
			return RGBtoInt(0,keyIntensity,0);
			
		case BLUE:
			return RGBtoInt(0,0,keyIntensity);
		default:
			return 0;
		}
		
		
		
		
	}
	
	/* THIS DOESN'T WORK BECAUSE JAVA DOESN'T ALLOW MULTIPLE THREADS TO WRITE TO THE SAME BUFFERED IMAGE AT THE SAME TIME, WHICH MAKES THIS SLOWER THAN THE "NORMAL" APPROACH
	
	
	
	public static BufferedImage multithreadedMandelbrot(int pixelsX, int pixelsY, int iterations, int threads, ColorEnum colorValue) throws InterruptedException {
		Instant start = Instant.now();
		
		double leftX = -3;
		double rightX=3;
		double topY=2;
		double bottomY=-2;
		
		double distanceX = Math.abs(rightX-leftX);
		double distanceY = Math.abs(topY-bottomY);
		double jumpsX=distanceX/pixelsX;
		double jumpsY=distanceY/pixelsY;
		BufferedImage image = new BufferedImage(pixelsX,pixelsY,BufferedImage.TYPE_INT_RGB);
		//Thread IDs start from 0 here!
		
		List<MandelbrotThread> lista = new LinkedList<MandelbrotThread>();
		for(int i=0;i<threads;i++) {
			
			MandelbrotThread currentThread = new MandelbrotThread(image, threads, i,colorValue, jumpsX, jumpsY, leftX, rightX, topY, bottomY, pixelsX, pixelsY, iterations);
			currentThread.start();
			lista.add(currentThread);
		}
		
		
		for(Thread temp : lista) {
			temp.join();
		}
		
		Instant finish = Instant.now();
		System.out.println("Time it took to generate image with multithreading: "+Duration.between(start, finish).toMillis());
		return image;
	}
	*/
	
	public static void clearConsole() {
		   System.out.print("\033[H\033[2J");  
		   System.out.flush();
	}
	
	public static void generateMultipleRandomJulia(int count, boolean pngMode, ColorEnum color) throws IOException {
		for(int i=0; i<count;i++) {
			double randomX = ThreadLocalRandom.current().nextDouble(-1, 1);
			double randomY = ThreadLocalRandom.current().nextDouble(-1, 1);
			
			
			ImaginaryNumber C = new ImaginaryNumber(randomX,randomY);
			BufferedImage outputBufferedImage = Tools.generateJulia(-2,2,2,-2,1600,1600,100,C, color);
			if(pngMode) {
				String fileName = randomX+"+"+randomY+"i"+".png";
				File outputFile = new File(fileName);
				ImageIO.write(outputBufferedImage,"png",outputFile);
			}else {
				String fileName = randomX+"+"+randomY+"i"+".jpg";
				File outputFile = new File(fileName);
				ImageIO.write(outputBufferedImage,"jpg",outputFile);
			}
			
		}
	}
}
