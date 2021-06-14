import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

public class Enemy3 extends Aliens{

	public Enemy3(int screenWidth, int screenHeight, int posY){

		super(screenWidth, screenHeight, (int)(720 /8 + 64 * (3 + 1)));

		this.frameSpeed = 30;

		this.speed = (int)(screenHeight/360); //speed 1 in 640x360 resolution
		this.alienScale = (float)(screenHeight/720.0f); //Alien Scale 0.5 in 640x360 resolution

		//this.enemyScale = this.alienScale;
		this.enemyScale = 1.0f;

		this.alienPositionY[0] = (int)(screenHeight /8 + 64 * this.alienScale * posY);

		this.positionLimits[0] = (int)(screenWidth /2 - (5 * screenWidth /8)/2);
		this.positionLimits[1] = this.positionLimits[0] + (int)(5 * screenWidth /8) - (int)this.alienScale * 64;

		for(int i=0; i<10; i++){
			this.alienPositionX[i] = (int)(this.positionLimits[0] + i * 64);
		}

		this.direction = true;

		this.alienSprites[0] = LoadTexture("assets/sprites/wr/enemy3_1.png");
		this.alienSprites[1] = LoadTexture("assets/sprites/wr/enemy3_2.png");
		this.alienSprites[2] = LoadTexture("assets/sprites/wr/enemy3_3.png");
		this.alienSprites[3] = LoadTexture("assets/sprites/wr/enemy3_4.png");
		this.alienSprites[4] = LoadTexture("assets/sprites/wr/enemy3_5.png");
		this.alienSprites[5] = LoadTexture("assets/sprites/wr/enemy3_6.png");
		this.alienSprites[6] = LoadTexture("assets/sprites/wr/enemy3_7.png");
		this.alienSprites[7] = LoadTexture("assets/sprites/wr/enemy3_8.png");

	}

	@Override
	public void draw(){

				randomPositionX();
				if(!this.canNotShoot[this.r]){
				aShoot();
				}
				drawBullets();

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

	@Override
	public void drawEnemy(int frame){
		for(int i=0; i<10; i++){
			if(!this.dead[i][0]){
				DrawTextureEx(
				    this.alienSprites[frame], 
				    new Vector2(this.alienPositionX[i], 
				        this.alienPositionY[0]), 
				    0.0f, 
				    this.alienScale, 
				    WHITE);
			}
			
			if(this.direction){
				this.alienPositionX[i] += this.speed;
			}else{
				this.alienPositionX[i] -= this.speed;
			}
		}
	}

	@Override
	public void setShootPosY(){
		for(int i=0; i<10; i++){
			if(this.canShoot[i]){
				if(!this.dead[i][0]){
					this.shootPosY[i] = this.alienPositionY[0];
					setEnemyPositionY(this.alienPositionY[0], i);
				}else{
					this.shootPosY[i] = -1;
					this.canNotShoot[i] = true;
				}
			}
		}
	}

	@Override
	public void isDead(boolean isDead[][]){
		for(int i=0; i<10; i++){
			isDead[i][4] = this.dead[i][0];
		}
	}

	@Override
	public void setCanShoot(boolean isDead[][]){
		for(int i=0; i<10; i++){
			if(isDead[i][0] && isDead[i][1] && isDead[i][2] && isDead[i][3]){
				this.canShoot[i] = true;
			}
		}
	}

	@Override
	public void checkCollision(int bulletPositions[][], boolean bulletActive[]){

		for(int i=0; i<10; i++){ //10 bullets
			if(bulletActive[i]){
				//x axis
				for(int x=0; x<10; x++){
					if(bulletPositions[i][0] >= (this.alienPositionX[x])
						&& bulletPositions[i][0] < this.alienPositionX[x] + 64 * this.alienScale){
	
						//y axis		
						if(bulletPositions[i][1] <= (this.alienPositionY[0] + 40 * this.alienScale)
							&& bulletPositions[i][1] >= this.alienPositionY[0]){
							
							if(!this.dead[x][0]){
								bulletActive[i] = false;
							}
							this.dead[x][0] = true;
						}
					}
				}
			}

		}

	}

}