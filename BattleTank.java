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
    
    //Textures
    private Texture2D battleTank[] = new Texture2D[4];

    //Size and Position
    private int tankPosition[] = new int[2]; //Position in width
    private int positionLimits[] = new int[2];
    private int height;
    private int direction;
    private float tankScale;

    //Frames and Time
    //private float timer = 0.0f;
    private int speed;
    private int frame = 0;

    //Mechanics and Others
    private boolean player2 = false; //Informs if there's two players
    private boolean shoot = false;
    TankBullet bullet1 = new TankBullet(); //Bullets of tank1
    TankBullet bullet2 = new TankBullet(); //Bullets of tank2

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

        //Battle Tank Scale
        this.tankScale = (float)this.height/720;

        //Position Limits
        this.positionLimits[0] = (int)(screenWidth /2 - (5 * screenWidth /8)/2);
        this.positionLimits[1] = this.positionLimits[0] + (int)(5 * screenWidth /8) - (int)this.tankScale * 64;
    }

    public void draw(){

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

            DrawTextureEx(
                this.battleTank[frame], 
                new Vector2(this.tankPosition[0], 
                    this.tankPosition[1]), 
                0.0f, 
                this.tankScale, 
                WHITE);
    }

    public void input(int player){ //1 or 2
        if(1 == player){
            if (IsKeyDown(KEY_S)){

                this.direction = 1;

                if(this.tankPosition[0] > this.positionLimits[1]){
                    this.tankPosition[0] = this.positionLimits[1];
                }else{
                    this.tankPosition[0] += this.speed;
                }

            } 
            if (IsKeyDown(KEY_A)){
                
                this.direction = -1;

                if(this.tankPosition[0] < this.positionLimits[0]){
                    this.tankPosition[0] = this.positionLimits[0];
                }else{
                    this.tankPosition[0] -= this.speed;
                }

            }
            if (IsKeyDown(KEY_SPACE)){
                if(0 == bullet1.getShootRate()){
                    this.shoot = true;
                    bullet1.setShootRate(50);
                }
            }else{
                this.shoot = false;
            }
            bullet1.timeShootRate();

        }else{
            if (IsKeyDown(KEY_RIGHT)){
                    this.direction = 1;

                    if(this.tankPosition[0] > this.positionLimits[1]){
                        this.tankPosition[0] = this.positionLimits[1];
                    }else{
                        this.tankPosition[0] += this.speed;
                    }
            }
            if (IsKeyDown(KEY_LEFT)){
                this.direction = -1;

                if(this.tankPosition[0] < this.positionLimits[0]){
                    this.tankPosition[0] = this.positionLimits[0];
                }else{
                    this.tankPosition[0] -= this.speed;
                }
            }
            if (IsKeyDown(KEY_M)){
                if(0 == bullet2.getShootRate()){
                    this.shoot = true;
                    bullet2.setShootRate(50);
                }
            }else{
                this.shoot = false;
            }
            bullet2.timeShootRate();
        }

    }

    public void setDirection(int d){ //-1 Left, 1 Right
        this.direction = d;
    }

    public void setShoot(boolean s){
        this.shoot = s;
    }

    /*public void setTankPosition(int newPosition){
        if(this.tankPosition[0] <= this.positionLimits[0]){
            this.tankPosition[0] = this.positionLimits[0];
        }else if((this.tankPosition[0] + this.tankScale >= this.positionLimits[1])){
            this.tankPosition[0] = this.positionLimits[1];
        }else{
            this.tankPosition[0] = newPosition;
        }
    }
    public int getTankPosition(){
        return this.tankPosition[0];
    }*/

    public int getSpeed(){
        return this.speed;
    }
}
