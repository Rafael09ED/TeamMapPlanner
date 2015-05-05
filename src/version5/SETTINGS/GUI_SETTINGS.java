package version5.SETTINGS;

import javax.swing.plaf.metal.MetalIconFactory;
import java.util.Hashtable;

/**
 * Created by Rafael on 5/5/2015.
 */
public class GUI_SETTINGS {
    public static final Hashtable icons;

    static {
            icons = new Hashtable();
            icons.put("item1", MetalIconFactory.getTreeFloppyDriveIcon());
            icons.put("item2", MetalIconFactory.getTreeHardDriveIcon());
            icons.put("item3", MetalIconFactory.getTreeComputerIcon());
    }
}
