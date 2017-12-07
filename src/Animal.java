import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animal extends GameObject {
	protected Color colour;
	double age;
	double width;
	double height;
	public Animal(GraphicsContext gc, double x, double y, Color colour) {
		super(gc, x, y);
		this.colour = colour;
		// TODO Auto-generated constructor stub
	}
	public Animal clone(){
		return new Animal(gc,x,y,colour);
	}
	public void inherit(Animal parent){
		
		
		double red = (parent.colour.getRed() + colour.getRed())/2;
		double blue = (parent.colour.getBlue() + colour.getBlue())/2;
		double green = (parent.colour.getGreen() + colour.getGreen())/2;
		
		Color newColour = new Color(red,green,blue,1.0);
		colour = newColour;
		
		x = (x+parent.x)/2;
		y = (y+parent.y)/2;
	}

	public boolean pointCollides(double x, double y){
		boolean b1 = x > this.x;
		boolean b2 = x < this.x + age*width;
		boolean b3 = y > this.y;
		boolean b4 = y < this.y + age*height;
		System.out.println(b1+" "+b2+" "+b3+" "+b4);
		if(b1 && b2 && b3 && b4){
			return true;
		}
		return false;
	}
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}
	public double getAge() {
		// TODO Auto-generated method stub
		return age;
	}
	public double getWidth() {
		// TODO Auto-generated method stub
		return width;
	}

	public double getHeight() {
		// TODO Auto-generated method stub
		return height;
	}

}
