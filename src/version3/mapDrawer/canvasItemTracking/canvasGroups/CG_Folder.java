package version3.mapDrawer.canvasItemTracking.canvasGroups;

import version3.mapDrawer.canvasItemTracking.canvasItems.CanvasItem;
import version3.mapDrawer.canvasItemTracking.informationStorage.BoundingBox2D;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 4/7/2015.
 */
public class CG_Folder extends CanvasGroup {

    private List<CanvasGroup> canvasGroups;

    public CG_Folder() {
        canvasGroups = new ArrayList<>();
    }

    @Override
    public BoundingBox2D getBoundingBox() {

        // todo: get bounding box
        return null;
    }

    @Override
    public List<CanvasItem> getAllSubCanvasItems() {

        //TODO: get all canvas items
        return null;
    }

    @Override
    public List<CanvasGroup> getAllSubCanvasGroups() {
        List<CanvasGroup> list = new ArrayList<CanvasGroup>();

        for (int i = 0; i < canvasGroups.size(); i++) {

            CanvasGroup currentGroup = canvasGroups.get(i);

            list.add(currentGroup);
            list.addAll(currentGroup.getAllSubCanvasGroups());
        }

        return list;
    }

    @Override
    public List<CG_Folder> getAllSubFolders() {

        List<CG_Folder> list = new ArrayList<>();

        for (int i = 0; i < canvasGroups.size(); i++) {
            if (canvasGroups.get(i) instanceof CG_Folder){
                CG_Folder folder = (CG_Folder) canvasGroups.get(i);

                list.add(folder);
                list.addAll(folder.getAllSubFolders());
            }

        }

        return list;
    }


    @Override
    public List<CanvasGroup> getCanvasGroups() {
        //TODO:
        return canvasGroups;
    }

    @Override
    public List<CG_Folder> getFolders() {
        List<CG_Folder> list = new ArrayList<>();
        for (int i = 0; i < canvasGroups.size(); i++) {
            if (canvasGroups.get(i) instanceof CG_Folder) {
                list.add( (CG_Folder) canvasGroups.get(i) );
            }
        }
        return list;
    }

    @Override
    public List<CG_Layer> getLayers() {
        List<CG_Layer> list = new ArrayList<>();
        for (int i = 0; i < canvasGroups.size(); i++) {
            if (canvasGroups.get(i) instanceof CG_Layer) {
                list.add( (CG_Layer) canvasGroups.get(i) );
            }
        }
        return list;
    }

    public void addCanvasGroup(CG_Layer layer) {

    }
}
