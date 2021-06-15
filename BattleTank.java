/*
* Battle tank features
*/

import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*; //for Color type

public class BattleTank extends Player
{
    
    //Textures
    private Texture2D battleTank[] = new Texture2D[4];

    //Size and Position
    private int positionLimits[] = new int[2];
    private int direction;

    //Frames and Time
    private int speed;
    private int frame = 0;

    //Mechanics and Others
    private boolean player2 = false; //Informs if there's two players
    private boolean shoot = false;
    private int numberBullet = 0; //Informs wich bullet

    public BattleTank(int screenWidth, int screenHeight, boolean p2, int player){

        super(screenWidth, screenHeight, (int)(screenHeight/10*8), (float)screenHeight/720);

        //Set positions of player 1 and player 2
        this.speed = (int)(screenHeight/180);
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

        //Position Limits
        this.positionLimits[0] = (int)(screenWidth /2 - (5 * screenWidth /8)/2);
        this.positionLimits[1] = this.positionLimits[0] + (int)(5 * screenWidth /8) - (int)this.tankScale * 64;
    }

    @Override
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

            drawBullets();

            DrawTextureEx(
                this.battleTank[frame], 
                new Vector2(this.tankPosition[0], 
                    this.tankPosition[1]), 
                0.0f, 
                this.tankScale, 
                WHITE);
    }

    @Override
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
                if(0 == getShootRate()){
                    this.shoot = true;
                    setShootRate(25);
                    shoot(this.tankPosition[0], this.numberBullet);
                    this.numberBullet++;
                    if(this.numberBullet > 9){
                        this.numberBullet = 0;
                    }
                }
            }else if(getShootRate() <= 0){
                    this.shoot = false;
            }

            timeShootRate();

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
                if(0 == getShootRate()){
                    this.shoot = true;
                    setShootRate(25);
                    shoot(this.tankPosition[0], this.numberBullet);
                    this.numberBullet++;
                    if(this.numberBullet > 9){
                        this.numberBullet = 0;
                    }
                }
            }else{
                this.shoot = false;
            }
            timeShootRate();
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

    //Getters TankBullet
    public int[][] getBulletPositions(){
        return getShootPositions();
    }

    public boolean[] getBulletActive(){
        return getShootActive();
    }

}
