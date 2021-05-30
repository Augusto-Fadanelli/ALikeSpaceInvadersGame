/*
* Menu Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class MenuScreen extends Screen
{
	private int choose = 0; //Choose in menu
	private Color chooseMenuColor[] = new Color[5];

	public MenuScreen(int width, int height, Color c){
		super(width, height, c);

		//set chooseMenuColor
		this.chooseMenuColor[0] = GREEN;
		for(int i=1; i<5; i++){
			this.chooseMenuColor[i] = RAYWHITE;
		}
	}

	public void draw(){
		ClearBackground(getBackgroundColor());

		DrawText("Menu", 290, 100, 50, RAYWHITE);
		DrawText("Single Player", 280, 200, 30, chooseMenuColor[0]); //choose = 0
		DrawText("Two Players", 280, 230, 30, chooseMenuColor[1]); //choose = 1
		DrawText("Rank", 280, 260, 30, chooseMenuColor[2]); //choose = 2
		DrawText("Video Settings", 280, 290, 30, chooseMenuColor[3]); //choose = 3
		DrawText("About", 280, 320, 30, chooseMenuColor[4]); //choose = 4
	}

	public void input(int c){ //-1 up - 1 down
		this.chooseMenuColor[this.choose] = RAYWHITE;
		if(c == -1){ //up
			if(this.choose == 0){
				this.choose = 4;
			}else{
				this.choose--;
			}
		}else if(c == 1){ //down
			if(this.choose == 4){
				this.choose = 0;
			}else{
				this.choose++;
			}
		}
		this.chooseMenuColor[this.choose] = GREEN;
	}

}
