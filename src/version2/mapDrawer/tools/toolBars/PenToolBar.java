package version2.mapDrawer.tools.toolBars;

import version2.mapDrawer.tools.Pen;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

/**
 * Created by Rafael on 3/25/2015.
 */
public class PenToolBar extends ToolToolBar{
    private Pen penTool;
    private int Size = 1;
    private Color color = Color.BLACK;
    private final static String
        SET_COLOR = "SetColor";
    private ActionListener buttonListener;

    private ChangeListener changeListener;

    public PenToolBar(Pen penTool) {
        this.penTool = penTool;
        createActionListeners();
        setOrientation(SwingConstants.HORIZONTAL);

        JButton jButtonSizeUp = new JButton("Color Picker");
        add(jButtonSizeUp);
        jButtonSizeUp.addActionListener(buttonListener);
        jButtonSizeUp.setActionCommand(SET_COLOR);

        JSlider jSliderStroke = new JSlider();
        add(jSliderStroke);
        jSliderStroke.addChangeListener(changeListener);
        jSliderStroke.setOrientation(JSlider.HORIZONTAL);


        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(1), new JLabel("1"));
        labelTable.put(new Integer(5), new JLabel("5"));
        labelTable.put(new Integer(10), new JLabel("10"));
        jSliderStroke.setLabelTable(labelTable);

        jSliderStroke.setMajorTickSpacing(1);
        jSliderStroke.setMinorTickSpacing(1);
        jSliderStroke.setPaintTicks(true);
        jSliderStroke.setPaintLabels(true);
        jSliderStroke.setMinimum(1);
        jSliderStroke.setMaximum(10);

        jSliderStroke.setValue(1);




    }

   private void createActionListeners() {

       buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    String command = e.getActionCommand();
                    switch (command){
                        case SET_COLOR:
                            penTool.setCurrentColor(Color.BLACK);
                            break;

                        default:
                            break;
                    }

                }
            }
        };

       changeListener = new ChangeListener() {
           @Override
           public void stateChanged(ChangeEvent e) {
               if (e.getSource() instanceof JSlider) {
                   penTool.setCurrentStroke(((JSlider) e.getSource()).getValue());
                   System.out.println(((double)((JSlider) e.getSource()).getValue()));
               }
           }
       };


    }
}
