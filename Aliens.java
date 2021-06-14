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
	protected int frameSpeed = 80;

	//Size and Position
	protected int shootPosY[] = new int[10];
	protected int alienPositionX[] = new int[10];
	protected int alienPositionY[] = new int[2];
	protected int positionLimits[] = new int[2];
	protected float alienScale;

	//Mechanics and others
	protected boolean shoot = false;
    protected int numberBullet = 0; //Informs wich bullet
    protected boolean canShoot[] = new boolean[10];
    protected boolean canNotShoot[] = new boolean[10]; //false - Can Shoot, true - Can not shoot
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
	private int bulletPositions[][] = new int[100][2];
	private int enemyPositionY[] = new int[10];
	protected float enemyScale;
	private float bulletScale;

	//Mechanics and Others
	private int shootRate = 0;
	private boolean active[] = new boolean[100];
    //---------------------------------------------------------



    public Aliens(int screenWidth, int screenHeight, int ePY){
		this.speed = (int)(screenHeight/180); //speed 2 in 640x360 resolution

		for(int i=0; i<10; i++){
			this.enemyPositionY[i] = ePY;
		}

		this.bulletScale = (float)(screenHeight/1440.0f);
		this.bullet = LoadTexture("assets/sprites/enemy_bullet.png");
	}

	//Abstract methods
	public abstract void draw();
	public abstract void setShootPosY();
	public abstract void isDead(boolean isDead[][]);
	public abstract void setCanShoot(boolean isDead[][]);
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

	public void aShoot(){

		setShootPosY();

        if(0 == getShootRate()){
            this.shoot = true;
            setShootRate(25);
            shoot(this.alienPositionX[this.r], this.r, this.numberBullet);
            this.numberBullet++;
                
            if(this.numberBullet > 99){
                this.numberBullet = 0;
            }
        }

        timeShootRate();
	}

	public void randomPositionX(){
		this.r = random.nextInt(10);
	}



	//Aliens bullets features
	//---------------------------------------------------------
	@Override
	public void drawBullets(){

		for(int i=0; i<100; i++){

			if(this.bulletPositions[i][1] > 0 && this.active[i]){
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

    public void shoot(int enemyPositionX, int wichEnemy, int wichBullet){

		this.bulletPositions[wichBullet][0] = (int)(enemyPositionX + this.enemyScale *32 / 2);
	    this.bulletPositions[wichBullet][1] = (int)(this.enemyPositionY[wichEnemy] + this.enemyScale *32 / 4);
	    this.active[wichBullet] = true;

	}

	public void setEnemyPositionY(int ePY, int wichEnemy){
		this.enemyPositionY[wichEnemy] = ePY;
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

