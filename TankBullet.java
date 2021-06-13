/*
* Battle tank bullets features
*/

import com.raylib.Raylib;
import com.raylib.Jaylib.*;
import static com.raylib.Jaylib.*;

public class TankBullet
{
	private int shootRate = 0;
	private int speed;
	private boolean active[] = new boolean[10];
	private int bulletPositions[][] = new int[10][2];
	private float bulletScale;
	private int tankPositionY;
	private float tankScale;
	private Texture2D bullet;

	public TankBullet(int screenWidth, int screenHeight, int tPY, float tS){
		this.speed = (int)(screenHeight/45); //speed 8 in 640x360 resolution
		this.tankPositionY = tPY;
		this.tankScale = tS;

		this.bulletScale = (float)(screenHeight/1440.0f);
		this.bullet = LoadTexture("assets/sprites/bullet.png");
	}

	public void draw(){

		for(int i=0; i<10; i++){

			if(this.bulletPositions[i][1] > 0 && this.active[i]){
				this.bulletPositions[i][1] -= this.speed;

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

	public void shoot(int tankPositionX, int wichBullet){

		this.bulletPositions[wichBullet][0] = (int)(tankPositionX + this.tankScale *32 / 2);
	    this.bulletPositions[wichBullet][1] = (int)(this.tankPositionY + this.tankScale *32 / 4);
	    this.active[wichBullet] = true;

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

