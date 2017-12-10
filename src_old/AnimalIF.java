
public interface AnimalIF {
	public void update(float rate);
	public GameObject clone();
	public double getX();
	public double getY();
	public boolean pointCollides(double x, double y);
	public void inherit(AnimalIF animal);
}
