import com.raylib.Jaylib.Vector3;
import com.raylib.Jaylib.Camera;
import com.raylib.Raylib;
import static com.raylib.Jaylib.*;

import java.io.IOException; //Manipular arquivos

public class Main
{
    public static void main(String args[]) throws IOException{

        //Manipular arquivos
        Files display = new Files();
        display.read("DAT/display_resolution.dat"); //read setted resolution

        Files r16x9 = new Files();
        VideoSettings video_settings = new VideoSettings(display.getResolutions(0), display.getResolutions(1), BLACK);

        //Files.write(path);
        r16x9.read("DAT/16x9_resolutions.dat"); //read list of 16x9 resolutions
        for(int i=0; i<10; i++){
            video_settings.setResolutions(r16x9.getResolutions(i));
        }


        SplashScreen splash = new SplashScreen(display.getResolutions(0), display.getResolutions(1), BLACK);
        MenuScreen menu = new MenuScreen(800, 450, BLACK);
        GameScreen game = new GameScreen(1000, 600, BLACK);

        splash.initWindow();

        SetTargetFPS(60);               // Set our game to run at 60 frames-per-second

        //Splash screen
        boolean flag = true;
        while(flag && !WindowShouldClose()){
            BeginDrawing();
            splash.draw();
            if(IsKeyPressed(KEY_ENTER)){
                flag = false;
            }
            EndDrawing();
        }
        flag = true;

        //Menu screen
        while(flag && !WindowShouldClose()){
            BeginDrawing();
            menu.draw();
            
            //Input
            if(IsKeyPressed(KEY_DOWN)){
                menu.input(1);
            }else if(IsKeyPressed(KEY_UP)){
                menu.input(-1);
            }
            if(IsKeyPressed(KEY_ENTER)){
                flag = false;
            }
            EndDrawing();
        }
        flag = true;

        boolean twoPlayers = false;
        boolean gameFlag = false;
        switch(menu.getChoose()){
            case 0:
                gameFlag = true;
            break;
            case 1:
                gameFlag = true;
                twoPlayers = true;
            break;
            case 2:

            break;
            case 3:
                while(flag && !WindowShouldClose()){
                    //Draw
                    BeginDrawing();
                    video_settings.draw();
                    
                    //Input
                    if(IsKeyPressed(KEY_DOWN)){
                        video_settings.input(1);
                    }else if(IsKeyPressed(KEY_UP)){
                        video_settings.input(-1);
                    }
                    if(IsKeyPressed(KEY_ENTER)){
                        flag = false;
                    }
                    EndDrawing();
                }
            break;
            case 4:

            break;
        }
        //flag = true;

        BattleTank tank1 = new BattleTank(game.getScreenWidth(), twoPlayers, 0); //Player 1
        BattleTank tank2 = new BattleTank(game.getScreenWidth(), twoPlayers, 1); //Player 2
        TankBullet bullet1 = new TankBullet(); //Bullets of tank1
        TankBullet bullet2 = new TankBullet(); //Bullets of tank2

        tank1.setColor(BLUE);
        tank2.setColor(PINK);


        CloseWindow();       // Close window and OpenGL context
        game.initWindow();

        // Main game loop
        while (!WindowShouldClose() && gameFlag)    // Detect window close button or ESC key
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
