package second_experience;

import java.util.Scanner;
class GeometricObject{
	public GeometricObject() {}
	public double getArea() {
		return 0;
	}
}

class Circle extends GeometricObject{
	private double radius;
	public Circle() {
		super();
	}
	public Circle(double radius) {
		super();
		this.radius = radius;
	}
	public double getArea() {
		return (Math.PI*radius*radius);
	}
}

class Rectangle extends GeometricObject{
	private double width;
	private double height;
	public Rectangle() {
		super();
	}
	public Rectangle(double width,double height) {
		super();
		this.width = width;
		this.height = height;
	}
	public double getArea() {
		return (width*height);
	}
}
public class CircleandRectangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner cin = new Scanner(System.in);
		while(cin.hasNext()) {
			char m = cin.next().charAt(0);
			if(m == 'c') {
				double radius=cin.nextDouble();
				Circle circle=new Circle(radius);
				double S=circle.getArea();
				System.out.println(String.format("%.2f",S));
			}
			if(m == 'r') {
				double width=cin.nextDouble();
				double height=cin.nextDouble();
				Rectangle rectangle=new Rectangle(width,height);
				double V=rectangle.getArea();
				System.out.println(String.format("%.2f",V));
			}
		}
		cin.close();
	}
	
}
