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

        boolean flag = true;
        while(flag && !WindowShouldClose()){
            //Menu screen
            switch(menu.chooseDraw()){
                case 0:
                    //Game Screen Single Player
                    game.setTwoPlayers(false);
                    game.draw();
                break;
                case 1:
                    //Game Screen Multiplayer Player
                    game.setTwoPlayers(true);
                    game.draw();
                break;
                case 2:

                break;
                case 3:
                    video_settings.draw();
                    display.write("DAT/display_resolution.dat", video_settings.getWidthChoose(), video_settings.getHeightChoose());
                break;
                case 4:

                break;
                case 5:
                    flag = false;
                break;
            }
        }  

        // De-Initialization
        CloseWindow();       // Close window and OpenGL context
    }
}
