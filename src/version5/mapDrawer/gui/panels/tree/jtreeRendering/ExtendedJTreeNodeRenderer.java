package version5.mapDrawer.gui.panels.tree.jtreeRendering;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.util.Hashtable;

/**
 * File created by Rafael on 5/5/2015.
 * Code from:
 * http://www.java2s.com/Code/Java/Swing-Components/IconNodeTreeExample.htm
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
