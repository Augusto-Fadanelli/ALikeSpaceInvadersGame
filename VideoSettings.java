/*
* Video Settings Menu
*/

import static com.raylib.Jaylib.*; //for type Color

public class VideoSettings extends Screen
{
	private int choose = 0; //Choose in menu
	private int choice = 0;
	private int exit = 3;
	private Color chooseMenuColor[] = new Color[5];
	private String resolutions[] = new String[10];
	private int cont = 0;
	private int width;
	private int height;

	public VideoSettings(int w, int h, Color c){
		super(w, h, c);

		this.width = w;
		this.height = h;

		//set chooseMenuColor
		this.chooseMenuColor[0] = GREEN;
		for(int i=1; i<5; i++){
			this.chooseMenuColor[i] = RAYWHITE;
		}
	}

	@Override
	public void draw(){

        	BeginDrawing();
        	
        	ClearBackground(getBackgroundColor());

			int h = (int)(this.height/8*2);
			DrawText("Video Settings", positionCentralize(this.width, 7), h, size(50), RAYWHITE);
			h += size(50);
			String text;
			int j = 0;
			for(int i=0; i<5; i++){
				text = resolutions[j] + "x";
				j++;
				text += resolutions[j];
				j++;
				
				DrawText(text, positionCentralize(this.width, 2.4), h, size(30), chooseMenuColor[i]);
				h += size(30);
			}
                    
			input();

        	EndDrawing();
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

		if(IsKeyPressed(KEY_ENTER)){
			this.choice = this.choose;
			this.exit = -1;
		}else{
			this.choice = 0;
			this.exit = 3;
		}
	}

	public int getExit(){
		return exit;
	}

	public void setResolutions(int r){
		this.resolutions[this.cont] = Integer.toString(r);
		this.cont++;
		if(this.cont == 10){
			this.cont = 0;
		}
	}

	public String getWidthChoose(){
		return this.resolutions[this.choice*2];
	}

	public String getHeightChoose(){
		return this.resolutions[this.choice*2+1];
	}

}
