package oldCode.version3.mapDrawer.settings;

import java.awt.*;

/**
 * Created by Rafael on 4/3/2015.
 */
public class RENDER_SETTINGS {
    public class G2_RENDER_SETTINGS{

    }
    // Any Value <= to 0 will be a loop that will start after the previous frame finished.
    public static double pointUpdatesPerSecond = 60;

    // initial delay time in seconds. anything less than 1 is guaranteed to result in an error
    public static int initDelayTime_ms = 100;

    public static void SetRenderAA(Graphics2D g1){
        g1.setRenderingHint
                (RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g1.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }

}
