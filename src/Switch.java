import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;

/**
 * A {@link Component} mimicking a switch that breaks the circuit when toggled off
 * @author Kyle Watson
 *
 */
public class Switch extends Component {

	private boolean switchedOn;
	private Image onImage;
	private Image offImage;
	/**
	 * @param gc the {@link GraphicsContext} that the {@link Component} will be drawn to
	 * @param x position of the image
	 * @param y position of the image
	 */
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
	
	/**
	 * Changes the state of the switch on/off
	 */
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
