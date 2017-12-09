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
public class Dog extends Animal {
	/**
	 * @param gc
	 * @param x
	 * @param y
	 */
	public Dog(GraphicsContext gc, double x, double y, Color colour) {
		super(gc, x, y, colour);
		width = 60;
		height = 30;
		update();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(){
		gc.setFill(colour);
		gc.fillRect(x, y, age*width, age*height);
		//super.update();
	}
}
