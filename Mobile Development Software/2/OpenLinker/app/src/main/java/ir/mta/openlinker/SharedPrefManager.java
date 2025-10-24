package ir.mta.openlinker;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPrefManager {
    private static final String PREF_NAME = "search_prefs";
    private static final String KEY_LIST = "search_list";
    private SharedPreferences prefs;
    private Gson gson = new Gson();

    public SharedPrefManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public List<SearchEngine> getList() {
        String json = prefs.getString(KEY_LIST, null);
        Type type = new TypeToken<ArrayList<SearchEngine>>() {}.getType();
        List<SearchEngine> list = gson.fromJson(json, type);
        return list == null ? new ArrayList<>() : list;
    }

    public void saveList(List<SearchEngine> list) {
        prefs.edit().putString(KEY_LIST, gson.toJson(list)).apply();
    }
}