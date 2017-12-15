//Factory Design Pattern
import javafx.scene.canvas.GraphicsContext;

interface FactoryIF { 
	GameObject createProduct(String discrim, double x, double y);
}

class Factory implements FactoryIF {

	private GraphicsContext gc;
	private static Factory instance;
	private Factory() {
		super();
	}

	public static Factory getFactory(){
		if(instance == null)
			instance = new Factory();
		return instance;
	}
	public void setGraphicsContext(GraphicsContext gc){
		this.gc = gc;
	}
	@Override
	public GameObject createProduct(String discrim, double x, double y) {
		GameObject go = null;
		switch(discrim){//add to gamestate holding 
		case "cell":
			go = new Cell(gc,x,y);
			break;
		case "bulb":
			go = new Bulb(gc,x,y);
			break;
		case "switch":
			go = new Switch(gc,x,y);
			break;
		case "buzzer":
			go = new Buzzer(gc,x,y);
			break;
		case "motor":
			go = new Motor(gc,x,y);
			break;
		case "prof":
			go = new Tutor(gc,x,y);
			break;
		}
		if(go != null){
			GameState.getGameState().addGameObject(go);
			return go;
		}
		return new GameObject(gc,0,0);
	}
}