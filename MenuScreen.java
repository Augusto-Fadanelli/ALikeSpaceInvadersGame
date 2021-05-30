/*
* Menu Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class MenuScreen extends Screen
{
	public MenuScreen(int width, int height, Color c){
		super(width, height, c);
	}

	public void draw(){
		ClearBackground(getBackgroundColor());

		DrawText("Menu", 280, 280, 20, LIGHTGRAY);
	}

}
