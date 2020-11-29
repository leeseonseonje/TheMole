package Dump;

import java.applet.AudioClip;

import javax.swing.JApplet;

public class SoundPlayer {

	public static final SoundPlayer sound = new SoundPlayer("test.mp3");
	
	private AudioClip inputSound; // 파일명에 그냥 파일명만 넣을것 // 지가알아서 찾아줌

	private SoundPlayer(String SoundFileURL) {
			inputSound = JApplet.newAudioClip(getClass().getResource(SoundFileURL));
	}

	public void play() {
			inputSound.play();	
	}

	public void stopPlayer() {
		inputSound.stop();
	}
}