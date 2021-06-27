package musichub;

import musichub.controller.MusicHubController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
// Test commit
public class Main {

    public static void main (String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        MusicHubController theHub = new MusicHubController();
        theHub.mainController();
    }
}
