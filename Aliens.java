import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

import java.util.Random;

public abstract class Aliens implements Bullets{

	//Aliens
	//---------------------------------------------------------
	//Textures
	protected Texture2D alienSprites[] = new Texture2D[8];

	//Frames and Time
	protected int speed; 
	protected int frameSpeed;

	//Size and Position
	private int screen[] = new int[2]; //[0] - Screen Width; [1] - Screen Height
	protected int alienPositionX[] = new int[10];
	protected int alienPositionY[] = new int[2];
	protected int positionLimits[] = new int[2];
	protected float alienScale;

	//Mechanics and others
    protected int numberBullet = 0; //Informs wich bullet
	protected boolean dead[][] = new boolean[10][2];
	protected boolean direction; //true - Right, false - Left
	Random random = new Random();
	protected int r; //randon number
	//---------------------------------------------------------



	//Aliens bullets features
	//---------------------------------------------------------
	//Textures
	private Texture2D bullet;

	//Size and Position
	protected int bulletPositions[][] = new int[100][2];
	private float bulletScale;

	//Mechanics and Others
	protected int shootRate = 0;
	protected boolean active[] = new boolean[100];
    //---------------------------------------------------------



    public Aliens(int screenWidth, int screenHeight){

    	screen[0] = screenWidth;
    	screen[1] = screenHeight;

		this.speed = (int)(screenHeight/180); //speed 2 in 640x360 resolution

		this.bulletScale = (float)(screenHeight/1440.0f);
		this.bullet = LoadTexture("assets/sprites/enemy_bullet.png");
	}

	//Abstract methods
	public abstract void draw(int whoCanShoot[]);
	public abstract void setCanShoot(int whoCanShoot[]);
	public abstract void checkCollision(int bulletPositions[][], boolean bulletActive[]);

	public void drawEnemy(int frame){
		for(int i=0; i<10; i++){
			for(int j=0; j<2; j++){
				if(!this.dead[i][j]){
					DrawTextureEx(
				    	this.alienSprites[frame], 
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


	//Aliens bullets features
	//---------------------------------------------------------

	public void randomPositionX(){
		this.r = random.nextInt(10);
	}

	@Override
	public void drawBullets(){

		for(int i=0; i<100; i++){

			if(this.bulletPositions[i][1] < this.screen[1] && this.active[i]){
				this.bulletPositions[i][1] += this.speed;

				DrawTextureEx(
                this.bullet, 
                new Vector2(this.bulletPositions[i][0], 
                    this.bulletPositions[i][1]), 
                0.0f, 
                this.bulletScale, 
                WHITE);

			}else{
				this.active[i] = false;
			}

		}
	}

    public void shoot(){

    	int enemyPositionX = this.alienPositionX[this.r];
    	int wichEnemy = this.r;
    	int wichBullet = this.numberBullet;

    	if(0 == this.shootRate){
            this.shootRate = 25;

	    	int positionY = 0;
	    	if(!this.dead[wichEnemy][1]){
	    		positionY = 1;
	    	}

			this.bulletPositions[wichBullet][0] = (int)(enemyPositionX + this.alienScale *32 / 2);
		    this.bulletPositions[wichBullet][1] = (int)(this.alienPositionY[positionY] + this.alienScale *32 / 4);
		    this.active[wichBullet] = true;

		    	this.numberBullet++;
	                
	            if(this.numberBullet > 99){
	                this.numberBullet = 0;
	            }
	        }

        timeShootRate();

	}

	@Override
    public int[][] getShootPositions(){
		return this.bulletPositions;
	}

	@Override
    public boolean[] getShootActive(){
		return this.active;
	}

	@Override
    public void setShootRate(int s){
		this.shootRate = s;
	}

	@Override
    public int getShootRate(){
		return this.shootRate;
	}

	@Override
    public void timeShootRate(){
		if(this.shootRate > 0){
			this.shootRate--;
		}
	}
    //---------------------------------------------------------
}

