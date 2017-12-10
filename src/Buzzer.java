
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Buzzer extends Component {


	Image soundImg = new Image("res/sound.png");
	static Thread t = new Thread(GameState.soundThread);
	
	public Buzzer(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		// TODO Auto-generated constructor stub
		img = new Image("res/buzzer.png");
	}

	public void update(){
		super.update();
		if(!t.isAlive() && power != 0){
			t = new Thread(GameState.soundThread);
			t.start();
		}
		gc.drawImage(soundImg, x, y-power,30,power);
	}
	
	public void setPower(float power){
		super.setPower(power);
	}
	
	public float calculatePower(float curPower, Component startNode){
		if(startNode != null)
			curPower -= 3;
		return super.calculatePower(curPower, startNode);
	}
}
