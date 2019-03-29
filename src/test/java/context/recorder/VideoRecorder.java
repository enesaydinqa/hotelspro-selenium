package context.recorder;

import org.apache.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.FormatKeys.*;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;

import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;

public class VideoRecorder
{
    private static Logger logger = Logger.getLogger(VideoRecorder.class);

    private static ScreenRecorder screenRecorder;

    public static void startRecording(String fileName) throws Exception
    {

        File file = new File(System.getProperty("user.dir") + "/src/test/resources/test-scenario-video");

        if (!file.exists())
        {
            logger.info("Creating Directory : " + file.getAbsolutePath());
            try
            {
                file.mkdir();
            }
            catch (SecurityException se)
            {
                se.printStackTrace();
            }
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Rectangle captureSize = new Rectangle(0, 0, width, height);

        GraphicsConfiguration gc = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        screenRecorder = new Screenshot(gc, captureSize,
                new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, FormatKeys.MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, 15 * 60),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "yellow",
                        FrameRateKey, Rational.valueOf(30)),
                null, file, fileName);

        screenRecorder.start();

    }

    public static void stopRecording() throws Exception
    {
        screenRecorder.stop();
    }
}
