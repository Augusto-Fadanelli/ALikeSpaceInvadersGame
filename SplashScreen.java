/*
* Splash Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class SplashScreen extends Screen
{
	public SplashScreen(int width, int height, Color c){
		super(width, height, c);
	}

	public void draw(){
		ClearBackground(getBackgroundColor());

		DrawText("A like", 180, 155, 20, LIGHTGRAY);
		DrawText("SPACE INVADERS", 170, 170, 50, LIGHTGRAY);
		DrawText("game!", 570, 209, 20, LIGHTGRAY);
		DrawText("Press ENTER to continue...", 260, 300, 20, RAYWHITE);
		DrawText("Developed by: Augusto Fadanelli", 10, 430, 15, RAYWHITE);
	}

}
