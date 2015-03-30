package version2.mapDrawer.tools.toolBars;

import version2.mapDrawer.tools.PictureTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Hashtable;

/**
 * Created by ADMIN on 3/29/2015.
 */
public class PictureToolBar extends ToolToolBar {
    private PictureTool pictureTool;
    private ActionListener buttonListener;
    private final static String OPEN_FILE_BROWSER = "FileBrowser";
    private BufferedImage currentImage;
    private ChangeListener changeListener;


    public PictureToolBar(PictureTool pictureTool) {
        this.pictureTool = pictureTool;
        createActionListeners();

        setOrientation(SwingConstants.HORIZONTAL);

        JButton jButtonSizeUp = new JButton("Import");
        add(jButtonSizeUp);
        jButtonSizeUp.addActionListener(buttonListener);
        jButtonSizeUp.setActionCommand(OPEN_FILE_BROWSER);

        JSlider jSliderScale = new JSlider();
        add(jSliderScale);
        jSliderScale.addChangeListener(changeListener);
        jSliderScale.setOrientation(JSlider.HORIZONTAL);

        Hashtable labelTable = new Hashtable();
        labelTable.put(new Integer(0), new JLabel("0%"));
        labelTable.put(new Integer(100), new JLabel("100%"));
        labelTable.put(new Integer(200), new JLabel("200%"));
        jSliderScale.setLabelTable(labelTable);

        jSliderScale.setMajorTickSpacing(10);
        jSliderScale.setMinorTickSpacing(1);
        jSliderScale.setPaintTicks(true);
        jSliderScale.setPaintLabels(true);
        jSliderScale.setMinimum(1);
        jSliderScale.setMaximum(200);

        jSliderScale.setValue(100);

    }

    private void createActionListeners() {

        buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() instanceof JButton) {
                    String command = e.getActionCommand();
                    switch (command) {
                        case OPEN_FILE_BROWSER:
                            filePicker();

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
                Object eSource = e.getSource();
                if (eSource instanceof JSlider) {
                    JSlider scaleSlider = (JSlider) eSource;
                    pictureTool.setScale(scaleSlider.getValue()/100.0);
                }
            }
        };
    }

    public BufferedImage getSelectedImage() {
        return currentImage;
    }
    private void filePicker(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Image File Types (.jpg, .png, .gif)", "jpg", "gif", "png");
        fc.setFileFilter(filter);
        int returnVal = fc.showOpenDialog(getParent());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {

                File file = fc.getSelectedFile();
                BufferedImage in = ImageIO.read(file);
                BufferedImage preImage = new BufferedImage(in.getWidth(), in.getHeight(), BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = in.createGraphics();
                g.drawImage(preImage, 0, 0, null);
                g.dispose();
                currentImage = in;

                JLabel label = new JLabel(new ImageIcon(currentImage));
                JFrame f = new JFrame("Preview");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.getContentPane().add(label);
                f.pack();
                f.setLocation(200, 200);
                f.setVisible(true);

                pictureTool.setImageLoaded(true);
            } catch (IOException e) {

                e.printStackTrace();

                pictureTool.setImageLoaded(false);
                currentImage = null;
            }

        } else {
            // else do nothing

        }
    }
}
