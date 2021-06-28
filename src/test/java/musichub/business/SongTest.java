package musichub.business;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

class SongTest {
    MusicHub theHub = new MusicHub();
    Album a = new Album("TEST1","TEST2",42,"TEST3");
    MusicHub musicHubPlaylistApresSupprimer = new MusicHub();
    PlayList p = new PlayList("TEST9000");
    PlayList m = new PlayList ("");
    @Test
    void addElementTest() {
            assertTrue(theHub.addElement(null));
        }
        @Test
        void addAlbumTest(){
            assertTrue(theHub.addAlbum(null));
            assertFalse(theHub.addAlbum(a));
        }
        @Test
    void addPlaylistTest(){
            assertTrue(theHub.addPlaylist(p));
            assertFalse(theHub.addPlaylist(m));
        }
        @Test
    void deleteAlbumTest() throws NoPlayListFoundException {
        theHub.addAlbum(a);
            musicHubPlaylistApresSupprimer.addAlbum(a);
            musicHubPlaylistApresSupprimer.deletePlayList("TEST1");

        }
            }
