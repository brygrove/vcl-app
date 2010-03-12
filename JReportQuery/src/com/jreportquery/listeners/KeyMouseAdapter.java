package com.jreportquery.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class KeyMouseAdapter implements KeyMouseListener {

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract void keyReleased(KeyEvent arg0);
	
	@Override
	public abstract void mouseClicked(MouseEvent arg0);
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public abstract void mousePressed(MouseEvent arg0) ;
	

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
