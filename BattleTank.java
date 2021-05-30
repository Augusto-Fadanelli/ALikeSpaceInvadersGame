/*
* Battle tank features
*/

import static com.raylib.Jaylib.*; //for Color type

public class BattleTank
{
    
    private int tankPosition; //Position in width
    private boolean player2 = false; //Informs if there's two players
    private Color color = GREEN;
    private int speed = 5;

    public BattleTank(int screenWidth, boolean p2, int player){
        if(p2){
            if(0 == player){
                this.tankPosition = (screenWidth/20)*6; //Break screen in 20 parts
            }else{
                this.tankPosition = (screenWidth/20)*13;
            }
        }else{
            this.tankPosition = screenWidth/2;
        }
        this.player2 = p2;
    }

    public void setTankPosition(int newPosition){
        this.tankPosition = newPosition;
    }
    public int getTankPosition(){
        return this.tankPosition;
    }

    public void setColor(Color c){
        this.color = c;
    }
    public Color getColor(){
        return this.color;
    }

    public int getSpeed(){
        return this.speed;
    }
}
