import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * 
 */

/**
 * @author Kyle
 *
 */
public class Cat extends GameObject implements AnimalIF {
	/**
	 * @param gc
	 * @param x
	 * @param y
	 */
	static float width = 60;
	static float height =30;
	float age;
	Color colour;
	public Cat(GraphicsContext gc, double x, double y, Color colour) {
		super(gc, x, y);
		this.colour = colour;
		age = 1;
		update();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(){
		gc.setFill(colour);
		gc.fillOval(x, y, age*width, age*height);
		//super.update();
	}

	public Cat clone(){
		return new Cat(gc,x,y,colour);
	}
	public Cat inherit(Animal parent){
		//if(parent.getClass() == this.getClass()){
			Cat offspring = this.clone();
			
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

	@Override
	public void update(float rate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pointCollides(double x, double y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void inherit(AnimalIF animal) {
		// TODO Auto-generated method stub
		
	}

}
