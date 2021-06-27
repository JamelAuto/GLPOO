package musichub.controller;

import musichub.business.*;
import musichub.view.MusicHubView;

import java.util.Iterator;
import java.util.List;

public class MusicHubController {

    private MusicHub theHub = new MusicHub ();
    private MusicHubView theHubView = new MusicHubView();

    public void mainController(){
        initializeProgram();
        menuController();
    }

    private void initializeProgram(){
        theHubView.displayHelp();
    }

    private void menuController(){
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
                System.out.println("New element list: ");
                Iterator<AudioElement> it = theHub.elements();
                while (it.hasNext()) System.out.println(it.next().getTitle());
                System.out.println("Song created!");
                menuController();
                break;
            case 'a':
                // add a new album
                Album a = theHubView.addAlbumView();
                theHub.addAlbum(a);
                System.out.println("New list of albums: ");
                Iterator<Album> ita = theHub.albums();
                while (ita.hasNext()) System.out.println(ita.next().getTitle());
                System.out.println("Album created!");
                menuController();
                break;
            case '+':
                //add a song to an album:
                System.out.println("Add an existing song to an existing album");
                System.out.println("Type the name of the song you wish to add. Available songs: ");
                Iterator<AudioElement> itae = theHub.elements();
                while (itae.hasNext()) {
                    AudioElement ae = itae.next();
                    if ( ae instanceof Song) System.out.println(ae.getTitle());
                }
                String songTitle = theHubView.genericScanner();

                System.out.println("Type the name of the album you wish to enrich. Available albums: ");
                Iterator<Album> ait = theHub.albums();
                while (ait.hasNext()) {
                    Album al = ait.next();
                    System.out.println(al.getTitle());
                }
                String titleAlbum = theHubView.genericScanner();

                try {
                    theHub.addElementToAlbum(songTitle, titleAlbum);
                } catch (NoAlbumFoundException ex){
                    System.out.println (ex.getMessage());
                } catch (NoElementFoundException ex){
                    System.out.println (ex.getMessage());
                }
                System.out.println("Song added to the album!");
                menuController();
                break;
            case 'l':
                // add a new audiobook
                System.out.println("Enter a new audiobook: ");
                System.out.println("AudioBook title: ");
                String bTitle = theHubView.genericScanner();
                System.out.println("AudioBook category (youth, novel, theater, documentary, speech)");
                String bCategory = theHubView.genericScanner();
                System.out.println("AudioBook artist: ");
                String bArtist = theHubView.genericScanner();
                System.out.println ("AudioBook length in seconds: ");
                int bLength = Integer.parseInt(theHubView.genericScanner());
                System.out.println("AudioBook content: ");
                String bContent = theHubView.genericScanner();
                System.out.println("AudioBook language (french, english, italian, spanish, german)");
                String bLanguage = theHubView.genericScanner();
                AudioBook b = new AudioBook (bTitle, bArtist, bLength, bContent, bLanguage, bCategory);
                theHub.addElement(b);
                System.out.println("Audiobook created! New element list: ");
                Iterator<AudioElement> itl = theHub.elements();
                while (itl.hasNext()) System.out.println(itl.next().getTitle());
                menuController();
                break;
            case 'p':
                //create a new playlist from existing elements
                System.out.println("Add an existing song or audiobook to a new playlist");
                System.out.println("Existing playlists:");
                Iterator<PlayList> itpl = theHub.playlists();
                while (itpl.hasNext()) {
                    PlayList pl = itpl.next();
                    System.out.println(pl.getTitle());
                }
                System.out.println("Type the name of the playlist you wish to create:");
                String playListTitle = theHubView.genericScanner();
                PlayList pl = new PlayList(playListTitle);
                theHub.addPlaylist(pl);
                System.out.println("Available elements: ");

                Iterator<AudioElement> itael = theHub.elements();
                while (itael.hasNext()) {
                    AudioElement ae = itael.next();
                    System.out.println(ae.getTitle());
                }
                while (theHubView.menuView() != 'n') 	{
                    System.out.println("Type the name of the audio element you wish to add or 'n' to exit:");
                    String elementTitle = theHubView.genericScanner();
                    try {
                        theHub.addElementToPlayList(elementTitle, playListTitle);
                    } catch (NoPlayListFoundException ex) {
                        System.out.println (ex.getMessage());
                    } catch (NoElementFoundException ex) {
                        System.out.println (ex.getMessage());
                    }

                    System.out.println("Type y to add a new one, n to end");
                    theHubView.menuView();
                }
                System.out.println("Playlist created!");
                menuController();
                break;
            case '-':
                //delete a playlist
                System.out.println("Delete an existing playlist. Available playlists:");
                Iterator<PlayList> itp = theHub.playlists();
                while (itp.hasNext()) {
                    PlayList p = itp.next();
                    System.out.println(p.getTitle());
                }
                String plTitle = theHubView.genericScanner();
                try {
                    theHub.deletePlayList(plTitle);
                }	catch (NoPlayListFoundException ex) {
                    System.out.println (ex.getMessage());
                }
                System.out.println("Playlist deleted!");
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
                theHubView.displayElement(theHub.getRandomElement());
                menuController();
                break;
            case 'q':
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
