import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameState{
	private static Component heldComponent;
	private static boolean holding = false;
	private static ArrayList<GameObject> list = new ArrayList<GameObject>();
	private static float volume;
    private static SourceDataLine sdl;
	private static Factory factory;
	private static Runnable soundThread;

	public static ArrayList<GameObject> getList(){
		return list;
	}
	
	public static Factory getFactory(){
		return factory;
	}
	
	public static Component getHeldComponent(){
		return heldComponent;
	}
	
	public static void setHeldComponent(Component co){
		heldComponent = co;
		holding = true;
	}
	
	public static  boolean isHolding(){
		return holding;
	}
	
	public static void dropHolding(){
		holding = false;
	}
	
	static void setVolume(float volume){
		byte[] buf = new byte[ 1 ];
		AudioFormat af= new AudioFormat( (float )44100, 8, 1, true, false );
		if(sdl == null){
		    try {
				sdl = AudioSystem.getSourceDataLine( af );
			
		    } catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		soundThread = new Runnable(){
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
			        buf[ 0 ] = (byte )( Math.sin( angle ) * GameState.volume);
			        sdl.write( buf, 0, 1 );
			    }
				sdl.drain();
				sdl.close();
			}
			
		};
	}

	public static void setGraphicsContext(GraphicsContext gc) {
		factory = new Factory(gc);
		
	}

	public static Runnable getSoundThread() {
		// TODO Auto-generated method stub
		return soundThread;
	}
}
