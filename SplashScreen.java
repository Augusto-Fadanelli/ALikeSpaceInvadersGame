/*
* Splash Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class SplashScreen extends Screen
{
	private int width;
	private int height;

	private int logoPositionX;
    private int logoPositionY;
    private int framesCounter = 0;
    private int lettersCount = 0;
	private int topSideRecWidth = 16;
    private int leftSideRecHeight = 16;
    private int bottomSideRecWidth = 16;
    private int rightSideRecHeight = 16;
    private int state = 0;                  // Tracking animation states (State Machine)
    private float alpha = 1.0f;             // Useful for fading


	public SplashScreen(int w, int h, Color c){ //Width Height Color
		super(w, h, c);
		this.width = w;
		this.height = h;

		this.logoPositionX = this.width/2 - 128;
    	this.logoPositionY = this.height/2 - 128;
	}

	@Override
	public void draw(){

        while(!IsKeyPressed(KEY_SPACE) && !WindowShouldClose()){
            BeginDrawing();
            
            ClearBackground(getBackgroundColor());

            if(this.state == 4){

	            //Math.toIntExact(Math.round((this.width - this.width/24*8.7)/2))
				DrawText(
					"A like", 
					positionCentralize(this.width, 8.5),
					(int)(this.height/8*3 - size(20)), 
					size(20), 
					LIGHTGRAY);

				DrawText(
					"SPACE INVADERS",
					positionCentralize(this.width, 8.5), 
					(int)(this.height/8*3), 
					size(50), 
					LIGHTGRAY);

				DrawText(
					"game!", 
					(int)((this.width - this.width /12*8.5)/2 + this.width /12*8.5 - this.width /12),
					(int)(this.height/8*3 + size(50) - 10), 
					size(20), 
					LIGHTGRAY);

				DrawText(
					"Press SPACE to continue...", 
					(int)((this.width - this.width / 12 * 5.2)/2), 
					(int)(this.height/4*3), 
					size(20), 
					RAYWHITE);

				DrawText(
					"Developed by: Augusto Fadanelli", 
					(int)(this.width * 10 /640), 
					(int)(this.height - 20), 
					size(15), 
					RAYWHITE);

            }else{
            	drawRaylibLogo();
            }
            
            EndDrawing();
        }
	}

	public void drawRaylibLogo(){

		// Update
        //----------------------------------------------------------------------------------
        if (this.state == 0)                 // State 0: Small box blinking
        {
            this.framesCounter++;

            if (this.framesCounter == 120)
            {
                this.state = 1;
                this.framesCounter = 0;      // Reset counter... will be used later...
            }
        }
        else if (this.state == 1)            // State 1: Top and left bars growing
        {
            this.topSideRecWidth += 4;
            this.leftSideRecHeight += 4;

            if (this.topSideRecWidth == 256) this.state = 2;
        }
        else if (this.state == 2)            // State 2: Bottom and right bars growing
        {
            this.bottomSideRecWidth += 4;
            this.rightSideRecHeight += 4;

            if (this.bottomSideRecWidth == 256) this.state = 3;
        }
        else if (this.state == 3)            // State 3: Letters appearing (one by one)
        {
            this.framesCounter++;

            if (this.framesCounter/12 == 1)       // Every 12 frames, one more letter!
            {
                this.lettersCount++;
                this.framesCounter = 0;
            }

            if (this.lettersCount >= 10)     // When all letters have appeared, just fade out everything
            {
                this.alpha -= 0.02f;

                if (this.alpha <= 0.0f)
                {
                    this.alpha = 0.0f;
                    this.state = 4;
                }
            }
        }
        //----------------------------------------------------------------------------------

        // Draw
        //----------------------------------------------------------------------------------
        BeginDrawing();

            if (this.state == 0)
            {
                if ((this.framesCounter/15)%2 == 1) DrawRectangle(this.logoPositionX, this.logoPositionY, 16, 16, RAYWHITE);
            }
            else if (this.state == 1)
            {
                DrawRectangle(this.logoPositionX, this.logoPositionY, this.topSideRecWidth, 16, RAYWHITE);
                DrawRectangle(this.logoPositionX, this.logoPositionY, 16, this.leftSideRecHeight, RAYWHITE);
            }
            else if (state == 2)
            {
                DrawRectangle(this.logoPositionX, this.logoPositionY, this.topSideRecWidth, 16, RAYWHITE);
                DrawRectangle(this.logoPositionX, this.logoPositionY, 16, this.leftSideRecHeight, RAYWHITE);

                DrawRectangle(this.logoPositionX + 240, this.logoPositionY, 16, this.rightSideRecHeight, RAYWHITE);
                DrawRectangle(this.logoPositionX, this.logoPositionY + 240, this.bottomSideRecWidth, 16, RAYWHITE);
            }
            else if (this.state == 3)
            {
                DrawRectangle(this.logoPositionX, this.logoPositionY, this.topSideRecWidth, 16, Fade(RAYWHITE, this.alpha));
                DrawRectangle(this.logoPositionX, this.logoPositionY + 16, 16, this.leftSideRecHeight - 32, Fade(RAYWHITE, this.alpha));

                DrawRectangle(this.logoPositionX + 240, this.logoPositionY + 16, 16, this.rightSideRecHeight - 32, Fade(RAYWHITE, this.alpha));
                DrawRectangle(this.logoPositionX, this.logoPositionY + 240, this.bottomSideRecWidth, 16, Fade(RAYWHITE, this.alpha));

                DrawRectangle(this.width/2 - 112, this.height/2 - 112, 224, 224, Fade(BLACK, this.alpha));

                DrawText(TextSubtext("raylib", 0, this.lettersCount), this.width/2 - 44, this.height/2 + 48, 50, Fade(RAYWHITE, this.alpha));
            }
	}

}
