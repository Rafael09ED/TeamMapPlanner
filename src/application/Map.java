package application;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.Timer;

public class Map extends Canvas implements KeyListener, MouseListener {
	private boolean mouseDown = false;
	private ArrayList<Integer> activeKeys;
	private Point point;
	public Map() {
		addKeyListener(this);
		addMouseListener(this);
		activeKeys = new ArrayList<Integer>();

		ActionListener action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				updateDrawing();
				//System.out.println("lol");
			}
		};
		new Timer(1, action).start();
		this.setVisible(true);
		this.requestFocusInWindow();
	}
	
	@Override
	public void paint(Graphics brush){
		
	}
	
	private void updateDrawing() {
		if (mouseDown) {
			PointerInfo a = MouseInfo.getPointerInfo();
			if (point != null) {
				getGraphics().drawLine(point.x, point.y, a.getLocation().x-this.getLocationOnScreen().x, a.getLocation().y-this.getLocationOnScreen().y);	
			}
			point = new Point(a.getLocation().x-this.getLocationOnScreen().x, a.getLocation().y-this.getLocationOnScreen().y);
		} else {
			point = null;
		}
		for (Integer integer : activeKeys) {
			switch (integer) {
			case KeyEvent.VK_UP:
				//System.out.println("Up!");
				break;

			default:
				break;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (activeKeys.indexOf(e.getKeyCode()) == -1) {
			activeKeys.add(e.getKeyCode());
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
			activeKeys.remove((Object) e.getKeyCode());

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseDown = false;

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
		System.out.println("MouseOut");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;

	}

}
