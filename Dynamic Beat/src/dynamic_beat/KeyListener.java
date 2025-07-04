package dynamic_beat;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (DynamicBeat.game == null) { //if there's no game going on 
			return; //no event; function ends
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { //if user presses the "s" key
			DynamicBeat.game.pressS();
		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.pressD();
		}
		else if (e.getKeyCode() == KeyEvent.VK_F) { 
			DynamicBeat.game.pressF();
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
			DynamicBeat.game.pressSpace();
		}
		else if (e.getKeyCode() == KeyEvent.VK_J) { 
			DynamicBeat.game.pressJ();
		}
		else if (e.getKeyCode() == KeyEvent.VK_K) { 
			DynamicBeat.game.pressK();
		}
		else if (e.getKeyCode() == KeyEvent.VK_L) { 
			DynamicBeat.game.pressL();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (DynamicBeat.game == null) { //if there's no game going on 
			return; //no event
		}
		if (e.getKeyCode() == KeyEvent.VK_S) { //if user presses the "s" key
			DynamicBeat.game.releaseS();
		}
		else if (e.getKeyCode() == KeyEvent.VK_D) {
			DynamicBeat.game.releaseD();
		}
		else if (e.getKeyCode() == KeyEvent.VK_F) { 
			DynamicBeat.game.releaseF();
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) { 
			DynamicBeat.game.releaseSpace();
		}
		else if (e.getKeyCode() == KeyEvent.VK_J) { 
			DynamicBeat.game.releaseJ();
		}
		else if (e.getKeyCode() == KeyEvent.VK_K) { 
			DynamicBeat.game.releaseK();
		}
		else if (e.getKeyCode() == KeyEvent.VK_L) { 
			DynamicBeat.game.releaseL();
		}		
	}
}
