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
    private Texture2D tankDying[] = new Texture2D[20];

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

        this.life = 6;
        this.bulletDamage = 1;

        this.frameDying = 50;

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

        //Load Sprites Dying
        this.tankDying[0] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_1.png");
        this.tankDying[1] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_2.png");
        this.tankDying[2] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_3.png");
        this.tankDying[3] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_4.png");
        this.tankDying[4] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_5.png");
        this.tankDying[5] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_6.png");
        this.tankDying[6] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_7.png");
        this.tankDying[7] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_8.png");
        this.tankDying[8] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_9.png");
        this.tankDying[9] = LoadTexture("assets/sprites/wr/tank_invader_dying_right_10.png");

        this.tankDying[10] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_1.png");
        this.tankDying[11] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_2.png");
        this.tankDying[12] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_3.png");
        this.tankDying[13] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_4.png");
        this.tankDying[14] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_5.png");
        this.tankDying[15] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_6.png");
        this.tankDying[16] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_7.png");
        this.tankDying[17] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_8.png");
        this.tankDying[18] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_9.png");
        this.tankDying[19] = LoadTexture("assets/sprites/wr/tank_invader_dying_left_10.png");

        //Position Limits
        this.positionLimits[0] = (int)(screenWidth /2 - (5 * screenWidth /8)/2);
        this.positionLimits[1] = this.positionLimits[0] + (int)(5 * screenWidth /8) - (int)this.tankScale * 64;
    }

    @Override
    public void draw(){

        if(this.life > 0){
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
        }else if(this.frameDying >= 0){
            if(this.direction == -1){ //left

                if(this.frameDying <= 50 && this.frameDying > 45){
                    this.frame = 10;
                }else if(this.frameDying <= 45 && this.frameDying > 40){
                    this.frame = 11;
                }else if(this.frameDying <= 40 && this.frameDying > 35){
                    this.frame = 12;
                }else if(this.frameDying <= 35 && this.frameDying > 30){
                    this.frame = 13;
                }else if(this.frameDying <= 30 && this.frameDying > 25){
                    this.frame = 14;
                }else if(this.frameDying <= 25 && this.frameDying > 20){
                    this.frame = 15;
                }else if(this.frameDying <= 20 && this.frameDying > 15){
                    this.frame = 16;
                }else if(this.frameDying <= 15 && this.frameDying > 10){
                    this.frame = 17;
                }else if(this.frameDying <= 10 && this.frameDying > 5){
                    this.frame = 18;
                }else if(this.frameDying <= 5){
                    this.frame = 19;
                }

            }else if(this.direction == 1){ //right

                if(this.frameDying <= 50 && this.frameDying > 45){
                    this.frame = 0;
                }else if(this.frameDying <= 45 && this.frameDying > 40){
                    this.frame = 1;
                }else if(this.frameDying <= 40 && this.frameDying > 35){
                    this.frame = 2;
                }else if(this.frameDying <= 35 && this.frameDying > 30){
                    this.frame = 3;
                }else if(this.frameDying <= 30 && this.frameDying > 25){
                    this.frame = 4;
                }else if(this.frameDying <= 25 && this.frameDying > 20){
                    this.frame = 5;
                }else if(this.frameDying <= 20 && this.frameDying > 15){
                    this.frame = 6;
                }else if(this.frameDying <= 15 && this.frameDying > 10){
                    this.frame = 7;
                }else if(this.frameDying <= 10 && this.frameDying > 5){
                    this.frame = 8;
                }else if(this.frameDying <= 5){
                    this.frame = 9;
                }

            }

            this.frameDying--;
        }

            drawBullets();

            if(this.life > 0){
                DrawTextureEx(
                this.battleTank[frame], 
                new Vector2(this.tankPosition[0], 
                    this.tankPosition[1]), 
                0.0f, 
                this.tankScale, 
                WHITE);
            }else if(this.frameDying > 0){
                DrawTextureEx(
                this.tankDying[frame], 
                new Vector2(this.tankPosition[0], 
                    this.tankPosition[1]), 
                0.0f, 
                this.tankScale, 
                WHITE);
            }
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
                    if(this.life > 0){
                        shoot(this.tankPosition[0], this.numberBullet);
                    }
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
                    if(this.life > 0){
                        shoot(this.tankPosition[0], this.numberBullet);
                    }
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

    public int getSpeed(){
        return this.speed;
    }

    @Override
    public void checkCollision(int enemyBulletPositions[][], boolean enemyBulletActive[], int enemyBulletDamage){

        for(int i=0; i<100; i++){ //100 bullets
            if(enemyBulletActive[i]){
                //x axis
                if(enemyBulletPositions[i][0] >= (this.tankPosition[0])
                    && enemyBulletPositions[i][0] < this.tankPosition[0] + 40 * this.tankScale){
    
                    //y axis        
                    if(enemyBulletPositions[i][1] <= (this.tankPosition[1] + 64 * this.tankScale)
                        && enemyBulletPositions[i][1] >= this.tankPosition[1]){
                            
                        if(this.life > 0){
                            enemyBulletActive[i] = false;
                            this.life -= enemyBulletDamage;
                        }

                    }
                }
            }

        }

    }

}
