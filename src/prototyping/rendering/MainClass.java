package prototyping.rendering;

import prototyping.rendering.canvasItem.Line;
import prototyping.rendering.canvasItem.ObjectTracker;
import prototyping.rendering.graphicsRendering.Renderer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Rafael on 4/1/2015.
 */
public class MainClass extends Canvas{
    private ObjectTracker objectTracker;
    private Renderer graphicsRenderer;
    private static Canvas canvas;
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame panel = new JFrame();
                panel.setSize(400, 400);

                canvas = new MainClass();
                panel.add(canvas);
                canvas.setVisible(true);
                panel.setVisible(true);
            }
        });
    }

    public MainClass() {
        objectTracker = new ObjectTracker();
        graphicsRenderer = new Renderer(objectTracker);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                 //   revalidate();
                    if (isDisplayable()) {
                        getGraphics().drawString("lol", 10, 10);
                        render();
                        update();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();

    }

    private void render(){
        graphicsRenderer.renderAll((Graphics2D) getGraphics());
    }
    private void update(){
       objectTracker.addRenderable(new Line(new Point(rndNum(),rndNum()), new Point(rndNum(), rndNum())));
    }
    private int rndNum(){
        return (int) (Math.random()* 399) + 1;
    }
}
