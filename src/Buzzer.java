
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Buzzer extends Component {


	private Image soundImg = new Image("res/sound.png");
	
	public Buzzer(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		// TODO Auto-generated constructor stub
		img = new Image("res/buzzer.png");
		setPowerCons(new ConsumesPower());
	}
	
	public void buzz(){
		if(power != 0)
			SoundThread.getSoundThread().playSound();
		gc.drawImage(soundImg, x, y-power,30,power);		
	}

	public void update(){
		buzz();
		super.update();
	}
}
