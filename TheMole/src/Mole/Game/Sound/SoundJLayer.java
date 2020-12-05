package Mole.Game.Sound;

import java.io.FileInputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackListener;

public class SoundJLayer extends PlaybackListener implements Runnable
{
    private String filePath;
    private AdvancedPlayer player;
    private Thread playerThread;   
    
    public SoundJLayer(String filePath) {//������ ��θ� filePath�� �Է��մϴ�.
        this.filePath = filePath; //filePath�� ���� mp3�� ��� ������ �ʱ�ȭ.
    }

    public void play() {
        try {
            String urlAsString = this.filePath;
            this.player = new AdvancedPlayer(new FileInputStream(urlAsString));

            this.player.setPlayBackListener(this);

            this.playerThread = new Thread(this, "AudioPlayerThread");

            this.playerThread.start();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            this.player.play();
        }
        catch (javazoom.jl.decoder.JavaLayerException ex) {
            ex.printStackTrace();
        }
    }
    
    public void pause() throws javazoom.jl.decoder.JavaLayerException, InterruptedException {
	    this.player.stop();
    }
}