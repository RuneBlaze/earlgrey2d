package xyz.unfound.earlgrey2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class ErrorScreen implements Screen {

    Exception e;
    public ErrorScreen(Exception e) {
        this.e = e;
    }

    SpriteBatch sb;
    BitmapFont f;

    String mes;

    int timer;
    @Override
    public void show() {
        sb = new SpriteBatch();
        f = new BitmapFont(Gdx.files.internal("terminal.fnt"));

        StringBuilder sb = new StringBuilder();
        sb.append("-- Exception in Screen! --").append("\n");
        sb.append(e.toString()).append("\n");
        for (StackTraceElement ele : e.getStackTrace()) {
            sb.append("    ").append(ele.toString()).append("\n");
        }
        mes = sb.toString();
        timer = 0;
    }

    @Override
    public void render(float v) {
        timer += 1;
        timer %= 180;
        Gdx.gl.glClearColor(0.1f,0.1f,0.1f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sb.begin();
        Color c = new Color(0.8f,0.8f,0.8f,0.7f + (timer / 60) / 10f);
        GlyphLayout gl = new GlyphLayout(f,mes,c,Gdx.graphics.getWidth() - 20, Align.topLeft, false);
        f.draw(sb, gl, 20, Gdx.graphics.getHeight() - 20);
        sb.end();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
