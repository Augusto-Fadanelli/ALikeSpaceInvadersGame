/*
* Aliens bullets features
*/

import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

public class EnemyBullet
{
	private int shootRate = 0;
	private int speed;
	private boolean active[] = new boolean[100];
	private int bulletPositions[][] = new int[100][2];
	private float bulletScale;
	private int enemyPositionY[] = new int[10];
	private float enemyScale;
	private Texture2D bullet;
	
	public EnemyBullet(int screenWidth, int screenHeight, int ePY, float eS){
		this.speed = (int)(screenHeight/180); //speed 2 in 640x360 resolution
		this.enemyScale = eS;

		for(int i=0; i<10; i++){
			this.enemyPositionY[i] = ePY;
		}

		this.bulletScale = (float)(screenHeight/1440.0f);
		this.bullet = LoadTexture("assets/sprites/enemy_bullet.png");
	}

	public void draw(){

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

	public int[][] getShootPositions(){
		return this.bulletPositions;
	}

	public boolean[] getShootActive(){
		return this.active;
	}

	public void setShootRate(int s){
		this.shootRate = s;
	}
	public int getShootRate(){
		return this.shootRate;
	}

	public void timeShootRate(){
		if(this.shootRate > 0){
			this.shootRate--;
		}
	}
}

