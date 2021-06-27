package musichub.business;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Player {

    public static void playElement(String content) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        String filePath = "files/elements/" + content;
        File file = new File(filePath);
        Scanner scanner = new Scanner(System.in);
        AudioInputStream audioStream;

        audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        String response = "";
        System.out.println("p : play, s : stop, r : reset, q : quit");
        while (!response.equals("q")) {
            response = scanner.next();

            switch (response) {
                case ("p"):
                    clip.start();
                    break;
                case ("s"):
                    clip.stop();
                    break;
                case ("r"):
                    clip.setMicrosecondPosition(0);
                    break;
                case ("q"):
                    clip.close();
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
