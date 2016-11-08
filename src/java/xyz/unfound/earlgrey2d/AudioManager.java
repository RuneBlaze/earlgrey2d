package xyz.unfound.earlgrey2d;


import clojure.lang.Keyword;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

import java.util.HashMap;
import java.util.Stack;

public class AudioManager {
    Stack<Music> musics;
    Stack<Sound> sounds;

    public void playBgm(FileHandle fh) {
        Music music = Gdx.audio.newMusic(fh);
        musics.add(music);
        music.play();
    }

    public void playMusic(FileHandle fh) {
        Music music = Gdx.audio.newMusic(fh);
        musics.add(music);
        music.setLooping(true);
        music.play();
    }

    public void playSound(FileHandle sd) {
        
    }

    public void changeBgm(FileHandle fh) {
        Music m = musics.pop();
        m.stop();
        m.dispose();
        playBgm(fh);
    }
}