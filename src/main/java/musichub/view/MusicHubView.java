package musichub.view;

import musichub.business.*;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicHubView {
    private final static Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public char menuView(){
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        while (choice.charAt(0)!= 'q') 	{
            switch (choice.charAt(0)){
                case 'g':
                    System.out.println("Songs of an album sorted by genre will be displayed; enter the album name, available albums are:");
                    break;
                case 'd':
                    System.out.println("Songs of an album will be displayed; enter the album name, available albums are:");
                    break;
                case 'e':
                    System.out.println("Enter element content full name");
                    break;
                case '+':
                    System.out.println("Add an existing song to an existing album");
                    System.out.println("Type the name of the song you wish to add. Available songs: ");
                    break;
                case 'p':
                    System.out.println("Add an existing song or audiobook to a new playlist");
                    System.out.println("Existing playlists:");
                    break;
                case '-':
                    System.out.println("Delete an existing playlist. Available playlists:");
                    break;
            }
            return choice.charAt(0);
        }
        return 'q';
    }

    public  String genericScanner(){
        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        return string;
    }

    public String deletePlaylistView(Iterator<PlayList> itp){
        while (itp.hasNext()) {
            PlayList p = itp.next();
            System.out.println(p.getTitle());
        }
        String plTitle = genericScanner();
        return plTitle;
    }

    public AudioBook addAudioBookView(){
        System.out.println("Enter a new audiobook: ");
        System.out.println("AudioBook title: ");
        String bTitle = genericScanner();
        System.out.println("AudioBook category (youth, novel, theater, documentary, speech)");
        String bCategory = genericScanner();
        System.out.println("AudioBook artist: ");
        String bArtist = genericScanner();
        System.out.println ("AudioBook length in seconds: ");
        int bLength = Integer.parseInt(genericScanner());
        System.out.println("AudioBook content: ");
        String bContent = genericScanner();
        System.out.println("AudioBook language (french, english, italian, spanish, german)");
        String bLanguage = genericScanner();
        AudioBook b = new AudioBook (bTitle, bArtist, bLength, bContent, bLanguage, bCategory);
        System.out.println("Audiobook created! New element list: ");
        return b;
    }

    public void playlistAvailableElementsView(Iterator<AudioElement> itael){
        System.out.println("Available elements: ");
        while (itael.hasNext()) {
            AudioElement ae = itael.next();
            System.out.println(ae.getTitle());
        }
    }

    public String createNewPlaylistTitleView(Iterator<PlayList> itpl){
        while (itpl.hasNext()) {
            PlayList pl = itpl.next();
            System.out.println(pl.getTitle());
        }
        System.out.println("Type the name of the playlist you wish to create:");
        String playListTitle = genericScanner();
        return  playListTitle;
    }

    public String chooseAlbumToAddView(Iterator<Album> ait) {
        while (ait.hasNext()) {
            Album al = ait.next();
            System.out.println(al.getTitle());
        }
        String titleAlbum = genericScanner();
        return titleAlbum;
    }

    public String chooseSongToAddView(Iterator<AudioElement> itae){
        while (itae.hasNext()) {
            AudioElement ae = itae.next();
            if ( ae instanceof Song) System.out.println(ae.getTitle());
        }
        String songTitle = genericScanner();
        System.out.println("Type the name of the album you wish to enrich. Available albums: ");
        return songTitle;
    }

    public void addSongViewDisplayView(Iterator<AudioElement> it){
        System.out.println("New element list: ");
        while (it.hasNext()) System.out.println(it.next().getTitle());
        System.out.println("Song created!");
    }

    public Song addSongView(){
        int length=0;
        System.out.println("Enter a new song: ");
        System.out.println("Song title: ");
        String title = genericScanner();
        System.out.println("Song genre (jazz, classic, hiphop, rock, pop, rap):");
        String genre = genericScanner();
        System.out.println("Song artist: ");
        String artist = genericScanner();
        System.out.println ("Song length in seconds: ");
        try {
            length = Integer.parseInt(genericScanner());
        }
        catch (Exception ex){
            logr.log(Level.WARNING, "Error :", ex);
            System.exit(0);
        }
        System.out.println("Song content: ");
        String content = genericScanner();
        Song s = new Song (title, artist, length, content, genre);
        return s;
    }

    public void addAlumViewDisplayView(Iterator<Album> ita){
        System.out.println("New list of albums: ");
        while (ita.hasNext()) System.out.println(ita.next().getTitle());
        System.out.println("Album created!");
    }

    public Album addAlbumView(){
         int aLength = 0;
          System.out.println("Enter a new album: ");
        System.out.println("Album title: ");
        String aTitle = genericScanner();
        System.out.println("Album artist: ");
        String aArtist = genericScanner();
        System.out.println ("Album length in seconds: ");
        try {
            aLength = Integer.parseInt(genericScanner());
        }
        catch (Exception ex){
            logr.log(Level.WARNING, "Error :", ex);
            System.exit(0);
        }
        System.out.println("Album date as YYYY-DD-MM: ");
        String aDate = genericScanner();
        Album a =  Album.getInstance(aTitle, aArtist, aLength, aDate);
        return a;
    }

    public void displayString(String consoleDisplay){
        System.out.println(consoleDisplay);
    }

    public void displayElement(AudioElement consoleDisplay){
        System.out.println(consoleDisplay);
    }

    public void displayList(List consoleDisplay){
        System.out.println(consoleDisplay);
    }

    public void displayHelp(){
        System.out.println("Type h for available commands");
    }

    public void printAvailableCommands() {
        System.out.println("e: play an element");
        System.out.println("t: display the album titles, ordered by date");
        System.out.println("g: display songs of an album, ordered by genre");
        System.out.println("d: display songs of an album");
        System.out.println("u: display audiobooks ordered by author");
        System.out.println("c: add a new song");
        System.out.println("a: add a new album");
        System.out.println("+: add a song to an album");
        System.out.println("l: add a new audiobook");
        System.out.println("p: create a new playlist from existing songs and audio books");
        System.out.println("-: delete an existing playlist");
        System.out.println("s: save elements, albums, playlists");
        System.out.println("r: play a random element");
        System.out.println("q: quit program");
    }
}
