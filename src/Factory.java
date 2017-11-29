import javafx.scene.canvas.GraphicsContext;

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
			String colour;
			switch((int)(Math.random()*4)){
			case 1:
				colour = "black";
				break;
			case 2:
				colour = "brown";
				break;
			case 3:
				colour = "ginger";
				break;
			case 0:
				colour = "grey";
				break;
			default:
				colour = "brown";				
			}
			return new Cat(gc,(int)x,(int)y,colour,Math.random(),Math.random());
		}else if(discrim.equals("saucer")){
			return new Cat(gc, x, y,"brown",Math.random(),Math.random());
		}
		return null;
	}
}