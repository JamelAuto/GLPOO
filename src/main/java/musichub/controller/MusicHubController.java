package musichub.controller;

import musichub.business.*;
import musichub.view.MusicHubView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.*;

public class MusicHubController {
    private static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private MusicHub theHub = new MusicHub ();
    private MusicHubView theHubView = new MusicHubView();

    public void mainController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        initializeProgram();
        menuController();
    }

    private void initializeProgram(){
        theHubView.displayHelp();
    }

    private void menuController() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        switch (theHubView.menuView()){
            case 't':
                //album titles, ordered by date
                String albumTitles = theHub.getAlbumsTitlesSortedByDate();
                theHubView.displayString(albumTitles);
                theHubView.printAvailableCommands();
                menuController();
                break;
            case 'g':
                //songs of an album, sorted by genre
                String albumsList = theHub.getAlbumsTitlesSortedByDate();
                theHubView.displayString(albumsList);
                try {
                    List<Song> albumSongs = theHub.getAlbumSongsSortedByGenre(theHubView.genericScanner());
                    theHubView.displayList(albumSongs);
                } catch (NoAlbumFoundException ex) {
                    System.out.println("No album found with the requested title " + ex.getMessage());
                    logr.log(Level.SEVERE, "Error :", ex);
                }
                menuController();
                break;
            case 'd':
                //songs of an album
                String albumsList2 = theHub.getAlbumsTitlesSortedByDate();
                theHubView.displayString(albumsList2);
                try {
                    List<AudioElement> albumSongs = theHub.getAlbumSongs(theHubView.genericScanner());
                    theHubView.displayList(albumSongs);
                } catch (NoAlbumFoundException ex) {
                    System.out.println("No album found with the requested title " + ex.getMessage());
                    logr.log(Level.SEVERE, "Error :", ex);
                }
                menuController();
                break;
            case 'u':
                //audiobooks ordered by author
                String audiobooksList = theHub.getAudiobooksTitlesSortedByAuthor();
                theHubView.displayString(audiobooksList);
                menuController();
                break;
            case 'c':
                // add a new song
                Song s = theHubView.addSongView();
                theHub.addElement(s);
                Iterator<AudioElement> it = theHub.elements();
                theHubView.addSongViewDisplayView(it);
                menuController();
                break;
            case 'a':
                // add a new album
                Album a = theHubView.addAlbumView();
                theHub.addAlbum(a);
                Iterator<Album> ita = theHub.albums();
                theHubView.addAlumViewDisplayView(ita);
                menuController();
                break;
            case '+':
                //add a song to an album:
                Iterator<AudioElement> itae = theHub.elements();
                String songTitle = theHubView.chooseSongToAddView(itae);
                Iterator<Album> ait = theHub.albums();
                String titleAlbum = theHubView.chooseAlbumToAddView(ait);
                try {
                    theHub.addElementToAlbum(songTitle, titleAlbum);
                } catch (NoAlbumFoundException ex){
                    System.out.println (ex.getMessage());
                    logr.log(Level.SEVERE, "Error :", ex);
                } catch (NoElementFoundException ex){
                    System.out.println (ex.getMessage());
                    logr.log(Level.SEVERE, "Error :", ex);
                }
                System.out.println("Song added to the album!");
                menuController();
                break;
            case 'l':
                // add a new audiobook
                AudioBook b = theHubView.addAudioBookView();
                theHub.addElement(b);
                Iterator<AudioElement> itl = theHub.elements();
                while (itl.hasNext()) System.out.println(itl.next().getTitle());
                menuController();
                break;
            case 'p':
                // Detailler le bug d'attente lors de la creation de playlist lié au scanner
                //create a new playlist from existing elements
                Iterator<PlayList> itpl = theHub.playlists();
                String playListTitle = theHubView.createNewPlaylistTitleView(itpl);
                PlayList pl = new PlayList(playListTitle);
                theHub.addPlaylist(pl);
                Iterator<AudioElement> itael = theHub.elements();
                theHubView.playlistAvailableElementsView(itael);
                while (theHubView.menuView() != 'n') 	{
                    System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
                    String elementTitle = theHubView.genericScanner();
                    try {
                        theHub.addElementToPlayList(elementTitle, playListTitle);
                        System.out.println("Playlist created!");
                    } catch (NoPlayListFoundException ex) {
                        System.out.println (ex.getMessage());
                        logr.log(Level.SEVERE, "Error :", ex);
                    } catch (NoElementFoundException ex) {
                        System.out.println (ex.getMessage());
                        logr.log(Level.SEVERE, "Error :", ex);
                    }

                    System.out.println("Type y to add a new one, n to end");
                    theHubView.menuView();
                }
                menuController();
                break;
            case '-':
                //delete a playlist
                Iterator<PlayList> itp = theHub.playlists();
                String plTitle = theHubView.deletePlaylistView(itp);
                try {
                    theHub.deletePlayList(plTitle);
                    System.out.println("Playlist deleted!");
                }	catch (NoPlayListFoundException ex) {
                    System.out.println (ex.getMessage());
                    logr.log(Level.SEVERE, "Error :", ex);
                }
                menuController();

                break;
            case 's':
                //save elements, albums, playlists
                theHub.saveElements();
                theHub.saveAlbums();
                theHub.savePlayLists();
                System.out.println("Elements, albums and playlists saved!");
                menuController();
                break;
            case 'r':
                // play a random element
                AudioElement randomElement = theHub.getRandomElement();
                theHubView.displayElement(randomElement);
                String content = randomElement.getContent();
                Player.playElement(content);
                menuController();
                break;
            case 'e':
                // play an element
                String content2 = theHubView.genericScanner();
                Player.playElement(content2);
                menuController();
                break;
            case 'q':
                logr.log(Level.INFO, "Quit program");
                //exit program
                System.exit(0);
            default:
                //default case
                theHubView.printAvailableCommands();
                menuController();
                break;
        }
    }
}
