import java.util.ArrayList;
public class GameState{
	private Component heldComponent;
	private ArrayList<GameObject> list;
	private ArrayList<GameObject> delList = new ArrayList<GameObject>();
	private ArrayList<GameObject> addList = new ArrayList<GameObject>();
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
	public void removeGameObject(GameObject go){
		delList.add(go);
	}
	public void clearComponents(){
		for(GameObject go:list){
			if(go instanceof Component)
				delList.add(go);
		}
	}
	public void addGameObject(GameObject go){
		addList.add(go);
	}
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
	public void setHeldComponent(Component co){
		heldComponent = co;
	}
	
	public void dropHolding(){
		heldComponent = null;
	}
}
