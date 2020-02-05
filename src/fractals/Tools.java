package fractals;
import java.awt.image.BufferedImage;
import java.lang.Math;
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
	
	private static int getRedIntensity(int goesToInfinity) {
		if(goesToInfinity*3>255) {
			return 255;
		}else {
			return goesToInfinity*3;
		}
	}
	
	public static BufferedImage generateBlackAndWhiteJulia(double leftX,double topY, double rightX, double bottomY, int pixelsX, int pixelsY, int iterations, ImaginaryNumber C) {
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
					int blackDensity = getRedIntensity(goesToInfinity);
					int imageColor = RGBtoInt(blackDensity,0,0);
					image.setRGB(x,y,imageColor);
				}else {
					image.setRGB(x, y, intWhite);
				}
				
				
			}
		}
		
		return image;
	}
	
}
