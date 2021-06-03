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

        //Menu screen
        menu.draw();

        switch(menu.getChoose()){
            case 0:
                game.setTwoPlayers(false);
                game.draw();
            break;
            case 1:
                game.setTwoPlayers(true);
                game.draw();
            break;
            case 2:

            break;
            case 3:
                video_settings.draw();
            break;
            case 4:

            break;
        }

        // De-Initialization
        CloseWindow();       // Close window and OpenGL context
    }
}
