package fractals;

public class ImaginaryNumber {

	private double realPart;
	private double imaginaryPart; //The "i" is implicitly included
	
	public ImaginaryNumber(double realPart, double imaginaryPart) {
		this.realPart=realPart;
		this.imaginaryPart=imaginaryPart;
	}
	
	public double getRealPart() {
		return realPart;
	}
	
	public double getImaginaryPart() {
		return imaginaryPart;
	}
	
	public void setRealPart(double value) {
		this.realPart=value;
	}
	
	public void setImaginaryPart(double value) {
		this.imaginaryPart=value;
	}
	
	
	public ImaginaryNumber multiplyBy(ImaginaryNumber other) {
		
		double a =this.getRealPart();
		double b = this.getImaginaryPart();
		double c = other.getRealPart();
		double d = other.getImaginaryPart();
		
		double outputReal=((a*c)-(b*d));
		double outputImaginary=((a*d)+(b*c));	
		
		
		ImaginaryNumber output = new ImaginaryNumber(outputReal,outputImaginary);
		return output;
	}
	
	public ImaginaryNumber square() {
		double a =this.getRealPart();
		double b = this.getImaginaryPart();
		
		double outputReal=((a*a)-(b*b));
		double outputImaginary=2*a*b;	
		
		
		ImaginaryNumber output = new ImaginaryNumber(outputReal,outputImaginary);
		return output;
	}
	
	public String toString() {
		String real = Double.toString(this.getRealPart());
		String imaginary = Double.toString(this.getImaginaryPart());
		
		return(real+"+"+imaginary+"i");
	}
	
	public ImaginaryNumber add(ImaginaryNumber other) {
		double a =this.getRealPart();
		double b = this.getImaginaryPart();
		double c = other.getRealPart();
		double d = other.getImaginaryPart();
		
		double outputReal = a+c;
		double outputImaginary = b+d;		
		ImaginaryNumber output = new ImaginaryNumber(outputReal,outputImaginary);
		return output;
	}
	
	public double getNorm() {
		double a =this.getRealPart();
		double b = this.getImaginaryPart();
		
		return Math.sqrt(a*a+b*b);
	}
	
	public double calculateR() {
		double norm = this.getNorm();
		return ((1+Math.sqrt(1+(4*norm)))/2);
	}
}
