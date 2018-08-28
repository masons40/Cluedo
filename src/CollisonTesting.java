//collision testing
public class CollisonTesting {
	
	GameMechanics mech;
	Dimensions dim;
	int door;
	int[] testingDoor = {20,30,40,50,60,70,80,90,100,110};
	
	public CollisonTesting(GameMechanics mech){
		this.mech = mech;
		dim = mech.getDimensions();
	}
	
	public boolean testMove(String s, GameObject ob) {
		switch(s){
			case "u":
				if((dim.getVal(ob.getx(), (ob.gety())-1) == 0) && (dim.getValTwo(ob.getx(), (ob.gety())-1) == 0)) {
					return true;
				}else if(DoorTest(dim.getVal(ob.getx(), (ob.gety())-1))) {
					mech.setDoor(door,true);
				}
				break;
			case "d":
				if((dim.getVal(ob.getx(), (ob.gety())+1) == 0) && (dim.getValTwo(ob.getx(), (ob.gety())+1) == 0)) {
					return true;
				}else if(DoorTest(dim.getVal(ob.getx(), (ob.gety())+1))) {
					mech.setDoor(door,true);
				}
				break;
			case "r":
				if((dim.getVal((ob.getx()) + 1, ob.gety()) == 0) && (dim.getValTwo((ob.getx()) + 1, ob.gety()) == 0)) {
					return true;
				}else if(DoorTest(dim.getVal((ob.getx()) + 1, ob.gety()))) {
					mech.setDoor(door,true);
				}
				break;
			case "l":
				if((dim.getVal((ob.getx()) - 1, ob.gety()) == 0) && (dim.getValTwo((ob.getx()) - 1, ob.gety()) == 0)) {
					return true;
				}else if(DoorTest(dim.getVal((ob.getx()) - 1, ob.gety()))) {
					mech.setDoor(door,true);
				}
				break;
		}
		return false;
	}
	public boolean DoorTest(int doorNum) {
			for(int i = 0;i<testingDoor.length;i++) {
				if(doorNum == testingDoor[i]) {
					door = testingDoor[i];
					return true;
				}
			}
			return false;
	}
	
	public void resetDoor() {
		dim.setVal(mech.getOb().getx(), mech.getOb().gety(), mech.getOb().getDoor());
	}
}
