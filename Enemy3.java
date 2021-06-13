import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

public class Enemy3 extends Aliens{

	private int speed;
	private int frameSpeed = 80;
	private boolean dead[] = new boolean[10];
	private int alienPositionX[] = new int[10];
	private int alienPositionY;
	private float alienScale;
	private boolean direction; //true - Right, false - Left
	private int positionLimits[] = new int[2];

	private Texture2D enemy3[] = new Texture2D[8];

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