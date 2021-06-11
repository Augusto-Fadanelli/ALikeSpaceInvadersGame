//import com.raylib.Jaylib.Vector3;
//import com.raylib.Jaylib.Camera;
//import com.raylib.Raylib;

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
        MenuScreen menu = new MenuScreen(display.getResolutions(0), display.getResolutions(1), BLACK);
        GameScreen game = new GameScreen(display.getResolutions(0), display.getResolutions(1), BLACK);

        splash.initWindow();

        //Splash screen
        splash.draw();

        //Game Variables
        boolean twoPlayers = true;
        Texture2D background;
        int gameSpace[] = new int[4];
        int statusBar[] = new int[4];

        gameSpace[0] = (int)(game.getScreenWidth() /2 - (5 * game.getScreenWidth() /8)/2);
        gameSpace[1] = 0;
        gameSpace[2] = (int)(5 * game.getScreenWidth() /8);
        gameSpace[3] = (int)game.getScreenHeight();

        statusBar[0] = (int)(game.getScreenWidth() /2 - (5 * game.getScreenWidth() /8)/2);
        statusBar[1] = (int)(game.getScreenHeight()/10*8 + game.getScreenHeight()/720 *64);
        statusBar[2] = (int)(5 * game.getScreenWidth() /8);
        statusBar[3] = (int)(game.getScreenHeight() - (game.getScreenHeight()/10*8 + game.getScreenHeight()/720 *64));

        //this.background = LoadTexture("assets\\textures\\background_stage1.png");
        BattleTank tank1 = new BattleTank(game.getScreenWidth(), game.getScreenHeight(), twoPlayers, 0); //Player 1
        BattleTank tank2 = new BattleTank(game.getScreenWidth(), game.getScreenHeight(), twoPlayers, 1); //Player 2

        boolean flag = true;
        int op = -1;
        while(flag && !WindowShouldClose()){

            switch(op){
                case 0:
                case 1:

                    /*if(0 == op){
                        twoPlayers = false;
                    }else{
                        twoPlayers = true;
                    }*/

                    //teste

                    //Aqui começa o loop

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
                    DrawRectangleLines(gameSpace[0], gameSpace[1], gameSpace[2], gameSpace[3], WHITE);

                    //Status bar
                    DrawRectangleLines(statusBar[0], statusBar[1], statusBar[2], statusBar[3], WHITE);

                    EndDrawing();

                break;
                case 2:

                break;
                case 3:
                    video_settings.draw();
                    display.write("DAT/display_resolution.dat", video_settings.getWidthChoose(), video_settings.getHeightChoose());
                    op = video_settings.getExit();
                break;
                case 4:

                break;
                case 5:
                    flag = false;
                break;
                default:
                    //Menu (choice: -1)
                    menu.draw();
                    op = menu.getChoice();
            }
        }  

        // De-Initialization
        CloseWindow();       // Close window and OpenGL context
    }
}
