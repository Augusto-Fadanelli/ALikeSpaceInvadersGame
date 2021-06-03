/*
* Video Settings Menu
*/

import static com.raylib.Jaylib.*; //for type Color

public class VideoSettings extends Screen
{
	private int choose = 0; //Choose in menu
	private Color chooseMenuColor[] = new Color[5];
	private String resolutions[] = new String[10];
	private int cont = 0;

	public VideoSettings(int width, int height, Color c){
		super(width, height, c);

		//set chooseMenuColor
		this.chooseMenuColor[0] = GREEN;
		for(int i=1; i<5; i++){
			this.chooseMenuColor[i] = RAYWHITE;
		}
	}

	@Override
	public void draw(){

		while(!IsKeyPressed(KEY_ENTER) && !WindowShouldClose()){

        	BeginDrawing();
        	
        	ClearBackground(getBackgroundColor());

			int height = 200;
			DrawText("Video Settings", 280, 100, 50, RAYWHITE);
			String text;
			int j = 0;
			for(int i=0; i<5; i++){
				text = resolutions[j] + "x";
				j++;
				text += resolutions[j];
				DrawText(text, 280, height, 30, chooseMenuColor[i]);
				height += 30;
			}
                    
			input();

        	EndDrawing();
        }
	}

	public void input(){ //-1 up - 1 down
		this.chooseMenuColor[this.choose] = RAYWHITE;
		if(IsKeyPressed(KEY_UP)){ //up
			if(this.choose == 0){
				this.choose = 4;
			}else{
				this.choose--;
			}
		}else if(IsKeyPressed(KEY_DOWN)){ //down
			if(this.choose == 4){
				this.choose = 0;
			}else{
				this.choose++;
			}
		}
		this.chooseMenuColor[this.choose] = GREEN;
	}

	public void setResolutions(int r){
		this.resolutions[this.cont] = Integer.toString(r);
		this.cont++;
		if(this.cont == 10){
			this.cont = 0;
		}
	}

}
