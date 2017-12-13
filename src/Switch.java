import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

public class Switch extends Component {

	private boolean switchedOn;
	private Image onImage;
	private Image offImage;
	public Switch(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		switchedOn = true;
		onImage = new Image("res/switchon.png");
		offImage = new Image("res/switchoff.png");
		img = onImage;
		setPowerConsumption(new NoPower());
		MenuItem switchButton = new MenuItem("Flip Switch");
		switchButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				toggle();
			}
		});
		contextMenu.getItems().add(switchButton);
	}
	
	private void toggle(){
		switchedOn = !switchedOn;
		if(switchedOn){
			img = onImage;
		}else{
			img = offImage;
		}
		resetPower();
	}
	
	public float calculatePower(float curPower, Component startNode){
		if(!switchedOn){
			curPower = 0;
			startNode = this;
		}
		return super.calculatePower(curPower, startNode);
	}

}
