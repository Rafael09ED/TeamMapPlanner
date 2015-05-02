package version5.mapDrawer.gui.tools;

import javax.swing.*;

public class OnlyOneJToggleDown {
    public OnlyOneJToggleDown(JToggleButton buttonIn) {
        buttonSelected = buttonIn;
        buttonIn.setSelected(true);
    }

    private JToggleButton buttonSelected;

    public OnlyOneJToggleDown() {

    }

    public void setSelectedTool(JToggleButton buttonIn) {
        if (buttonIn == buttonSelected) {
            return;
        }
        if (buttonSelected != null) {
            buttonSelected.setSelected(false);
        }
        buttonSelected = buttonIn;
        buttonSelected.setSelected(true);
    }

    public String getCurrentSelected() {
        return buttonSelected.getActionCommand();
    }

}