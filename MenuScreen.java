/*
* Menu Screen
*/

import static com.raylib.Jaylib.*; //for type Color

public class MenuScreen extends Screen
{
	private int choose = 0; //Choose in menu
	private Color chooseMenuColor[] = new Color[6];
	private int width;
	private int height;

	public MenuScreen(int w, int h, Color c){
		super(w, h, c);

		this.width = w;
		this.height = h;

		//set chooseMenuColor
		this.chooseMenuColor[0] = GREEN;
		for(int i=1; i<6; i++){
			this.chooseMenuColor[i] = RAYWHITE;
		}
	}

	@Override
	public void draw(){

		while(!IsKeyPressed(KEY_ENTER) && !WindowShouldClose()){
            BeginDrawing();
            
            ClearBackground(getBackgroundColor());

			DrawText(
				"Menu", 
				positionCentralize(this.width, 2.4), 
				(int)(this.height/8*2), 
				size(50), 
				RAYWHITE);

			//choose = 0
			DrawText(
				"Single Player", 
				positionCentralize(this.width, 3.8), 
				(int)(this.height/8*2 + size(50)), 
				size(30), 
				chooseMenuColor[0]);

			//choose = 1
			DrawText(
				"Two Players", 
				positionCentralize(this.width, 3.6), 
				(int)(this.height/8*2 + size(80)), 
				size(30), 
				chooseMenuColor[1]);

			//choose = 2
			DrawText(
				"Rank", 
				positionCentralize(this.width, 1.4), 
				(int)(this.height/8*2 + size(110)), 
				size(30), 
				chooseMenuColor[2]);

			//choose = 3
			DrawText(
				"Video Settings", 
				positionCentralize(this.width, 4.2), 
				(int)(this.height/8*2 + size(140)), 
				size(30), 
				chooseMenuColor[3]);

			//choose = 4
			DrawText(
				"About", 
				positionCentralize(this.width, 1.7), 
				(int)(this.height/8*2 + size(170)), 
				size(30), 
				chooseMenuColor[4]);

			//choose = 5
			DrawText(
				"Exit", 
				positionCentralize(this.width, 1.1), 
				(int)(this.height/8*2 + size(200)), 
				size(30), 
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
