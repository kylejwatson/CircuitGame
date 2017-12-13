
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

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

	public void update(){
		super.update();
		if(power != 0)
			SoundThread.getSoundThread().playSound();
		gc.drawImage(soundImg, x, y-power,30,power);
	}
}
