/*
* Screen configurations
*/

import static com.raylib.Jaylib.*; //for InitWindow(); and Color type

public class Screen
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
	}

	public int getScreenWidth(){
		return this.screen[0];
	}
	public int getScreenHeight(){
		return this.screen[1];
	}

	/*public void setBackgroundColor(Color c){
		this.backgroundColor = c;
	}*/
	public Color getBackgroundColor(){
		return this.backgroundColor;
	}

}
