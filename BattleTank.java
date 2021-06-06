/*
* Battle tank features
*/

//import com.raylib.Jaylib.Vector3;
//import com.raylib.Jaylib.Camera;

import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*; //for Color type

public class BattleTank
{
    
    private int tankPosition[] = new int[2]; //Position in width
    private Texture2D battleTank[] = new Texture2D[4];
    private int height;
    private boolean player2 = false; //Informs if there's two players
    private int speed;
    //private float timer = 0.0f;
    private int direction;
    private boolean shoot = false;
    private int frame = 0;

    private Rectangle teste;

    public BattleTank(int screenWidth, int screenHeight, boolean p2, int player){
        this.height = screenHeight;
        this.speed = (int)screenHeight/180;
        if(p2){
            if(0 == player){
                this.tankPosition[0] = (int)(screenWidth/20*6); //Break screen width in 20 parts
            }else{
                this.tankPosition[0] = (int)(screenWidth/20*13);
            }
        }else{
            this.tankPosition[0] = (int)(screenWidth/2);
        }

        this.tankPosition[1] = (int)(screenHeight/10*8); //Break screen height in 10 parts
        this.player2 = p2;

        if(0 == player){
            //Load Sprites
            this.battleTank[0] = LoadTexture("assets/sprites/wr/green_tank_1.png");
            this.battleTank[1] = LoadTexture("assets/sprites/wr/green_tank_2.png");
            this.battleTank[2] = LoadTexture("assets/sprites/wr/green_tank_3.png");
            this.battleTank[3] = LoadTexture("assets/sprites/wr/green_tank_4.png");
        }else{
            //Load Sprites
            this.battleTank[0] = LoadTexture("assets/sprites/wr/red_tank_1.png");
            this.battleTank[1] = LoadTexture("assets/sprites/wr/red_tank_2.png");
            this.battleTank[2] = LoadTexture("assets/sprites/wr/red_tank_3.png");
            this.battleTank[3] = LoadTexture("assets/sprites/wr/red_tank_4.png");
        }
    }

    public void draw(){
        //DrawRectangle(this.tankPosition, 380, 41, 20, this.color); //width, height, lenght
        //DrawRectangle(this.tankPosition + 15, 368, 10, 12, this.color);

        if(this.direction == -1){ //Left
            if(true == this.shoot){
                this.frame = 3;
            }else{
                this.frame = 2;
            }
        }else if(this.direction == 1){ //Right
            if(true == this.shoot){
                this.frame = 1;
            }else{
                this.frame = 0;
            }
        }

            //DrawTexture(this.battleTank[frame], this.tankPosition[0], this.tankPosition[1], WHITE);
            DrawTextureEx(this.battleTank[frame], new Vector2(this.tankPosition[0], this.tankPosition[1]), 0.0f, (float)this.height/720, WHITE);
    }

    public void setDirection(int d){ //-1 Left, 1 Right
        this.direction = d;
    }

    public void setShoot(boolean s){
        this.shoot = s;
    }

    public void setTankPosition(int newPosition){
        this.tankPosition[0] = newPosition;
    }
    public int getTankPosition(){
        return this.tankPosition[0];
    }

    public int getSpeed(){
        return this.speed;
    }
}
