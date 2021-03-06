import com.raylib.Raylib;
import com.raylib.Jaylib.*;
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
        //-------------------------------------------------------
        boolean twoPlayers = true;
        Texture2D background;
        int gameSpace[] = new int[4];
        int statusBar[] = new int[4];
        boolean gameOver;
        boolean win;
        int timer = 0;

        gameSpace[0] = (int)(game.getScreenWidth() /2 - (5 * game.getScreenWidth() /8)/2);
        gameSpace[1] = 0;
        gameSpace[2] = (int)(5 * game.getScreenWidth() /8);
        gameSpace[3] = (int)game.getScreenHeight();

        statusBar[0] = (int)(game.getScreenWidth() /2 - (5 * game.getScreenWidth() /8)/2);
        statusBar[1] = (int)(game.getScreenHeight()/10*8 + game.getScreenHeight()/720 *64);
        statusBar[2] = (int)(5 * game.getScreenWidth() /8);
        statusBar[3] = (int)(game.getScreenHeight() - (game.getScreenHeight()/10*8 + game.getScreenHeight()/720 *64));

        //Textures
        Texture2D ground = LoadTexture("assets/sprites/ground.png");                    

        background = LoadTexture("assets\\textures\\background_stage1.png");

        InitAudioDevice();              // Initialize audio device

        Music music = LoadMusicStream("assets\\musics\\theme.mp3");
        PlayMusicStream(music);

        Enemy3 enemy3 = new Enemy3(game.getScreenWidth(), game.getScreenHeight(), 0);
        Enemy2 enemy2 = new Enemy2(game.getScreenWidth(), game.getScreenHeight(), 1);
        Enemy1 enemy1 = new Enemy1(game.getScreenWidth(), game.getScreenHeight(), 3);

        int whoCanShoot[] = new int[10]; //0 - Enemy1; 1 - Enemy2; 2 - Enemy3; 3 - No one

        BattleTank tank1 = new BattleTank(game.getScreenWidth(), game.getScreenHeight(), 0); //Player 1
        BattleTank tank2 = new BattleTank(game.getScreenWidth(), game.getScreenHeight(), 1); //Player 2

        //-------------------------------------------------------

        //Game loop
        boolean firstTimeFlag = true;

        //Music
        boolean musicFlag = true;
        boolean oneTimeFlag = true;

        boolean exitFlag = true;
        int op = -1;
        while(exitFlag && !WindowShouldClose()){

            if(musicFlag){
                UpdateMusicStream(music);   // Update music buffer with new stream data
            }else if(oneTimeFlag){
                oneTimeFlag = false;
                UnloadMusicStream(music);   // Unload music stream buffers from RAM
                CloseAudioDevice();         // Close audio device (music streaming is automatically stopped)
            }

            switch(op){
                case 0:
                    //Single Player
                    twoPlayers = false;

                case 1:
                    //Multiplayer

                    if(firstTimeFlag){
                        tank1.setPlayerPositions(twoPlayers);
                        if(twoPlayers){
                            tank2.setPlayerPositions(twoPlayers);
                        }

                        firstTimeFlag = false;
                    }

                    if(musicFlag){
                        musicFlag = false;
                    }

                    tank1.input(1);
                    if(twoPlayers){
                        tank2.input(2);
                    }

                    //Player 1 bullets colisions
                    enemy1.checkCollision(tank1.getShootPositions(), tank1.getShootActive(), tank1.getBulletDamage());
                    enemy2.checkCollision(tank1.getShootPositions(), tank1.getShootActive(), tank1.getBulletDamage());
                    enemy3.checkCollision(tank1.getShootPositions(), tank1.getShootActive(), tank1.getBulletDamage());

                    //Aliens bullets colisions with player 1
                    tank1.checkCollision(enemy1.getShootPositions(), enemy1.getShootActive(), enemy1.getBulletDamage());
                    tank1.checkCollision(enemy2.getShootPositions(), enemy2.getShootActive(), enemy2.getBulletDamage());
                    tank1.checkCollision(enemy3.getShootPositions(), enemy3.getShootActive(), enemy3.getBulletDamage());

                    if(twoPlayers){
                        //Player 2 bullets colisions
                        enemy1.checkCollision(tank2.getShootPositions(), tank2.getShootActive(), tank2.getBulletDamage());
                        enemy2.checkCollision(tank2.getShootPositions(), tank2.getShootActive(), tank2.getBulletDamage());
                        enemy3.checkCollision(tank2.getShootPositions(), tank2.getShootActive(), tank2.getBulletDamage());

                        //Aliens bullets colisions with player 1
                        tank2.checkCollision(enemy1.getShootPositions(), enemy1.getShootActive(), enemy1.getBulletDamage());
                        tank2.checkCollision(enemy2.getShootPositions(), enemy2.getShootActive(), enemy2.getBulletDamage());
                        tank2.checkCollision(enemy3.getShootPositions(), enemy3.getShootActive(), enemy3.getBulletDamage());
                    }

                    enemy1.setCanShoot(whoCanShoot);
                    enemy2.setCanShoot(whoCanShoot);                    
                    enemy3.setCanShoot(whoCanShoot);

                    BeginDrawing();

                    ClearBackground(GetColor(0x052c46ff));
                    DrawTextureEx(
                        background, 
                        new Vector2(0.0f, 0.0f), 
                        0.0f, 
                        game.getScreenWidth()/640, 
                        WHITE);
                    
                    //Battle tanks
                    tank1.draw();
                    if(twoPlayers){
                        tank2.draw();
                    }

                    //Aliens
                    enemy1.draw(whoCanShoot);
                    enemy2.draw(whoCanShoot);
                    enemy3.draw(whoCanShoot);

                    //Ground
                    DrawTextureEx(
                        ground, 
                        new Vector2(statusBar[0], 
                            statusBar[1]), 
                        0.0f, 
                        game.getScreenHeight()/360, 
                        WHITE);

                    //Game space
                    DrawRectangleLines(gameSpace[0], gameSpace[1], gameSpace[2], gameSpace[3], WHITE);

                    //Status bar
                    //DrawRectangleLines(statusBar[0], statusBar[1], statusBar[2], statusBar[3], WHITE);
                    DrawText(
                    "Tank 1 Lifes: " + tank1.getLife(), 
                    statusBar[0] + 5, 
                    statusBar[1] + 5, 
                    20, 
                    RAYWHITE);
                    if(twoPlayers){
                        DrawText(
                        "Tank 2 Lifes: " + tank2.getLife(), 
                        statusBar[0] + 600, 
                        statusBar[1] + 5, 
                        20, 
                        RAYWHITE);
                    }

                    EndDrawing();

                    //Check game over                    
                    if(!twoPlayers){
                        gameOver = tank1.gameOver();
                    }else{
                        gameOver = tank2.gameOver();
                        if(gameOver){
                        gameOver = tank1.gameOver();
                        }
                    }
                    if(gameOver){
                        if(180 <= timer){ //wait 3 sec
                            op = 6;
                        }
                        timer++;
                    }

                    //Check win
                    win = enemy1.win();
                    if(win){
                        win = enemy2.win();
                        if(win){
                            win = enemy3.win();
                        }
                    }

                    //Return to the Menu
                    if(win){
                        if(180 <= timer){ //wait 3 sec
                            op = -1;
                        }
                        timer++;
                    }

                break;
                case 2:
                    //Ranking
                break;
                case 3:
                    //Video Settings Screen
                    video_settings.draw();
                    display.write("DAT/display_resolution.dat", video_settings.getWidthChoose(), video_settings.getHeightChoose());
                    op = video_settings.getExit();
                break;
                case 4:
                    //About
                break;
                case 5:
                    //Exit
                    exitFlag = false;
                break;
                case 6:
                    //game over
                    game.drawGameOver();
                    if(IsKeyDown(KEY_SPACE)){
                        op = -1;
                    }
                break;
                default:
                    //Menu (choice: -1)
                    /*if(!musicFlag){
                        PlayMusicStream(music);
                        musicFlag = true;
                        oneTimeFlag = true;
                    }*/

                    menu.draw();
                    op = menu.getChoice();

                    //Reset game
                    tank1.reset();
                    if(twoPlayers){
                        tank2.reset();
                    }
                    twoPlayers = true;
                    timer = 0;
                    enemy1.reset();
                    enemy2.reset();
                    enemy3.reset();

                    //re-initialize flags
                    firstTimeFlag = true;
            }
        }  

        // De-Initialization
        if(oneTimeFlag){
            UnloadMusicStream(music);   // Unload music stream buffers from RAM
            CloseAudioDevice();         // Close audio device (music streaming is automatically stopped)
        }
        
        CloseWindow();       // Close window and OpenGL context
    }
}
