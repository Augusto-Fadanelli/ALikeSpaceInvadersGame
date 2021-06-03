/*
* Game Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class GameScreen extends Screen
{
	private boolean twoPlayers;

	public GameScreen(int width, int height, Color c){
		super(width, height, c);
	}

	public void setTwoPlayers(boolean t){
		this.twoPlayers = t;
	}

	@Override
	public void draw(){

		BattleTank tank1 = new BattleTank(getScreenWidth(), twoPlayers, 0); //Player 1
        BattleTank tank2 = new BattleTank(getScreenWidth(), twoPlayers, 1); //Player 2
        TankBullet bullet1 = new TankBullet(); //Bullets of tank1
        TankBullet bullet2 = new TankBullet(); //Bullets of tank2

        tank1.setColor(BLUE);
        tank2.setColor(PINK);

        // Main game loop
        while (!WindowShouldClose())    // Detect window close button or ESC key
        {
            // Update
            //----------------------------------------------------------------------------------
            if (IsKeyDown(KEY_S)) tank1.setTankPosition(tank1.getTankPosition() + tank1.getSpeed());
            if (IsKeyDown(KEY_A)) tank1.setTankPosition(tank1.getTankPosition() - tank1.getSpeed());

            if(twoPlayers){
                if (IsKeyDown(KEY_RIGHT)) tank2.setTankPosition(tank2.getTankPosition() + tank2.getSpeed());
                if (IsKeyDown(KEY_LEFT)) tank2.setTankPosition(tank2.getTankPosition() - tank2.getSpeed());
            }

            /*if (IsKeyDown(KEY_SPACE)){
                if(0 == bullet1.getShootRate()){
                    DrawRectangle(tank1.getTankPosition()+15, 368, 10, 12, LIGHTGRAY);

                    bullet1.setShootRate(5);
                }
            }*/
            //----------------------------------------------------------------------------------

            // Draw
            //----------------------------------------------------------------------------------
            BeginDrawing();

            ClearBackground(BLACK);
            
            //Battle tanks
            tank1.draw();
            if(twoPlayers){
                tank2.draw();
            }

            //Tank bullets

            
            EndDrawing();
            //----------------------------------------------------------------------------------
        }

	}

	public void input(){
		
	}

}
