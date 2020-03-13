package fractals;

import java.awt.image.BufferedImage;

public class MandelbrotThread extends Thread {

	BufferedImage commonImage;
	int threadCount;
	int threadId;
	int iterations;
	ColorEnum colorValue;
	double jumpsX;
	double jumpsY;
	double leftX;
	double rightX;
	double topY;
	double bottomY;
	int pixelsX;
	int pixelsY;
	public MandelbrotThread(BufferedImage commonImage, int threadCount, int threadId, ColorEnum colorValue, double jumpsX, double jumpsY, double leftX, double rightX, double topY, double bottomY, int pixelsX, int pixelsY, int iterations) {
		this.commonImage=commonImage;
		this.threadCount=threadCount;
		this.threadId=threadId;
		this.colorValue = colorValue;
		this.jumpsX=jumpsX;
		this.jumpsY=jumpsY;
		this.leftX=leftX;
		this.rightX=rightX;
		this.topY=topY;
		this.bottomY=bottomY;
		this.pixelsX=pixelsX;
		this.pixelsY=pixelsY;
		this.iterations=iterations;
	}
	
	
	public void run() {
		
		double currentImaginary = topY-(threadId*jumpsY);
		ImaginaryNumber zero = new ImaginaryNumber(0,0);
		System.out.println("Hello, this is thread #"+threadId+" here, I'm running!");
		
		
		for(int y=(0+threadId); y<pixelsY; y+=threadCount) {
			for(int x=0; x<pixelsX; x++) {
				double currentReal = leftX+(jumpsX*x);
				currentImaginary = topY-(jumpsY*y);
				ImaginaryNumber currentNumber=new ImaginaryNumber(currentReal,currentImaginary);
				int goesToInfinity = Tools.iteratePoint(zero,currentNumber,iterations);
				int imageColor = Tools.getIntColor(goesToInfinity, colorValue);
				commonImage.setRGB(x, y, imageColor);
			}
		}
	}
}
