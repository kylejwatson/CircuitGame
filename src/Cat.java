import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * 
 */

/**
 * @author Kyle
 *
 */
public class Cat extends GameObject implements AnimalIF {

	private String colour;
	double tailLength;
	double earHeight;
	Image tailImage;
	Image earImage;
	/**
	 * @param gc
	 * @param x
	 * @param y
	 */
	public Cat(GraphicsContext gc, double x, double y, String colour, double tailLength, double earHeight) {
		super(gc, x, y);
		System.out.println(x + " " + y);
		setColour(colour);
		this.earHeight = earHeight;
		this.tailLength = tailLength;
		tailImage = new Image("res/tail.png");
		earImage = new Image("res/ear.png");
		update();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Cat clone(){
		return new Cat(gc,x,y,colour,tailLength,earHeight);
	}
	
	public void setColour(String colour){
		this.colour = colour;
		img = new Image("res/cat-"+colour+".png",100,70,false,false);
	}
	
	@Override
	public void inherit(AnimalIF parent){
		Cat cParent = (Cat) parent;
		if(Math.random()>0.5){
			setColour(cParent.colour);
		}
		if(Math.random()>0.5){
			tailLength = cParent.tailLength;
		}
		if(Math.random()>0.5){
			earHeight = cParent.earHeight;
		}
		x = (cParent.x + x)/2;
		y = (cParent.y + y)/2;
	}
	
	@Override
	public boolean pointCollides(double x, double y){
		System.out.println(x + " " + this.x + " " + y + " " + this.y);
		boolean b1 = x > this.x;
		boolean b2 = x < this.x + 100;
		boolean b3 = y > this.y;
		boolean b4 = y < this.y + 70;
		System.out.println(b1+" "+b2+" "+b3+" "+b4);
		if(x > this.x && x < this.x + 100 && y > this.y && y < this.y + 70){
			return true;
		}
		return false;
	}
	
	public void update(){
		gc.drawImage(tailImage, x-60*tailLength, y, 60*tailLength, 60);
		gc.drawImage(earImage, x+80, y-20*earHeight, 20, 20*earHeight);
		super.update();
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}

}
