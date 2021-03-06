package prototyping.gui;

import version5.mapDrawer.interfacing.GroupDataInterface;
import version5.mapDrawer.interfacing.taskManagment.TaskManager;
import version5.mapDrawer.interfacing.taskManagment.tasks.Task_AddCanvasItem;
import version5.mapDrawer.interfacing.taskManagment.tasks.Task_NewCanvasLayer;
import version5.mapDrawer.itemManagement.infoTypes.Point2D;
import version5.mapDrawer.itemManagement.itemTracker.canvasGroupWrappers.CanvasGroupLayerWrapper;
import version5.mapDrawer.itemManagement.itemTracker.canvasItems.Item_Line;
import version5.mapDrawer.rendering.optimization.RenderData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

/**
 * Created by Rafael on 4/28/2015.
 */
public class guiStart extends JFrame {
    private final GroupDataInterface groupDataInterface;
    private final TaskManager taskManager;
    private BufferStrategy strategy;
    private Canvas canva;

    public guiStart(GroupDataInterface groupDataInterface, TaskManager taskManager) {
        this.groupDataInterface = groupDataInterface;
        this.taskManager = taskManager;

        setSize(1000, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(100, 100);
        canva = new Canvas();
        add(canva);
        setVisible(true);

        canva.createBufferStrategy(2);
        strategy = canva.getBufferStrategy();

        ComponentListener componentListener = new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                renderCanvas(strategy.getDrawGraphics());
                strategy.show();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                componentResized(e);
            }

            @Override
            public void componentShown(ComponentEvent e) {
                componentResized(e);
            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        };

        canva.addComponentListener(componentListener);

        taskManager.doNewTask(new Task_NewCanvasLayer(groupDataInterface.getRootWrapper()));


        canva.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("clicked");
                taskManager.doNewTask(
                        new Task_AddCanvasItem((CanvasGroupLayerWrapper)
                                groupDataInterface.getChildrenOfFolderWrapped(groupDataInterface.getRootWrapper()).get(0),
                                new Item_Line(randomPointOnCanvas(), randomPointOnCanvas()))
                );

                if (strategy != null) {
                    renderCanvas(strategy.getDrawGraphics());
                    strategy.show();

                }
            }

            Point2D randomPointOnCanvas() {
                return new Point2D(Math.random() * getWidth() - 1, Math.random() * getHeight() - 1);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    private void renderCanvas(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());
        RenderData renderData = groupDataInterface.renderCanvas();
        g.drawImage(renderData.getRender(),0, 0, null);
        g.dispose();
    }

    @Override
    public void validate() {
        super.validate();
        if (strategy != null) {
            renderCanvas(strategy.getDrawGraphics());
            strategy.show();
        }
    }
    private class CanvasPanel extends Canvas{
        @Override
        public void paint(Graphics g) {
            renderCanvas(strategy.getDrawGraphics());
            strategy.show();
        }
    }
}
