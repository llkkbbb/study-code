package com.example.blogcode.effectivejava.item20;

import java.applet.AudioClip;

/**
 * packageName    : com.example.blogcode.effectivejava.item20
 * fileName       : SingerSongwriter
 * author         : tkdwk567@naver.com
 * date           : 2022/07/26
 */
public interface SingerSongwriter extends Singer, Songwriter {
    AudioClip strum();
    void actSensitive();
}
