package networking.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rafael on 3/12/2015.
 */
public class MiscUtils {
    public static List<Object> cloneList(List<Object> list) {
        List<Object> clone = new ArrayList<Object>(list.size());
        for(Object item: list) {
            //clone.add(item.clone());
        }
        return clone;
    }
}
