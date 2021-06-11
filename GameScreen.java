/*
* Game Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class GameScreen extends Screen
{
	private boolean twoPlayers;

    //private Texture2D background;

    //Shapes
    private int gameSpace[] = new int[4];
    private int statusBar[] = new int[4];

	public GameScreen(int width, int height, Color c){
		super(width, height, c);

        //Shapes
        this.gameSpace[0] = (int)(getScreenWidth() /2 - (5 * getScreenWidth() /8)/2);
        this.gameSpace[1] = 0;
        this.gameSpace[2] = (int)(5 * getScreenWidth() /8);
        this.gameSpace[3] = (int)getScreenHeight();

        this.statusBar[0] = (int)(getScreenWidth() /2 - (5 * getScreenWidth() /8)/2);
        this.statusBar[1] = (int)(getScreenHeight()/10*8 + getScreenHeight()/720 *64);
        this.statusBar[2] = (int)(5 * getScreenWidth() /8);
        this.statusBar[3] = (int)(getScreenHeight() - (getScreenHeight()/10*8 + getScreenHeight()/720 *64));

        //this.background = LoadTexture("assets\\textures\\background_stage1.png");
	}

	public void setTwoPlayers(boolean t){
		this.twoPlayers = t;
	}

	@Override
	public void draw(){

		BattleTank tank1 = new BattleTank(getScreenWidth(), getScreenHeight(), twoPlayers, 0); //Player 1
        BattleTank tank2 = new BattleTank(getScreenWidth(), getScreenHeight(), twoPlayers, 1); //Player 2

        // Main game loop
        //while (!WindowShouldClose())    // Detect window close button or ESC key
        //{

            tank1.input(1);
            if(twoPlayers){
                tank2.input(2);
            }

            BeginDrawing();

            ClearBackground(GetColor(0x052c46ff));
            /*DrawTextureEx(
                this.background, 
                new Vector2(0.0f, 0.0f), 
                0.0f, 
                getScreenWidth()/640, 
                WHITE); */
            
            //Battle tanks
            tank1.draw();
            if(twoPlayers){
                tank2.draw();
            }

            //Game space
            DrawRectangleLines(this.gameSpace[0], this.gameSpace[1], this.gameSpace[2], this.gameSpace[3], WHITE);

            //Status bar
            DrawRectangleLines(this.statusBar[0], this.statusBar[1], this.statusBar[2], this.statusBar[3], WHITE);

            EndDrawing();
        //}

	}

}
