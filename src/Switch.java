import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

public class Switch extends Component {

	boolean switchedOn;
	public Switch(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		switchedOn = true;
		img = new Image("res/switchon.png");
		MenuItem switchButton = new MenuItem("Flip Switch");
		switchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				toggle();
			}
		});
		contextMenu.getItems().add(switchButton);
	}
	
	public void toggle(){
		switchedOn = !switchedOn;
		if(switchedOn){
			img = new Image("res/switchon.png");
		}else{
			img = new Image("res/switchoff.png");
		}
		calculatePower(0,null);
	}
	
	public float calculatePower(float curPower, Component startNode){
		if(!switchedOn){
			curPower = 0;
			startNode = this;
		}
		return super.calculatePower(curPower, startNode);
	}

}
