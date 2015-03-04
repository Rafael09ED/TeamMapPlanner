package application.logic;

import networking.interfaces.NetworkSendable;
import networking.util.OutBoxLoop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class Map extends Canvas implements KeyListener, MouseListener {
    private boolean mouseDown = false;
    private ArrayList<Integer> activeKeys;
    private Point previousPoint;
    private ArrayList<Line> lines;
    protected ArrayList<Line> lineOutBox;
    private ArrayList<Line> lineInBox;
    private OutBoxLoop outBoxLoop;
    private NetworkSendable outBoxSender;


    public Map() {

        addKeyListener(this);
        addMouseListener(this);
        activeKeys = new ArrayList<Integer>();
        lineOutBox = new ArrayList<Line>();
        lineInBox = new ArrayList<Line>();

        outBoxLoop = new OutBoxLoop();
        lines = new ArrayList<Line>();

        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                updateDrawing();
                paint();
                //System.out.println("lol");
            }
        };
        new Timer(6, action).start();

        this.setVisible(true);
        this.requestFocusInWindow();


    }
    public void setOutBoxSender(NetworkSendable outBoxSender) {
        this.outBoxSender = outBoxSender;
    }


    public void startOutputBox(){
        outBoxLoop.setNetworkSender(outBoxSender);
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(outBoxLoop, 2 * 1 * 1000, 2* 1 *1000);
    }
    @Override
    public void paint(Graphics g) {
        g.clearRect(0,0,getWidth(),getHeight());
        g.drawString("Rafael09ED", 20, 20);

        for (Line line : lines) {
            line.draw(g);
        }
       // g.dispose();
    }
    public void paint() {

        Graphics g = getGraphics();
       // g.clearRect(0, 0, getWidth(), getHeight());

        for (Line line : lines) {
            line.draw(g);
        }

    }
    private void checkInbox(){
        if (lineInBox.size() > 0){
            for (Line line : lineInBox) {
                lines.add(line);
            }
        }
    }

    public ArrayList<Line> getLineInBox() {
        return lineInBox;
    }

    private void updateDrawing() {


        //System.out.println(lineOutBox.size());
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

                    if (outBoxLoop!=null) {
                        outBoxLoop.setLineOutBox(lines.get(lines.size() - 1).Clone());

                    }
                } else {
                    Line tempLine = new Line(previousPoint, currentPoint);
                    lines.add(tempLine);
                        if (outBoxLoop != null) {
                            outBoxLoop.setLineOutBox(lines.get(lines.size() - 1).Clone());
                        }
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
        //paint(getGraphics());
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
        //System.out.println("MouseOut");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;

    }

}
