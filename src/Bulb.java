import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bulb extends Component {

	public Bulb(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/bulb.png");
		// TODO Auto-generated constructor stub
		setPowerCons(new ConsumesPower());
	}
	
	public void update(){
		gc.setFill(new Color(1,1,0,power/20));
		gc.fillOval(x+5, y+5, 20, 20);
		super.update();
	}
}