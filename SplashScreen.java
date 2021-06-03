/*
* Splash Screen
*/

import static com.raylib.Jaylib.*; //for type Color

//import java.lang.Math;
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

		//Size
		int size[] = new int[5];
		size[0] = (int)this.width /32;
		size[1] = (int)5 * this.width /64;
		size[2] = (int)this.width /32;
		size[3] = (int)this.width /32;
		size[4] = (int)3 * this.width /128;

        while(!IsKeyPressed(KEY_SPACE) && !WindowShouldClose()){
            BeginDrawing();
            
            ClearBackground(getBackgroundColor());

            //Math.toIntExact(Math.round((this.width - this.width/24*8.7)/2))
			DrawText(
				"A like", 
				positionCentralize(this.width, 8.5),
				(int)(this.height/8*3 - size[0]), 
				size[0], 
				LIGHTGRAY);

			DrawText(
				"SPACE INVADERS",
				positionCentralize(this.width, 8.5), 
				(int)(this.height/8*3), 
				size[1], 
				LIGHTGRAY);

			DrawText(
				"game!", 
				(int)((this.width - this.width /12*8.5)/2 + this.width /12*8.5 - this.width /12),
				(int)(this.height/8*3 + size[1] - 10), 
				size[2], 
				LIGHTGRAY);

			DrawText(
				"Press ENTER to continue...", 
				(int)((this.width - this.width / 12 * 5.2)/2), 
				(int)(this.height/4*3), 
				size[3], 
				RAYWHITE);

			DrawText(
				"Developed by: Augusto Fadanelli", 
				(int)(this.width * 10 /640), 
				(int)(this.height - 20), 
				size[4], 
				RAYWHITE);

            EndDrawing();
        }
        //try { Thread.sleep (3000); } catch (InterruptedException ex) {}
	}

}
