public interface Bullets{
    
    public void drawBullets();
    //public void shoot(int enemyPositionX, int wichEnemy, int wichBullet)
    public int[][] getShootPositions();

    public boolean[] getShootActive();

    public void setShootRate(int s);

    public int getShootRate();

    public void timeShootRate();
}
