import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Bulb extends Component {

	public Bulb(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/bulb.png");
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		super.update();
		gc.setFill(new Color(1,1,0,power/50));
		gc.fillOval(x+5, y+5, 20, 20);
	}

	public float calculatePower(float curPower, Component startNode){
		if(startNode != null)
			curPower -= 3;
		return super.calculatePower(curPower, startNode);
	}
}
