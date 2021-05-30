import com.raylib.Jaylib.Vector3;
import com.raylib.Jaylib.Camera;
import com.raylib.Raylib;
import static com.raylib.Jaylib.*;

//import src.Screen;

public class Main
{
    public static void main(String [] args){
       
        SplashScreen splash = new SplashScreen(800, 450, BLACK);
        MenuScreen menu = new MenuScreen(800, 450, BLACK);
        GameScreen game = new GameScreen(1000, 600, BLACK);

        splash.initWindow();

        boolean twoPlayers = true;
        BattleTank tank1 = new BattleTank(game.getScreenWidth(), twoPlayers, 0); //Player 1
        BattleTank tank2 = new BattleTank(game.getScreenWidth(), twoPlayers, 1); //Player 2
        TankBullet bullet1 = new TankBullet(); //Bullets of tank1
        TankBullet bullet2 = new TankBullet(); //Bullets of tank2

        tank1.setColor(BLUE);

        SetTargetFPS(60);               // Set our game to run at 60 frames-per-second

        //Splash screen
        boolean screenFlag = true;
        while(screenFlag && !WindowShouldClose()){
            BeginDrawing();
            splash.draw();
            if(IsKeyPressed(KEY_ENTER)){
                screenFlag = false;
            }
            EndDrawing();
        }

        //Menu screen
        screenFlag = true;
        while(screenFlag && !WindowShouldClose()){
            BeginDrawing();
            menu.draw();
            
            //Input
            if(IsKeyPressed(KEY_DOWN)){
                menu.input(1);
            }else if(IsKeyPressed(KEY_UP)){
                menu.input(-1);
            }
            if(IsKeyPressed(KEY_ENTER)){
                screenFlag = false;
            }
            EndDrawing();
        }


        CloseWindow();       // Close window and OpenGL context
        game.initWindow();

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

        // De-Initialization
        CloseWindow();       // Close window and OpenGL context
    }
}
