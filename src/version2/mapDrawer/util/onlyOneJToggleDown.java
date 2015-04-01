package version2.mapDrawer.util;

import javax.swing.JToggleButton;

public class onlyOneJToggleDown {
    public onlyOneJToggleDown(JToggleButton buttonIn) {
        buttonSelected = buttonIn;
        buttonIn.setSelected(true);
    }

    private JToggleButton buttonSelected;

    public void setSelectedTool(JToggleButton buttonIn) {
        buttonSelected.setSelected(false);
        buttonSelected = buttonIn;
        buttonSelected.setSelected(true);
    }
    public String getCurrentSelected(){
        return buttonSelected.getActionCommand();
    }

}