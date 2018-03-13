package sections.ui.objects;

import org.telegram.messenger.ApplicationLoader;

import java.util.ArrayList;

public class Favourite {
    private static final String TAG = "Favorite";
    private static Favourite Instance = null;

    private static ArrayList<Long> list;

    public static Favourite getInstance() {
        Favourite localInstance = Instance;
        if (localInstance == null) {
            Instance = localInstance = new Favourite();
        }
        return localInstance;
    }

    public Favourite(){
        list = ApplicationLoader.databaseHandler.getList();
    }

    public ArrayList<Long> getList(){
        //Log.e(TAG,"getList");
        return list;
    }

    public static void addFavorite(Long id){
        //Log.e(TAG,"addFavorite " + id);
        list.add(id);
        ApplicationLoader.databaseHandler.addFavorite(id);
    }

    public static void deleteFavorite(Long id){
        //Log.e(TAG,"deleteFavorite " + id);
        list.remove(id);
        ApplicationLoader.databaseHandler.deleteFavorite(id);
    }

    public static boolean isFavorite(Long id){
        //Log.e(TAG,"isFavorite " + id);
        return list.contains(id);
    }

    public int getCount(){
        //Log.e(TAG,"getCount");
        return list.size();
    }

}

