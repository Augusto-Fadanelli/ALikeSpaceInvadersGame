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

	//Position and Size methods
	public int positionCentralize(int screenSize, double proportionFor12){
		return (int)((screenSize - screenSize /12 * proportionFor12)/2);
	}

	public int size(int s){
		s /= 10;
		return (int)(s * this.screen[0] /64);
	}

	/*public int relativePosition(int screenSize, double position, double relativeP){
		double position;

		return (int)position;
	}*/

	//Abstract methods
	public abstract void draw();

}
