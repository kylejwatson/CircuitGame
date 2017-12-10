import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Switch extends Component {

	boolean switchedOn;
	public Switch(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		switchedOn = true;
		img = new Image("res/switchon.png");
		// TODO Auto-generated constructor stub
	}
	
	public void toggle(){
		switchedOn = !switchedOn;
		if(switchedOn){
			img = new Image("res/switchon.png");
		}else{
			img = new Image("res/switchoff.png");
		}
	}

	public void rightClick(double x, double y){
		if(click(x,y))
			toggle();
	}
	
	public float calculatePower(float curPower, Component startNode){
		if(!switchedOn){
			curPower = 0;
			startNode = this;
		}
		return super.calculatePower(curPower, startNode);
	}

}
