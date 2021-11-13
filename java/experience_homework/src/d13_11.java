
public class d13_11 {
public static void main(String[] args) throws CloneNotSupportedException  {
  Octagon o=new Octagon(5);
     System.out.println(o.getArea());
     System.out.println(o.getPerimeter());
     Octagon a2=(Octagon)o.clone();
     System.out.println(o.compareTo(a2));


 
}
}
  class Octagon extends GeometricObject implements Cloneable,Comparable<Octagon>{
 private double side;
 Octagon(){
  this.side=0;
 }
 Octagon(double side){
  this.side=side;
 }
 
 public double getArea() {
  double s=(2+4/Math.sqrt(2))*side*side;
  return s;
 }
 public double getPerimeter() {
  return 8*side;
 }
 
 @Override
 public Object clone() throws CloneNotSupportedException{
  
   return super.clone();
  
  
  }
   
 @Override
 public int compareTo(Octagon o) {
  if(this.getArea()>o.getArea()) {
   return 1;
  }
  else if(this.getArea()<o.getArea()) {
   return -1;
  }
  else {
   return 0;
  }
 }

}
  
  abstract class GeometricObject{
	    String color="white";
	    boolean filled;
	    java.util.Date dateCreated;
	public GeometricObject(){
	dateCreated=new java.util.Date(); 
	}
	public GeometricObject(String color,boolean filled){
	dateCreated=new java.util.Date();
	this.color=color;
	this.filled=filled;
	}
	public String getColor(){
	return color;
	}
	public void setColor(String color){
	this.color=color;
	}
	public boolean isFilled(){
	return filled;
	}
	public void setFilled(boolean filled){
	this.filled=filled;
	}
	public abstract double getArea();
	public abstract double getPerimeter();
	}
  
