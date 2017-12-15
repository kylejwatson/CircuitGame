import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
//make null object
interface LessonIF{
	public boolean goalReached();
	public void init();
	public void update(double x,double y,GraphicsContext gc);
}
public abstract class Lesson implements LessonIF{
	private Image image;
	private ArrayList<String> paragraph = new ArrayList<String>();
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
