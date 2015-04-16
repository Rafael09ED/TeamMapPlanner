package version4.SETTINGS;

import java.awt.*;

/**
 * Created by Rafael on 4/14/2015.
 */
public class RENDER_SETTINGS {
    public static boolean renderAfterFrameCompletion = false;

    // Frames per second if renderAfterFrameCompletion = false
    public static double UPDATES_PER_SECOND = 60;

    // Delay time in between frames if renderAfterFrameCompletion = true
    // Must be greater than zero
    public static int delayBetweenFrames_ms = 1;

    // Initial delay time in seconds.
    // Must be greater than zero.
    public static int initialDelayTime_ms = 100;

    public static void SetRenderAA(Graphics2D g){
        g.setRenderingHint
                (RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint
                (RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint
                (RenderingHints.KEY_ALPHA_INTERPOLATION,
                        RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint
                (RenderingHints.KEY_STROKE_CONTROL,
                        RenderingHints.VALUE_STROKE_NORMALIZE);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);
    }
}
