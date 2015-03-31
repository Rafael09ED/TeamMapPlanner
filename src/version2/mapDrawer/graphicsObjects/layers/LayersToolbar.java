package version2.mapDrawer.graphicsObjects.layers;

import version2.mapDrawer.DrawingCanvas;
import version2.mapDrawer.util.onlyOneJToggleDown;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 3/30/2015.
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
        newLayer.setActionCommand(layerGUIElements.size() + "");
        newLayer.addActionListener(layerSelectionListener);
        newLayer.setVisible(true);

    }
    private void addLayer(){
        LayerGUIElement newLayer = new LayerGUIElement(drawingCanvas.graphicsObjectTracker().createNewLayer());
        layerGUIElements.add(newLayer);
        add(newLayer);
        newLayer.setActionCommand(layerGUIElements.size() + "");
        newLayer.addActionListener(layerSelectionListener);
        newLayer.setVisible(true);
    }

    private void createActionListeners() {
        buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String command = e.getActionCommand();
                switch (command) {
                    case ADD_LAYER_STRING:
                        addLayer();
                        break;

                    case REMOVE_LAYER_STRING:

                        break;
                    default:
                        break;
                }
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
