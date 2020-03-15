package fractals;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

public class JuliaThread extends Thread {
	int threadId;
	int overallCount;
	int currentCount;
	int threadCount;
	boolean pngMode;
	ColorEnum color;
	
	
	public JuliaThread(int threadId, int overallCount, int threadCount, boolean pngMode, ColorEnum color) {
		this.threadId=threadId;
		this.overallCount=overallCount;
		this.currentCount=threadId;
		this.threadCount = threadCount;
		this.pngMode=pngMode;
		this.color=color;
	}
	
	public void run() {
		for(int i=currentCount;i<=overallCount;i+=threadCount) {
			double randomX = ThreadLocalRandom.current().nextDouble(-1, 1);
			double randomY = ThreadLocalRandom.current().nextDouble(-1, 1);
			ImaginaryNumber C = new ImaginaryNumber(randomX,randomY);
			
			BufferedImage outputBufferedImage = Tools.generateJulia(-2,2,2,-2,1600,1600,100,C, color);
			
			if(pngMode) {
				String fileName = randomX+"+"+randomY+"i"+".png";
				File outputFile = new File(fileName);
				try {
					ImageIO.write(outputBufferedImage,"png",outputFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				String fileName = randomX+"+"+randomY+"i"+".jpg";
				File outputFile = new File(fileName);
				try {
					ImageIO.write(outputBufferedImage,"jpg",outputFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
