import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

public class Enemy1 extends Aliens{

	public Enemy1(int screenWidth, int screenHeight, int posY){

		super(screenWidth, screenHeight, (int)(720 /8 + 64 * (3 + 1)));

		this.frameSpeed = 30;

		this.speed = (int)(screenHeight/360); //speed 1 in 640x360 resolution
		this.alienScale = (float)(screenHeight/720.0f); //Alien Scale 0.5 in 640x360 resolution

		//this.enemyScale = this.alienScale;
		this.enemyScale = 1.0f;

		this.alienPositionY[0] = (int)(screenHeight /8 + 64 * this.alienScale * posY);
		this.alienPositionY[1] = (int)(screenHeight /8 + 64 * this.alienScale * (posY + 1));
		//this.alienPositionY = (int)(screenHeight /4);

		this.positionLimits[0] = (int)(screenWidth /2 - (5 * screenWidth /8)/2);
		this.positionLimits[1] = this.positionLimits[0] + (int)(5 * screenWidth /8) - (int)this.alienScale * 64;

		for(int i=0; i<10; i++){
			this.alienPositionX[i] = (int)(this.positionLimits[0] + i * 64);
		}

		this.direction = true;

		this.alienSprites[0] = LoadTexture("assets/sprites/wr/enemy1_1.png");
		this.alienSprites[1] = LoadTexture("assets/sprites/wr/enemy1_2.png");
		this.alienSprites[2] = LoadTexture("assets/sprites/wr/enemy1_3.png");
	}

	@Override
	public void draw(){

				//Alien Bullets
				/*randomPositionX();
				if(!this.canNotShoot[this.r]){
				aShoot();
				}
				drawBullets();*/

				//for 6 frames per sec in 60 frames game
				if(this.frameSpeed <= 30 && this.frameSpeed > 20){
					drawEnemy(0);
				}else if(this.frameSpeed <= 20 && this.frameSpeed > 10){
					drawEnemy(1);
				}else{
					drawEnemy(2);

					if(frameSpeed == 0){
						this.frameSpeed = 30;
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
	public void setShootPosY(){
		for(int i=0; i<10; i++){
			if(!this.dead[i][1]){
				this.shootPosY[i] = this.alienPositionY[1];
				setEnemyPositionY(this.alienPositionY[1], i);
			}else if(!this.dead[i][0]){
				if(this.canShoot[i]){
					setEnemyPositionY(this.alienPositionY[0], i);
					this.shootPosY[i] = this.alienPositionY[0];
				}
			}else{
				this.shootPosY[i] = -1;
				this.canNotShoot[i] = true;
			}
		}
	}

	@Override
	public void isDead(boolean isDead[][]){
		for(int i=0; i<10; i++){
			isDead[i][0] = this.dead[i][1];
			isDead[i][1] = this.dead[i][0];
		}
	}

	@Override
	public void setCanShoot(boolean isDead[][]){
		for(int i=0; i<10; i++){
			if(isDead[i][0]){ 
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
						&& bulletPositions[i][0] < this.alienPositionX[x] + 40 * this.alienScale){
					
						for(int j=0; j<2; j++){
							//y axis		
							if(bulletPositions[i][1] <= (this.alienPositionY[j] + 40 * this.alienScale)
								&& bulletPositions[i][1] >= this.alienPositionY[j]){
										if(!this.dead[x][j]){
											bulletActive[i] = false;
										}
										this.dead[x][j] = true;
							}
							
						}
					}
				}
			}

		}

	}

}