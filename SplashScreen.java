/*
* Splash Screen
*/

import static com.raylib.Jaylib.*; //for type Color
import static java.lang.Math.*;

public class SplashScreen extends Screen
{
	private int width;
	private int height;

	public SplashScreen(int w, int h, Color c){ //Width Height Color
		super(w, h, c);
		this.width = w;
		this.height = h;
	}

	@Override
	public void draw(){

        while(!IsKeyPressed(KEY_ENTER) && !WindowShouldClose()){
            BeginDrawing();
            
            ClearBackground(getBackgroundColor());

			DrawText("A like", Math.toIntExact(Math.round((this.width - this.width/24*8.7)/2)), this.height/2 - 25, 20, LIGHTGRAY);
			DrawText("SPACE INVADERS", Math.toIntExact(Math.round((this.width - this.width/24*8.7)/2)), this.height/2, 50, LIGHTGRAY);
			DrawText("game!", Math.toIntExact(Math.round((this.width - this.width/24*8.7)/2+this.width/24*8.7 - this.width/24)), this.height/2 + 40, 20, LIGHTGRAY);
			DrawText("Press ENTER to continue...", Math.toIntExact( Math.round((this.width - this.width/24*5.2)/2)), this.height/4*3, 20, RAYWHITE);
			DrawText("Developed by: Augusto Fadanelli", 10, this.height - 20, 15, RAYWHITE);

            EndDrawing();
        }
	}

}
