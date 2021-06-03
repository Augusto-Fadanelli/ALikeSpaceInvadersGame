/*
* Screen configurations
*/

import static com.raylib.Jaylib.*; //for InitWindow(); and Color type

public abstract class Screen
{
	private int screen[] = new int[2];
	private Color backgroundColor;	

	public Screen(int width, int height, Color c){
		this.screen[0] = width;
		this.screen[1] = height;
		this.backgroundColor = c;
	}

	public void initWindow(){
		InitWindow(this.screen[0], this.screen[1], "Space Invaders!");
		SetTargetFPS(60); // Set our game to run at 60 frames-per-second
	}

	public int getScreenWidth(){
		return this.screen[0];
	}
	public int getScreenHeight(){
		return this.screen[1];
	}

	public Color getBackgroundColor(){
		return this.backgroundColor;
	}

	//Position methods
	public int positionCentralize(int size, double proportionFor12){
		double position;
		position = (size - size /12 * proportionFor12)/2;
		return (int)position;
	}

	/*public int relativePosition(int screenSize, double position, double relativeP){
		double position;

		return (int)position;
	}*/

	//Abstract methods
	public abstract void draw();

}
