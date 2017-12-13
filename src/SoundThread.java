import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

public class SoundThread implements Runnable {
	private SourceDataLine sdl;
	private AudioFormat af= new AudioFormat( (float )44100, 8, 1, true, false );
	private float volume;
	private static SoundThread instance;
	private SoundThread() {
		 try {
				sdl = AudioSystem.getSourceDataLine( af );
			
		    } catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	public static SoundThread getSoundThread(){
		if(instance == null)
			instance = new SoundThread();
		return instance;
	}
	public void setVolume(float volume){
		this.volume = volume;
	}
	@Override
	public void run() {
		byte[] buf = new byte[ 1 ];
	
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
		        buf[ 0 ] = (byte )( Math.sin( angle ) * volume);
		        sdl.write( buf, 0, 1 );
		    }
			sdl.drain();
			sdl.close();
	}

}
