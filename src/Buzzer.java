
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Buzzer extends Component {
	static byte[] buf = new byte[ 1 ];
	static AudioFormat af= new AudioFormat( (float )44100, 8, 1, true, false );
    static SourceDataLine sdl;
    static float volume = 1;
    static Runnable soundthread = new Runnable(){

		@Override
		public void run() {

		    try {
				sdl.open();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    sdl.start();
			// TODO Auto-generated method stub
			for( int i = 0; i < 100 * (float )44100 / 1000; i++ ) {
		        double angle = i / ( (float )44100 / 440 ) * 2.0 * Math.PI;
		        buf[ 0 ] = (byte )( Math.sin( angle ) * 100* volume);
		        sdl.write( buf, 0, 1 );
		    }
			sdl.drain();
			sdl.close();
		}
		
	};
	static Thread t = new Thread(soundthread);
	
	public Buzzer(GraphicsContext gc, double x, double y) {
		super(gc, x, y);
		// TODO Auto-generated constructor stub
		img = new Image("res/buzzer.png");
		if(sdl == null){
		    try {
				sdl = AudioSystem.getSourceDataLine( af );
			
		    } catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void update(){
		super.update();
		if(!t.isAlive() && power != 0){
			t = new Thread(soundthread);
			t.start();
		}
		
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
