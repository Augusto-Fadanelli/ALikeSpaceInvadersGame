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

	}

	public void drawGameOver(){
		
		BeginDrawing();
            
            ClearBackground(getBackgroundColor());

			DrawText(
				"GAME OVER!", 
				positionCentralize(getScreenWidth(), 6), 
				(int)(getScreenHeight()/8*2), 
				size(50), 
				RAYWHITE);

			DrawText(
				"Score Player 1:", 
				positionCentralize(getScreenWidth(), 2.9), 
				(int)(getScreenHeight()/8*2 + size(80)), 
				size(20), 
				GRAY);

			DrawText(
				"Score Player 2:", 
				positionCentralize(getScreenWidth(), 2.9), 
				(int)(getScreenHeight()/8*2 + size(100)), 
				size(20), 
				GRAY);

            EndDrawing();

	}

}
