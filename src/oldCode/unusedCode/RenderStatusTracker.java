package oldCode.unusedCode;

/**
 * Created by ADMIN on 4/11/2015.
 */
public class RenderStatusTracker {

    private boolean addingChange = false, rerenderRequired = false;
    public RenderStatusTracker() {

    }

    public void addingChange(){
        addingChange = true;
    }

    public void rerenderChange(){
        rerenderRequired = true;
    }
    public void resetChangeStatus(){
        addingChange = false;
        rerenderRequired = false;
    }
    public boolean hasChanged(){
        return (addingChange || rerenderRequired);
    }
    public boolean isAddingChange(){
        // if rerenderRequired then return false regardless of addingChange
        // else if addingChange is only true then return true;
        return !((!addingChange) && rerenderRequired);
    }
    public boolean isRerenderRequired(){
        return rerenderRequired;
    }
}
