package version5.mapDrawer.gui.panels;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.Hashtable;

/**
 * Created by Rafael on 5/5/2015.
 * Code Copied from the internet
 */
public class ExtendedJTreeNodeRenderer extends DefaultTreeCellRenderer{
    public Component getTreeCellRendererComponent(JTree tree, Object value,
                                                  boolean sel, boolean expanded, boolean leaf, int row,
                                                  boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf,
                row, hasFocus);

        Icon icon = ((IconNode) value).getIcon();

        if (icon == null) {
            Hashtable icons = (Hashtable) tree.getClientProperty("JTree.icons");
            String name = ((IconNode) value).getIconName();
            if ((icons != null) && (name != null)) {
                icon = (Icon) icons.get(name);
                if (icon != null) {
                    setIcon(icon);
                }
            }
        } else {
            setIcon(icon);
        }

        return this;
    }
}

class IconNode extends DefaultMutableTreeNode {

    protected Icon icon;

    protected String iconName;

    public IconNode() {
        this(null);
    }

    public IconNode(Object userObject) {
        this(userObject, true, null);
    }

    public IconNode(Object userObject, boolean allowsChildren, Icon icon) {
        super(userObject, allowsChildren);
        this.icon = icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getIconName() {
        if (iconName != null) {
            return iconName;
        } else {
            String str = userObject.toString();
            int index = str.lastIndexOf(".");
            if (index != -1) {
                return str.substring(++index);
            } else {
                return null;
            }
        }
    }

    public void setIconName(String name) {
        iconName = name;
    }

}

class TextIcons extends MetalIconFactory.TreeLeafIcon {

    protected String label;

    private static Hashtable labels;

    protected TextIcons() {
    }

    public void paintIcon(Component c, Graphics g, int x, int y) {
        super.paintIcon(c, g, x, y);
        if (label != null) {
            FontMetrics fm = g.getFontMetrics();

            int offsetX = (getIconWidth() - fm.stringWidth(label)) / 2;
            int offsetY = (getIconHeight() - fm.getHeight()) / 2 - 2;

            g.drawString(label, x + offsetX, y + offsetY + fm.getHeight());
        }
    }

    public static Icon getIcon(String str) {
        if (labels == null) {
            labels = new Hashtable();
            setDefaultSet();
        }
        TextIcons icon = new TextIcons();
        icon.label = (String) labels.get(str);
        return icon;
    }

    public static void setLabelSet(String ext, String label) {
        if (labels == null) {
            labels = new Hashtable();
            setDefaultSet();
        }
        labels.put(ext, label);
    }

    private static void setDefaultSet() {
        labels.put("", "NULL");


        // and so on
    /*
     * labels.put("txt" ,"TXT"); labels.put("TXT" ,"TXT"); labels.put("cc"
     * ,"C++"); labels.put("C" ,"C++"); labels.put("cpp" ,"C++");
     * labels.put("exe" ,"BIN"); labels.put("class" ,"BIN");
     * labels.put("gif" ,"GIF"); labels.put("GIF" ,"GIF");
     *
     * labels.put("", "");
     */
    }
}