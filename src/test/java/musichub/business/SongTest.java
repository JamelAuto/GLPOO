package musichub.business;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class SongTest {

    @Test
    void testAddSong() {
        MusicHub theHub = new MusicHub();
        String title = "TEST";
        String genre = "JAZZ";
        String artist = "JAMEL";
        int length = 42;
        String content = "C://TEST/TEST";
        Song s = new Song(title, artist, length, content, genre);
        theHub.addElement(s);
        Iterator it = theHub.elements();
        while(it.hasNext()) {
            System.out.println(((AudioElement)it.next()).getTitle());
        }

            }
}