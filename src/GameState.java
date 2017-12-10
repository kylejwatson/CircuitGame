import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.image.Image;

public abstract class GameState{
	static Component go;
	static boolean holding = false;
	static ArrayList<GameObject> list = new ArrayList<GameObject>();
	static float volume;
	static byte[] buf = new byte[ 1 ];
	static AudioFormat af= new AudioFormat( (float )44100, 8, 1, true, false );
    static SourceDataLine sdl;
    
    
	static Runnable soundThread;
	static void setVolume(float volume){
		System.out.println(GameState.volume = volume);
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
	
	static Factory factory;
}
