import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

public class Enemy1 extends Aliens{

	private int speed;
	private int frameSpeed = 30; //for 6 frames per sec in 60 frames game
	private boolean dead[][] = new boolean[10][2];
	private int alienPositionX[] = new int[10];
	private int alienPositionY[] = new int[2];
	private float alienScale;
	private boolean direction; //true - Right, false - Left
	private int positionLimits[] = new int[2];
	//private int linePosition[] = new int[2];

	private Texture2D enemy1[] = new Texture2D[3];

	public Enemy1(int screenWidth, int screenHeight, int posY){
		this.speed = (int)(screenHeight/360); //speed 1 in 640x360 resolution
		this.alienScale = (float)(screenHeight/720.0f); //Alien Scale 0.5 in 640x360 resolution

		this.alienPositionY[0] = (int)(screenHeight /8 + 64 * this.alienScale * posY);
		this.alienPositionY[1] = (int)(screenHeight /8 + 64 * this.alienScale * (posY + 1));
		//this.alienPositionY = (int)(screenHeight /4);

		this.positionLimits[0] = (int)(screenWidth /2 - (5 * screenWidth /8)/2);
		this.positionLimits[1] = this.positionLimits[0] + (int)(5 * screenWidth /8) - (int)this.alienScale * 64;

		for(int i=0; i<10; i++){
			this.alienPositionX[i] = (int)(this.positionLimits[0] + i * 64);
		}

		this.direction = true;

		this.enemy1[0] = LoadTexture("assets/sprites/wr/enemy1_1.png");
		this.enemy1[1] = LoadTexture("assets/sprites/wr/enemy1_2.png");
		this.enemy1[2] = LoadTexture("assets/sprites/wr/enemy1_3.png");
	}

	public void draw(){

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

	public void drawEnemy(int frame){
		for(int i=0; i<10; i++){
			for(int j=0; j<2; j++){
				if(!this.dead[i][j]){
					DrawTextureEx(
				    	this.enemy1[frame], 
				        new Vector2(this.alienPositionX[i], 
				            this.alienPositionY[j]), 
				        0.0f, 
				        this.alienScale, 
				        WHITE);
				}
			}
			
			if(this.direction){
				this.alienPositionX[i] += this.speed;
			}else{
				this.alienPositionX[i] -= this.speed;
			}
		}
	}

}