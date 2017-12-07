import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

interface FactoryIF { 
	GameObject createProduct(String discrim, double x, double y);
}

class Factory implements FactoryIF {

	GraphicsContext gc;
	
	public Factory(GraphicsContext gc) {
		super();
		this.gc = gc;
	}

	@Override
	public GameObject createProduct(String discrim, double x, double y) {
		if(discrim.equals("cat")){
			Color colour = new Color(Math.random(),Math.random(),Math.random(),1.0);
			return new Cat(gc,(int)x,(int)y,colour);
			
		}else if(discrim.equals("saucer")){
			return new Cat(gc, x, y,Color.ALICEBLUE);
		}
		return null;
	}
}