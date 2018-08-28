import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener{

	private boolean[] keys;
	GameMechanics mech;
	CollisonTesting cTest;
	
	public KeyManager(GameMechanics mech,CollisonTesting testing)
	{
		this.mech = mech;
		cTest = testing;
		keys = new boolean[256];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		 int key = e.getKeyCode();

		    if (key == KeyEvent.VK_LEFT) {
		    	if(cTest.testMove("l", mech.getOb())){
					mech.setInput("l");
				}
		    }

		    if (key == KeyEvent.VK_RIGHT) {
		    	if(cTest.testMove("r", mech.getOb())){
					mech.setInput("r");
				}
		    }

		    if (key == KeyEvent.VK_UP) {
				if(cTest.testMove("u", mech.getOb())){
					mech.setInput("u");
				}
		    }

		    if (key == KeyEvent.VK_DOWN) {
		    	if(cTest.testMove("d", mech.getOb())){
					mech.setInput("d");
				}
		    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
