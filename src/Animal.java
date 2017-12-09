import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animal extends GameObject {
	protected Color colour;
	double age;
	double width;
	double height;
	public Animal(GraphicsContext gc, double x, double y, Color colour) {
		super(gc, x, y);
		age = 1;
		this.colour = colour;
		// TODO Auto-generated constructor stub
	}
	public Animal clone(){
		return new Animal(gc,x,y,colour);
	}
	public Animal inherit(Animal parent){
		if(parent.getClass() == this.getClass()){
			Animal offspring = this.clone();
			
			double red = (parent.colour.getRed() + colour.getRed())/2;
			double blue = (parent.colour.getBlue() + colour.getBlue())/2;
			double green = (parent.colour.getGreen() + colour.getGreen())/2;
			
			Color newColour = new Color(red,green,blue,1.0);
			offspring.colour = newColour;
			
			offspring.x = (x+parent.x)/2;
			offspring.y = (y+parent.y)/2;
			System.out.println(offspring.getClass());
			return offspring;
		}
		return null;
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
	
}
