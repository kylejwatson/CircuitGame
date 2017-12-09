import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Battery extends Component {

	public Battery(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/battery.png");
	}

	public float calculatePower(float curPower, Component startNode){
		if(startNode != null)
			curPower += 10;
		return super.calculatePower(curPower, startNode);
	}

}
