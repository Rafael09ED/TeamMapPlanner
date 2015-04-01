package version2.mapDrawer.graphicsObjects.layers;

import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.graphicsObjects.GraphicsObject;
import version2.mapDrawer.rendering.GraphicsObjectLayer;
import version2.mapDrawer.util.onlyOneJToggleDown;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Rafael09ED on 3/30/2015.
 */
public class LayersToolbar extends JToolBar {
    private ActionListener buttonListener;
    private final static String ADD_LAYER_STRING = "AddLayer", REMOVE_LAYER_STRING = "RemoveLayer";
    private DrawingCanvas drawingCanvas;
    private ActionListener layerSelectionListener;
    private List<LayerGUIElement> layerGUIElements;
    private onlyOneJToggleDown onlyOneJToggleDown;


    public LayersToolbar(DrawingCanvas drawingCanvas) {
        this.drawingCanvas = drawingCanvas;
        layerGUIElements = new ArrayList<>();

        setOrientation(SwingConstants.VERTICAL);
        createActionListeners();

        JButton addLayerButton = new JButton("Add Layer");
        addLayerButton.setActionCommand(ADD_LAYER_STRING);
        addLayerButton.addActionListener(buttonListener);
        addLayerButton.setFocusable(false);
        add(addLayerButton);


        JButton removeLayerButton = new JButton("Remove Layer");
        removeLayerButton.setActionCommand(REMOVE_LAYER_STRING);
        removeLayerButton.addActionListener(buttonListener);
        add(removeLayerButton);
        removeLayerButton.setFocusable(false);


        LayerGUIElement newLayer = new LayerGUIElement(drawingCanvas.graphicsObjectTracker().setActiveLayer(0));
        layerGUIElements.add(newLayer);
        add(newLayer);
        onlyOneJToggleDown = new onlyOneJToggleDown(newLayer);
        newLayer.setActionCommand(0 + "");
        newLayer.addActionListener(layerSelectionListener);
        newLayer.setVisible(true);
        newLayer.setFocusable(false);
    }

    private void addLayer(){
        GraphicsObjectLayer graphicsLayer = drawingCanvas.graphicsObjectTracker().createNewLayer();
        LayerGUIElement guiElement = new LayerGUIElement(graphicsLayer);
        layerGUIElements.add(guiElement);
        add(guiElement);
        guiElement.setActionCommand(graphicsLayer.getUniqueLayerID() + "");
        guiElement.addActionListener(layerSelectionListener);
        guiElement.setVisible(true);

        guiElement.setFocusable(false);
    }
    /*
     * Creates the Action Listeners that will be be used by the butons.
     *
     */
    private void createActionListeners() {
        buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String command = e.getActionCommand();
                switch (command) {
                    case ADD_LAYER_STRING:
                        addLayer();
                        break;

                    case REMOVE_LAYER_STRING:
                        if (layerGUIElements.size() <= 1){
                            break;
                        }
                        int uniqueIDtoRemove = Integer.parseInt(onlyOneJToggleDown.getCurrentSelected());
                        for (int i = 0; i < layerGUIElements.size(); i++) {

                            LayerGUIElement guiElement = layerGUIElements.get(i);

                            if (guiElement.getUniqueLayerID() == uniqueIDtoRemove){
                                onlyOneJToggleDown.setSelectedTool(layerGUIElements.get(0));
                                
                                remove(guiElement);
                                layerGUIElements.remove(i);
                                drawingCanvas.graphicsObjectTracker().removeGraphicsLayer(uniqueIDtoRemove);
                            }
                        }
                        break;
                    default:
                        break;
                }
                revalidate();
                repaint();
            }
        };
        layerSelectionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawingCanvas.graphicsObjectTracker().setActiveLayer(Integer.parseInt(e.getActionCommand()));
                onlyOneJToggleDown.setSelectedTool((JToggleButton) e.getSource());
            }
        };
    }
}
