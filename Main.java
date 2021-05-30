import com.raylib.Jaylib.Vector3;
import com.raylib.Jaylib.Camera;
import com.raylib.Raylib;
import static com.raylib.Jaylib.*;

//import src.Screen;

public class Main
{
    public static void main(String [] args){
       
        Screen screen = new Screen(800, 450, BLACK);
        SplashScreen splash = new SplashScreen(800, 450, BLACK);

        screen.initWindow();

        boolean twoPlayers = true;
        BattleTank tank1 = new BattleTank(screen.getScreenWidth(), twoPlayers, 0); //Player 1
        BattleTank tank2 = new BattleTank(screen.getScreenWidth(), twoPlayers, 1); //Player 2
        TankBullet bullet1 = new TankBullet(); //Bullets of tank1
        TankBullet bullet2 = new TankBullet(); //Bullets of tank2

        tank1.setColor(BLUE);

        SetTargetFPS(60);               // Set our game to run at 60 frames-per-second

        boolean screenFlag = true;

        while(screenFlag && !WindowShouldClose()){
            BeginDrawing();
            splash.draw();
            if(IsKeyDown(KEY_ENTER)){
                screenFlag = false;
            }
            EndDrawing();
        }

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
            DrawRectangle(tank1.getTankPosition(), 380, 41, 20, tank1.getColor()); //width, height, lenght
            DrawRectangle(tank1.getTankPosition()+15, 368, 10, 12, tank1.getColor());
            if(twoPlayers){
                DrawRectangle(tank2.getTankPosition(), 380, 41, 20, tank2.getColor()); //width, height, lenght
                DrawRectangle(tank2.getTankPosition()+15, 368, 10, 12, tank2.getColor());
            }

            //Tank bullets

            
            EndDrawing();
            //----------------------------------------------------------------------------------
        }

        // De-Initialization
        CloseWindow();       // Close window and OpenGL context
    }
}
