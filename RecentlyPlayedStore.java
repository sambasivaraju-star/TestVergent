import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class RecentlyPlayedStore {
    private int capacity;
    private int size;
    private Map<String, LinkedList<String>> store;
    private LinkedList<String> recentlyPlayed;

    public RecentlyPlayedStore(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.store = new HashMap<>();
        this.recentlyPlayed = new LinkedList<>();
    }

    public void playSong(String user, String song) {
        LinkedList<String> songs = store.getOrDefault(user, new LinkedList<>());

        // Remove the song if it already exists for the user
        songs.remove(song);

        // Add the song at the beginning of the list
        songs.addFirst(song);

        // Update the store with the latest songs for the user
        store.put(user, songs);

        // Update the recently played list
        recentlyPlayed.remove(song);
        recentlyPlayed.addFirst(song);

        // Check if the store has exceeded its capacity
        if (size >= capacity) {
            String leastRecentlyPlayed = recentlyPlayed.removeLast();
            for (LinkedList<String> userSongs : store.values()) {
                userSongs.remove(leastRecentlyPlayed);
            }
        } else {
            size++;
        }
    }

    public LinkedList<String> getRecentlyPlayed(String user) {
        return store.getOrDefault(user, new LinkedList<>());
    }
}

