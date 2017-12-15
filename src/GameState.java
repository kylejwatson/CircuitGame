import java.util.ArrayList;
/**
 * Holds all global variables for the game mainly the display list and maintenance of the list
 * @author Kyle Watson
 *
 */
public class GameState{
	private Component heldComponent;
	private ArrayList<GameObject> list;
	private ArrayList<GameObject> delList = new ArrayList<GameObject>();
	private ArrayList<GameObject> addList = new ArrayList<GameObject>();
	private static GameState instance;
	/**
	 * Surpressed constructor to enable singleton design
	 */
	private GameState(){
		 list = new ArrayList<GameObject>();
	}
	/**
	 * @return single instance of {@link GameState}
	 */
	public static GameState getGameState(){
		if(instance == null){
			instance = new GameState();
		}
		return instance;
	}
	/**
	 * @return display list to update all drawn {@link GameObject}s
	 */
	public ArrayList<GameObject> getList(){
		return list;
	}
	/**
	 * Moves the currently held {@link Component} to the new location
	 * @param x position to move to
	 * @param y position to move to
	 */
	public void moveHeldComponent(double x, double y){
		if(heldComponent != null){
			heldComponent.x = x;
			heldComponent.y = y;
		}
	}
	/**
	 * @param go {@link GameObject} to delete at the end of the frame
	 */
	public void removeGameObject(GameObject go){
		delList.add(go);
	}
	/**
	 * Removes all {@link Component}s at the end of the frame
	 */
	public void clearComponents(){
		for(GameObject go:list){
			if(go instanceof Component)
				delList.add(go);
		}
	}
	/**
	 * @param go {@link GameObject} to add at the end of the frame
	 */
	public void addGameObject(GameObject go){
		addList.add(go);
	}
	/**
	 * Deletes and adds all required {@link GameObject}s (to be called at the end of a frame/loop)
	 */
	public void flush(){
		for(GameObject go:delList){
			list.remove(go);
		}
		for(GameObject go:addList){
			list.add(go);
		}
		delList.clear();
		addList.clear();
	}
	/**
	 * @param co {@link Component} to be set to be held
	 */
	public void setHeldComponent(Component co){
		heldComponent = co;
	}
	
	/**
	 * Drop any held {@link Component} if any
	 */
	public void dropHolding(){
		heldComponent = null;
	}
}
