import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * A {@link Component} mimicking a light-bulb that overlays a transparent yellow circle above its symbol dependent on its {@code power}
 * @author Kyle Watson
 */
public class Bulb extends Component {

	/**
	 * @param gc the {@link GraphicsContext} that the component will be drawn to
	 * @param x position of the image
	 * @param y position of the image
	 */
	public Bulb(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/bulb.png");
		// TODO Auto-generated constructor stub
		setPowerConsumption(new ConsumesPower());
	}
	
	/**
	 * Overlays the yellow circle to mimic light emissions
	 */
	private void shine(){
		gc.setFill(new Color(1,1,0,power/20));
		gc.fillOval(x+5, y+5, 20, 20);		
	}
	
	public void update(){
		shine();
		super.update();
	}
}
