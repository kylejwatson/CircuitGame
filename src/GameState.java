import java.util.ArrayList;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import javafx.scene.canvas.GraphicsContext;

public class GameState{
	private Component heldComponent;
	private ArrayList<GameObject> list;
	private static GameState instance;
	private GameState(){
		 list = new ArrayList<GameObject>();
	}
	public static GameState getGameState(){
		if(instance == null){
			instance = new GameState();
		}
		return instance;
	}
	public ArrayList<GameObject> getList(){
		return list;
	}
	public void moveHeldComponent(double x, double y){
		if(heldComponent != null){
			heldComponent.x = x;
			heldComponent.y = y;
		}
	}
	public void setHeldComponent(Component co){
		heldComponent = co;
	}
	
	public void dropHolding(){
		heldComponent = null;
	}
}
