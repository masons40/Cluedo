public class Dimensions {
	
	
	int x, y;
	//corresponds to the map, this allows for collision detection in future updates and allows 
	//for players to move
	private int[][] dimensions = {
			
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,5,5,5,5,5,1,0,1,4,4,4,4,4,4,4,1,0,1,17,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,5,5,5,5,5,50,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,15,5,5,5,5,5,0,0,0,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,4,4,4,4,4,40,0,0,0,30,3,3,3,3,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,0,4,4,40,4,4,4,0,0,0,0,0,0,0,0,0,1,1},
			{1,1,0,6,6,6,60,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,0,0,6,6,6,6,6,60,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,2,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,20,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,110,0,20,2,2,2,2,2,2,1,1},
			{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,2,2,2,2,2,2,2,1,1},
			{1,0,0,6,6,6,6,6,60,0,0,11,11,11,11,11,11,11,0,2,2,20,2,2,2,2,1,1},
			{1,1,0,6,6,6,60,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
			{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
			{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,9,90,9,0,0,0,100,10,10,1,1,1},
			{1,1,7,7,7,70,0,0,0,8,80,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,17,0,0,8,8,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,7,0,0,8,8,8,8,8,0,90,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,7,0,0,8,8,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,7,0,0,8,8,8,8,80,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
			{1,1,7,7,7,7,1,0,1,8,8,8,8,8,1,1,9,9,9,1,0,1,15,10,10,10,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	
private int[][] dimensionsTwo = {
			
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,5,5,5,5,5,1,0,1,4,4,4,4,4,4,4,1,47,1,17,3,3,3,3,3,1,1},
		{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
		{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
		{1,1,5,5,5,5,5,5,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
		{1,1,5,5,5,5,5,50,0,0,4,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
		{1,1,15,5,5,5,5,5,0,0,0,4,4,4,4,4,4,0,0,0,3,3,3,3,3,3,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,4,4,4,4,4,40,0,0,0,30,3,3,3,3,1,1,1},
		{1,1,0,0,0,0,0,0,0,0,0,4,4,40,4,4,4,0,0,0,0,0,0,0,0,47,1,1},
		{1,1,0,6,6,6,60,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{1,47,0,6,6,6,6,6,60,0,0,0,0,0,0,0,0,0,0,2,2,2,2,2,2,2,1,1},
		{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,2,2,2,2,2,2,2,1,1},
		{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,20,2,2,2,2,2,2,1,1},
		{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,110,0,20,2,2,2,2,2,2,1,1},
		{1,6,6,6,6,6,6,6,6,0,0,11,11,11,11,11,11,11,0,2,2,2,2,2,2,2,1,1},
		{1,47,0,6,6,6,6,6,60,0,0,11,11,11,11,11,11,11,0,2,2,20,2,2,2,2,1,1},
		{1,1,0,6,6,6,60,6,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
		{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
		{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,9,90,9,0,0,0,100,10,10,1,1,1},
		{1,1,7,7,7,70,0,0,0,8,80,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
		{1,1,7,7,7,7,17,0,0,8,8,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
		{1,1,7,7,7,7,7,0,0,8,8,8,8,8,0,90,9,9,9,9,0,0,10,10,10,10,1,1},
		{1,1,7,7,7,7,7,0,0,8,8,8,8,8,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
		{1,1,7,7,7,7,7,0,0,8,8,8,8,80,0,9,9,9,9,9,0,0,10,10,10,10,1,1},
		{1,1,7,7,7,7,1,47,1,8,8,8,8,8,1,1,9,9,9,1,47,1,15,10,10,10,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
	};
	
	/*
	public void reset() {
		for(int i=0;i<dimensionsTwo.length;i++) {
			for(int j=0;j<dimensionsTwo.length;j++) {
				setVal(i,j,0);
			}
		}
	}
	*/

	public void print() {
		for(int i=0;i<dimensionsTwo.length;i++) {
			for(int j=0;j<dimensionsTwo.length;j++) {
				System.out.printf("%d ",dimensionsTwo[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
	public boolean checkPosAvailable(int x, int y,int door) {
		
		if(dimensionsTwo[y][x] != 47 && dimensions[y][x] == door) {
			this.x = y;
			this.y = x;
			return true;
		}else if(dimensionsTwo[y][x+1] != 47 && dimensions[y][x+1] == door) {
			this.x = y;
			this.y = x+1;
			return true;
		}else if(dimensionsTwo[y][x-1] != 47 && dimensions[y][x-1] == door) {
			this.x = y;
			this.y = x-1;
			return true;
		}else if(dimensionsTwo[y+1][x] != 47 && dimensions[y+1][x] == door) {
			this.x = y+1;
			this.y = x;
			return true;
		}else if(dimensionsTwo[y-1][x] != 47 && dimensions[y-1][x] == door) {
			this.x = y-1;
			this.y = x;
			return true;
		}else if(dimensionsTwo[y][x+2] != 47 && dimensions[y][x+2] == door) {
			this.x = y;
			this.y = x+2;
			return true;
		}else if(dimensionsTwo[y][x-2] != 47 && dimensions[y][x-2] == door) {
			this.x = y;
			this.y = x-2;
			return true;
		}
		return false;
	}
	
	public boolean checkPosAvailableWeapon(int x, int y) {
		
		if(dimensionsTwo[y][x] != 47) {
			this.x = y;
			this.y = x;
			return true;
		}else if(dimensionsTwo[y][x+1] != 47) {
			this.x = y;
			this.y = x+1;
			return true;
		}else if(dimensionsTwo[y][x-1] != 47) {
			this.x = y;
			this.y = x-1;
			return true;
		}else if(dimensionsTwo[y+1][x] != 47) {
			this.x = y+1;
			this.y = x;
			return true;
		}else if(dimensionsTwo[y-1][x] != 47) {
			this.x = y-1;
			this.y = x;
			return true;
		}else if(dimensionsTwo[y][x+2] != 47) {
			this.x = y;
			this.y = x+2;
			return true;
		}else if(dimensionsTwo[y][x-2] != 47) {
			this.x = y;
			this.y = x-2;
			return true;
		}
		return false;
	}
	
	public int getVal(int i, int j) {
		return dimensions[i][j];
	}
	public int getValTwo(int i, int j) {
		return dimensionsTwo[i][j];
	}
	
	public int getX() {
		int x = this.x;
		this.x = 0;
		return x;
	}
	public int getY() {
		int y = this.y;
		this.y = 0;
		return y;
	}
	
	public void setVal(int i, int j,int change) {
		dimensionsTwo[i][j] = change;
		//print();
	}
}