/*
* Game Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class GameScreen extends Screen
{
	public GameScreen(int width, int height, Color c){
		super(width, height, c);
	}

	public void draw(){
		ClearBackground(getBackgroundColor());

		DrawText("Game", 280, 280, 20, LIGHTGRAY);
	}

}
