package musichub;

import musichub.controller.MusicHubController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.logging.*;

// Test commit
public class Main {
    private static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main (String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        Main.setupLogger();
        MusicHubController theHub = new MusicHubController();
        theHub.mainController();
    }
    private static void setupLogger(){
        LogManager.getLogManager().reset();
        logr.setLevel(Level.ALL);
        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        logr.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("myLogger.log",true);
            fh.setLevel(Level.FINE);
            logr.addHandler(fh);
        }
        catch (IOException e){
            logr.log(Level.SEVERE,"File logger not working.",e);
        }
    }
}
