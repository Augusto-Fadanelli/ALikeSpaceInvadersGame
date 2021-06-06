/*
* Battle tank bullets features
*/

public class TankBullet
{
	private int shootRate = 0;

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

