/*
* Menu Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class MenuScreen extends Screen
{
	private int choose = 0; //Choose in menu
	private Color chooseMenuColor[] = new Color[6];

	public MenuScreen(int width, int height, Color c){
		super(width, height, c);

		//set chooseMenuColor
		this.chooseMenuColor[0] = GREEN;
		for(int i=1; i<6; i++){
			this.chooseMenuColor[i] = RAYWHITE;
		}
	}

	@Override
	public void draw(){

		int size[] = new int[2];
		size[0] = (int)(5 * this.width /64); //Size 30 inn 
		size[1] = (int)(3 * this.width /64);

		while(!IsKeyPressed(KEY_ENTER) && !WindowShouldClose()){
            BeginDrawing();
            
            ClearBackground(getBackgroundColor());

			DrawText(
				"Menu", 
				290, 
				100, 
				50, 
				RAYWHITE);

			//choose = 0
			DrawText(
				"Single Player", 
				280, 
				200, 
				30, 
				chooseMenuColor[0]);

			//choose = 1
			DrawText(
				"Two Players", 
				280, 
				230, 
				30, 
				chooseMenuColor[1]);

			//choose = 2
			DrawText(
				"Rank", 
				280, 
				260, 
				30, 
				chooseMenuColor[2]);

			//choose = 3
			DrawText(
				"Video Settings", 
				280, 
				290, 
				30, 
				chooseMenuColor[3]);

			//choose = 4
			DrawText(
				"About", 
				280, 
				320, 
				30, 
				chooseMenuColor[4]);

			//choose = 5
			DrawText(
				"Exit", 
				280, 
				350, 
				30, 
				chooseMenuColor[5]);
            
            input();

            EndDrawing();
        }
	}

	public void input(){ //-1 up - 1 down
		this.chooseMenuColor[this.choose] = RAYWHITE;
		if(IsKeyPressed(KEY_UP)){ //up
			if(this.choose == 0){
				this.choose = 5;
			}else{
				this.choose--;
			}
		}else if(IsKeyPressed(KEY_DOWN)){ //down
			if(this.choose == 5){
				this.choose = 0;
			}else{
				this.choose++;
			}
		}
		this.chooseMenuColor[this.choose] = GREEN;
	}

	public int chooseDraw(){
		draw();
		return this.choose;
	}

}
