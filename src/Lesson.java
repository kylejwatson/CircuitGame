import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 * Displays instructions and checks when the instructions have been completed
 * @author Kyle Watson
 *
 */
interface LessonIF{
	/**
	 * @return if the instructions have been met
	 */
	public boolean goalReached();
	/**
	 * Sets up the sandbox to complete the instructions
	 */
	public void init();
	/**
	 * Displays the instructions 
	 * @param x position to display instructions
	 * @param y position to display instructions
	 * @param gc {@link GraphicsContext} to draw the instructions to
	 */
	public void update(double x,double y,GraphicsContext gc);
}
public abstract class Lesson implements LessonIF{
	private Image image;
	private ArrayList<String> paragraph = new ArrayList<String>();
	/**
	 * @param instructions to direct the user toward the goal
	 */
	public Lesson(String instructions) {
		image = new Image("res/speech.png");
		if(instructions.length() > 25){
			String curLine = "";
			String[] wordHolder = instructions.split("\\s+");
			for(String s:wordHolder){
				if(curLine.length() + s.length() + 1 > 25){
					paragraph.add(curLine);
					curLine = "";
				}
				curLine += " " + s;
			}
			paragraph.add(curLine);
		}else{
			paragraph.add(instructions);
		}
	}
	public void update(double x, double y, GraphicsContext gc){
		gc.drawImage(image, x, y-20*paragraph.size(), 200,20*paragraph.size() + 20);
		for(int i = 0; i < paragraph.size(); i++)
			gc.strokeText(paragraph.get(i), x+20,20+ y+i*20 - 20*paragraph.size());
	}
}
/**
 * {@link Lesson} used in place of null so its not need to be checked and stop {@link NullPointerException}s
 * @author Kyle Watson
 *
 */
class NullLesson implements LessonIF{

	@Override
	public boolean goalReached() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double x, double y, GraphicsContext gc) {
		// TODO Auto-generated method stub
		
	}
}
