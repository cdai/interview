package others.ood;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 */
public class Jukebox {

    public static void main(String[] args) {
        Jukebox jukebox = new Jukebox();

        Artist jayz = new Artist("Jay-Z");
        Album album1 = new Album("Hello", 2017, jayz);
        album1.add(new Song("song1", album1, jayz));
        album1.add(new Song("song2", album1, jayz));
        album1.add(new Song("song3", album1, jayz));
        jukebox.addAlbumToLibrary(album1);

        Album album2 = new Album("World", 2014, jayz);
        album2.add(new Song("song4", album2, jayz));
        album2.add(new Song("song5", album2, jayz));

        for (Song song : jukebox.getSongs()) {
            jukebox.addToPlaylist(song);
        }

        Assertions.assertEquals("song1", jukebox.play().getName());
        Assertions.assertEquals("song2", jukebox.play().getName());
        Assertions.assertEquals("Current song: song2 by artist Jay-Z from album Hello in 2017", jukebox.display());
        Assertions.assertEquals("song3", jukebox.play().getName());
        Assertions.assertEquals("song4", jukebox.play().getName());
        Assertions.assertEquals("Current song: song3 by artist Jay-Z from album World in 2014", jukebox.display());
    }

    private Playlist playlist = new Playlist();

    private Library library = new Library();

    public void addAlbumToLibrary(Album album) {
        library.addAlbum(album);
    }

    // 1.Check out what song we have
    public List<Song> getSongs(/*Query condition: type, artist, year...*/) {
        return library.getAllSongs();
    }

    // 2.Add some into the playlist (suppose only one public playlist for all users)
    public void addToPlaylist(Song song) {
        playlist.addSong(song);
    }

    public void deleteFromPlaylist(Song song) {
        playlist.removeSong(song);
    }

    public Song play() {
        return playlist.play();
    }

    public String display() {
        Song song = playlist.getCurrentSong();
        return "Current song: " + song.getName() + " by artist " +
                song.getArtist().getName() + " from album " +
                song.getAlbum().getName() + " in " + song.getAlbum().getYear();
    }
}

class Playlist {
    private Queue<Song> songs = new LinkedList<>();
    private Song current;

    public void addSong(Song song) {
        songs.add(song);
    }

    public void removeSong(Song song) { // Note O(N)
        songs.remove(song);
    }

    public Song play() {
        current = songs.poll();
        return current;
    }

    public Song getCurrentSong() {
        return current;
    }

}

class Library {
    /* Different index for song query */
    private Set<Song> allSongs = new HashSet<>();
    private Map<Artist, Song> artistSong = new HashMap<>();
    private Map<Integer, Song> yearSong = new HashMap<>();

    public void addAlbum(Album album) {
        for (Song song : album.getSongs()) {
            allSongs.add(song);
            artistSong.put(album.getArtist(), song);
            yearSong.put(album.getYear(), song);
        }
    }

    public List<Song> getAllSongs() {
        return new ArrayList<>(allSongs);
    }
}

class Album {
    private String name;
    private int year;
    private Artist artist;
    private List<Song> songs = new ArrayList<>();

    public Album(String name, int year, Artist artist) {
        this.name = name;
        this.year = year;
        this.artist = artist;
    }

    public void add(Song song) {
        songs.add(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public Artist getArtist() {
        return artist;
    }
}

class Artist {
    private String name;

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Song {
    private String name;
    private Album album;
    private Artist artist;

    public Song(String name, Album album, Artist artist) {
        this.name = name;
        this.album = album;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public Album getAlbum() {
        return album;
    }

    public Artist getArtist() {
        return artist;
    }
}