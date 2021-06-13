import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

import java.util.Random;

public class Enemy3 extends Aliens{

	private int speed;
	private int frameSpeed = 80;
	private boolean dead[] = new boolean[10];
	private int alienPositionX[] = new int[10];
	private int alienPositionY;
	private float alienScale;
	private boolean direction; //true - Right, false - Left
	private int positionLimits[] = new int[2];

	//-------------------------
	private int r; //randon number

	//Mechanics and others
	private boolean shoot = false;
    private int numberBullet = 0; //Informs wich bullet
    private int shootPosY[] = new int[10];
    private boolean canShoot[] = new boolean[10];
    private boolean canNotShoot[] = new boolean[10]; //false - Can Shoot, true - Can not shoot
    //-------------------------

	private Texture2D enemy3[] = new Texture2D[8];

	//-------------------------
	Random random = new Random();
	EnemyBullet bullet = new EnemyBullet(1280, 720, (int)(720 /8 + 64 * (3 + 1)), 1.0f); //Bullets of tank1
	//-------------------------

	public Enemy3(int screenWidth, int screenHeight, int posY){
		this.speed = (int)(screenHeight/360); //speed 1 in 640x360 resolution
		this.alienScale = (float)(screenHeight/720.0f); //Alien Scale 0.5 in 640x360 resolution

		this.alienPositionY = (int)(screenHeight /8 + 64 * this.alienScale * posY);

		this.positionLimits[0] = (int)(screenWidth /2 - (5 * screenWidth /8)/2);
		this.positionLimits[1] = this.positionLimits[0] + (int)(5 * screenWidth /8) - (int)this.alienScale * 64;

		for(int i=0; i<10; i++){
			this.alienPositionX[i] = (int)(this.positionLimits[0] + i * 64);
		}

		this.direction = true;

		this.enemy3[0] = LoadTexture("assets/sprites/wr/enemy3_1.png");
		this.enemy3[1] = LoadTexture("assets/sprites/wr/enemy3_2.png");
		this.enemy3[2] = LoadTexture("assets/sprites/wr/enemy3_3.png");
		this.enemy3[3] = LoadTexture("assets/sprites/wr/enemy3_4.png");
		this.enemy3[4] = LoadTexture("assets/sprites/wr/enemy3_5.png");
		this.enemy3[5] = LoadTexture("assets/sprites/wr/enemy3_6.png");
		this.enemy3[6] = LoadTexture("assets/sprites/wr/enemy3_7.png");
		this.enemy3[7] = LoadTexture("assets/sprites/wr/enemy3_8.png");

	}

	public void draw(){

				randomPositionX();
				if(!this.canNotShoot[this.r]){
				shoot();
				}
				bullet.draw();

				//for 6 frames per sec in 60 frames game
				if(this.frameSpeed <= 80 && this.frameSpeed > 70){
					drawEnemy(0);
				}else if(this.frameSpeed <= 70 && this.frameSpeed > 60){
					drawEnemy(1);
				}else if(this.frameSpeed <= 60 && this.frameSpeed > 50){
					drawEnemy(2);
				}else if(this.frameSpeed <= 50 && this.frameSpeed > 40){
					drawEnemy(3);
				}else if(this.frameSpeed <= 40 && this.frameSpeed > 30){
					drawEnemy(4);
				}else if(this.frameSpeed <= 30 && this.frameSpeed > 20){
					drawEnemy(5);
				}else if(this.frameSpeed <= 20 && this.frameSpeed > 10){
					drawEnemy(6);
				}else{
					drawEnemy(7);

					if(frameSpeed == 0){
						this.frameSpeed = 80;
					}
				}
				this.frameSpeed--;

		if (this.alienPositionX[9] >= this.positionLimits[1]){
			this.direction = false;
		}else if(this.alienPositionX[0] <= this.positionLimits[0]){
			this.direction = true;
		}

	}

	public void drawEnemy(int frame){
		for(int i=0; i<10; i++){
			//for(int j=0; j<2; j++){
				if(!this.dead[i]){
					DrawTextureEx(
				    	this.enemy3[frame], 
				        new Vector2(this.alienPositionX[i], 
				            this.alienPositionY), 
				        0.0f, 
				        this.alienScale, 
				        WHITE);
				}
			//}
			
			if(this.direction){
				this.alienPositionX[i] += this.speed;
			}else{
				this.alienPositionX[i] -= this.speed;
			}
		}
	}

	public void shoot(){

		setShootPosY();

       	if(0 == bullet.getShootRate()){
            this.shoot = true;
            bullet.setShootRate(25);
            bullet.shoot(this.alienPositionX[this.r], this.r, this.numberBullet);
            this.numberBullet++;
                
            if(this.numberBullet > 90){
                this.numberBullet = 0;
            }
        }

        bullet.timeShootRate();

	}

	public void randomPositionX(){
		this.r = random.nextInt(10);
	}

	public void setShootPosY(){
		for(int i=0; i<10; i++){
			if(this.canShoot[i]){
				if(!this.dead[i]){
					this.shootPosY[i] = this.alienPositionY;
					bullet.setEnemyPositionY(this.alienPositionY, i);
				}else{
					this.shootPosY[i] = -1;
					this.canNotShoot[i] = true;
				}
			}
		}
	}

	public void isDead(boolean isDead[][]){
		for(int i=0; i<10; i++){
			isDead[i][4] = this.dead[i];
		}
	}

	public void setCanShoot(boolean isDead[][]){
		for(int i=0; i<10; i++){
			if(isDead[i][0] && isDead[i][1] && isDead[i][2] && isDead[i][3]){
				this.canShoot[i] = true;
			}
		}
	}

	public void checkCollision(int bulletPositions[][], boolean bulletActive[]){

		for(int i=0; i<10; i++){ //10 bullets
			if(bulletActive[i]){
				//x axis
				for(int x=0; x<10; x++){
					if(bulletPositions[i][0] >= (this.alienPositionX[x])
						&& bulletPositions[i][0] < this.alienPositionX[x] + 64 * this.alienScale){
					
						//for(int j=0; j<2; j++){
							//y axis		
							if(bulletPositions[i][1] <= (this.alienPositionY + 40 * this.alienScale)
								&& bulletPositions[i][1] >= this.alienPositionY){
										if(!this.dead[x]){
											bulletActive[i] = false;
										}
										this.dead[x] = true;
							}
							
						//}
					}
				}
			}

		}

	}

}