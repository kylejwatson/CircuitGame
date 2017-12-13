import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Motor extends Component {
	private float angle = 0;
	public Motor(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		img = new Image("res/motor.png");
		setPowerCons(new ConsumesPower());
	}
	
	public void spin(){
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
