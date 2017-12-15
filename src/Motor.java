import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A {@link Component} mimicking a motor that spins a different speeds according to {@code power}
 * @author Kyle Watson
 *
 */
public class Motor extends Component {
	private float angle = 0;

	/**
	 * @param gc the {@link GraphicsContext} that the {@link Component} will be drawn to
	 * @param x position of the image
	 * @param y position of the image
	 */
	public Motor(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/motor.png");
		setPowerConsumption(new ConsumesPower());
	}
	
	/**
	 * Spins a line around the symbol with speed dependent of {@code power}
	 */
	private void spin(){
		angle += power/100;
		double xpoint = Math.cos(angle)*10;
		double ypoint = Math.sin(angle)*10;
		gc.setLineWidth(2);
		gc.strokeLine(xpoint + x+15, ypoint + y+15, xpoint*1.5 + x+15, ypoint*1.5 + y+15);
		gc.strokeLine(x+15 - xpoint, y+15 - ypoint,x+15 - xpoint*1.5, y+15 - ypoint*1.5);
		gc.setLineWidth(1);
	}
	
	public void update(){
		spin();
		super.update();
	}
}
