import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Motor extends Component {
	float angle = 0;
	public Motor(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/motor.png");
		// TODO Auto-generated constructor stub
	}
	
	public void update(){
		angle += power/100;
		double xpoint = Math.cos(angle)*10;
		double ypoint = Math.sin(angle)*10;
		gc.setLineWidth(2);
		gc.strokeLine(xpoint + x+15, ypoint + y+15, x - xpoint+15, y - ypoint+15);
		gc.setLineWidth(1);
		super.update();
	}

	public float calculatePower(float curPower, Component startNode){
		if(startNode != null)
			curPower -= 3;
		return super.calculatePower(curPower, startNode);
	}

}
