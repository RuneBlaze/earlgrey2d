package xyz.unfound.earlgrey2d;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.ScreenUtils;

public class GdxUtils {
    public static Pixmap screenshot() {
        int bw = Gdx.graphics.getBackBufferWidth();
        int bh = Gdx.graphics.getBackBufferHeight();
        byte[] pixels = ScreenUtils.getFrameBufferPixels(0, 0, bw, bh, true);
        Pixmap pixmap = new Pixmap(bw, bh, Pixmap.Format.RGBA8888);
        BufferUtils.copy(
                pixels,
                0,
                pixmap.getPixels(),
                pixels.length
        );
        return pixmap;
    }

    public static void saveScreenshot(FileHandle fh) {
        Pixmap ss = screenshot();
        PixmapIO.writePNG(fh, ss);
        ss.dispose();
    }
}
