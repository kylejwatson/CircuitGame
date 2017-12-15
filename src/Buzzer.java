
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A {@link Component} mimicking a speaker to play a buzzing noise with audio and visual indication
 * @author Kyle Watson
 *
 */
public class Buzzer extends Component {
	private Image soundImg = new Image("res/sound.png");

	/**
	 * @param gc the {@link GraphicsContext} that the {@link Component} will be drawn to
	 * @param x position of the image
	 * @param y position of the image
	 */
	public Buzzer(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		// TODO Auto-generated constructor stub
		img = new Image("res/buzzer.png");
		setPowerConsumption(new ConsumesPower());
	}
	
	/**
	 * Shows an image representing the sound level of the buzzer dependent on {@code power} and plays a constant beep
	 */
	private void buzz(){
		if(power != 0)
			SoundThread.getSoundThread().playSound();
		gc.drawImage(soundImg, x, y-power,30,power);		
	}

	public void update(){
		buzz();
		super.update();
	}
}
