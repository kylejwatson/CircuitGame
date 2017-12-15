import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import sun.applet.Main;

public class SoundThread implements Runnable {
	private SourceDataLine sdl;
	private AudioFormat af;
	private float volume;
	private Thread t;
	private static SoundThread instance;
	private Clip clip;
	private SoundThread() {
		af = new AudioFormat( (float )44100, 8, 1, true, false );
		 try {
				sdl = AudioSystem.getSourceDataLine( af );
				clip = AudioSystem.getClip();
		        AudioInputStream inputStream = AudioSystem.getAudioInputStream(
		  	          Main.class.getResourceAsStream("/res/success.wav"));
		        clip.open(inputStream);
			
		    } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 t = new Thread(instance);
	}
	public static SoundThread getSoundThread(){
		if(instance == null)
			instance = new SoundThread();
		return instance;
	}
	public void playSound(){
		if(!t.isAlive()){
			t = new Thread(instance);
			t.start();
		}
	}
	public void setVolume(float volume){
		this.volume = volume;
	}
	public void playSuccess() {
		/*
		 * Solution for playing audio clip from pek 2008
		 * https://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
		 */
		if(!clip.isRunning()){
			clip.setFramePosition(0);
			clip.start();
		}
	}
	@Override
	public void run() {
		/*
		 * Solution for generating beep from tangens 2009
		 * https://stackoverflow.com/questions/1932490/java-generating-sound/1932537#1932537
		 */
		byte[] buf = new byte[ 1 ];
		 try {
				sdl.open();
			} catch (LineUnavailableException e) {
				e.printStackTrace();
			}
		    sdl.start();
			for( int i = 0; i < 100 * (float )44100 / 1000; i++ ) {
		        double angle = i / ( (float )44100 / 440 ) * 2.0 * Math.PI;
		        buf[ 0 ] = (byte )( Math.sin( angle ) * volume);
		        sdl.write( buf, 0, 1 );
		    }
			sdl.drain();
			sdl.close();
	}
}
