import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A {@link Component} mimicking a power source which adds power to a circuit
 * @author Kyle Watson
 *
 */
public class Cell extends Component {

	/**
	 * @param gc the {@link GraphicsContext} that the {@link Component} will be drawn to
	 * @param x position of the image
	 * @param y position of the image
	 */
	public Cell(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/battery.png");
		setPowerConsumption(new AddsPower());
	}
}
