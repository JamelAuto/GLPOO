package musichub.view;

import musichub.business.*;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class MusicHubView {

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
            }
            return choice.charAt(0);
        }
        return 'q';
    }

    public String genericScanner(){
        Scanner scan = new Scanner(System.in);
        String string = scan.nextLine();
        return string;
    }

    public Song addSongView(){
        System.out.println("Enter a new song: ");
        System.out.println("Song title: ");
        String title = genericScanner();
        System.out.println("Song genre (jazz, classic, hiphop, rock, pop, rap):");
        String genre = genericScanner();
        System.out.println("Song artist: ");
        String artist = genericScanner();
        System.out.println ("Song length in seconds: ");
        int length = Integer.parseInt(genericScanner());
        System.out.println("Song content: ");
        String content = genericScanner();
        Song s = new Song (title, artist, length, content, genre);
        return s;
    }

    public Album addAlbumView(){
        System.out.println("Enter a new album: ");
        System.out.println("Album title: ");
        String aTitle = genericScanner();
        System.out.println("Album artist: ");
        String aArtist = genericScanner();
        System.out.println ("Album length in seconds: ");
        int aLength = Integer.parseInt(genericScanner());
        System.out.println("Album date as YYYY-DD-MM: ");
        String aDate = genericScanner();
        Album a = new Album(aTitle, aArtist, aLength, aDate);
        return a;
    }

    public void displayString(String consoleDisplay){
        System.out.println(consoleDisplay);
    }

    public void displayList(List consoleDisplay){
        System.out.println(consoleDisplay);
    }
    public void displayHelp(){
        System.out.println("Type h for available commands");
    }
    public void printAvailableCommands() {
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
        System.out.println("q: quit program");
    }

}
