package version1.application.logic;

import version1.networking.interfaces.NetworkSendable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;


public class Map extends Canvas implements KeyListener, MouseListener {
    private boolean mouseDown = false;
    private ArrayList<Integer> activeKeys;
    private Point previousPoint;
    protected List<Line> lines;
    protected ArrayList<Line> lineOutBox;
    private ArrayList<Line> lineInBox;
    private NetworkSendable outBoxSender;
    private static final int RenderEveryNms = 10;
    private Object thisObject = this;

    public Map() {

        addKeyListener(this);
        addMouseListener(this);
        activeKeys = new ArrayList<Integer>();
        lineOutBox = new ArrayList<Line>();
        lineInBox = new ArrayList<Line>();

        lines = Collections.synchronizedList(new ArrayList<Line>());

        Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Running ID:" + System.identityHashCode(this));
                System.out.println("Running ID:" + System.identityHashCode(thisObject));
                updateDrawing();
                paint();
            }
        }, RenderEveryNms, RenderEveryNms);

        this.setVisible(true);
        this.requestFocusInWindow();


    }
    public void setOutBoxSender(NetworkSendable outBoxSender) {
        this.outBoxSender = outBoxSender;
    }

    public void sendObjects(){
        ArrayList tmpList = new ArrayList<Line>(lines);
        System.out.println("When Trying to Send, Lines is Size :" + lines.size());
        outBoxSender.ObjectsToSend(tmpList);
    }

    public void startOutputBox(){
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendObjects();
            }
        }, 2 * 1 * 1000, 2 * 1 * 1000);
    }
    @Override
    public void paint(Graphics g) {

        if (g == null)
            return;

        g.clearRect(0,0,getWidth(),getHeight());
        g.drawString("Rafael09ED", 20, 20);
        for (Line line : lines) {
            line.draw(g);
        }
        System.out.println("When Trying to paint w/ g, Lines is Size :" + lines.size());
       // g.dispose();
    }

    public void paint() {

        Graphics g = getGraphics();
       // g.clearRect(0, 0, getWidth(), getHeight());
        System.out.println("When Trying to paint, Lines is Size :" + lines.size());
        System.out.println(System.identityHashCode(thisObject));
        for (Line line : lines) {
            line.draw(g);
        }

    }
	public List<Line> getListToSend() {
        java.util.List<Line> tempList =  Collections.synchronizedList(new ArrayList<Line>(lines));
        return tempList;
	}
    public String getARandomeString(){
        return  "LOLCODE";
    }
    private void checkInbox(){
        if (lineInBox.size() > 0){
            for (Line line : lineInBox) {
                lines.add(line);
            }
        }
    }
    
    public ArrayList<Line> getLineInBox() {
   
    	//tem.out.println( "suzise :" + lineInBox.size()); //  this was the wrong one
        return (ArrayList<Line>) lineInBox.clone();
    }

    private void updateDrawing() {


        //System.out.println(lineOutBox.toString());
        if (mouseDown) {

            PointerInfo a = MouseInfo.getPointerInfo();
            Point currentPoint = new Point(a.getLocation().x - this.getLocationOnScreen().x, a.getLocation().y - this.getLocationOnScreen().y);

            if (previousPoint != null) {
                if ((previousPoint.equals(currentPoint))) {
                    //getGraphics().drawLine(previousPoint.x, previousPoint.y, a.getLocation().x - this.getLocationOnScreen().x, a.getLocation().y - this.getLocationOnScreen().y);
                } else if (lines.size() > 2 && lines.get(lines.size() - 1).equalsSlope(currentPoint)) {
                    //getGraphics().drawLine(previousPoint.x, previousPoint.y, currentPoint.x, currentPoint.y);
                    lines.add(new Line(lines.get(lines.size() - 1).getStartPoint(), currentPoint));
                    lines.remove(lines.size() - 2);
                } else {
                    Line tempLine = new Line(previousPoint, currentPoint);
                    lines.add(tempLine);
                        //tempLine.draw(getGraphics());
                }

            }
            previousPoint = currentPoint;
        } else {
            previousPoint = null;
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

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseDown = false;

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
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;

    }

}
