package ir.mta.openlinker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtUrl;
    Button btnAdd;
    ListView listView;

    List<SearchEngine> engines;
    SharedPrefManager manager;
    ArrayAdapter<String> adapter;

    int editIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtUrl = findViewById(R.id.edtUrl);
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.listView);

        manager = new SharedPrefManager(this);
        engines = manager.getList();

        if (engines.isEmpty()) {
            engines.add(new SearchEngine("MTA", "https://www.mohammadtahaabdinasab.ir"));
            engines.add(new SearchEngine("Google", "https://www.google.com"));
            engines.add(new SearchEngine("Bing", "https://www.bing.com"));
            engines.add(new SearchEngine("Yahoo", "https://www.yahoo.com"));
            manager.saveList(engines);
        }

        updateList();

        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String url = edtUrl.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(url)) return;

            if (!url.startsWith("http")) {
                url = "https://" + url;
            }

            if (editIndex == -1) {
                engines.add(new SearchEngine(name, url));
            } else {
                engines.get(editIndex).setName(name);
                engines.get(editIndex).setUrl(url);
                editIndex = -1;
                btnAdd.setText("افزودن");
            }

            manager.saveList(engines);
            updateList();
            edtName.setText("");
            edtUrl.setText("");
        });

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            String url = engines.get(position).getUrl();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });

        listView.setOnItemLongClickListener((adapterView, view, position, id) -> {
            SearchEngine se = engines.get(position);

            new androidx.appcompat.app.AlertDialog.Builder(MainActivity.this)
                    .setTitle("انتخاب عمل")
                    .setMessage("می‌خوای این مورد رو حذف کنی یا ویرایش؟")
                    .setPositiveButton("ویرایش", (dialog, which) -> {
                        edtName.setText(se.getName());
                        edtUrl.setText(se.getUrl());
                        editIndex = position;
                        btnAdd.setText("ویرایش");
                    })
                    .setNegativeButton("حذف", (dialog, which) -> {
                        engines.remove(position);
                        manager.saveList(engines);
                        updateList();
                    })
                    .setNeutralButton("انصراف", null)
                    .show();
            return true;
        });
    }

    private void updateList() {
        List<String> names = new ArrayList<>();
        for (SearchEngine e : engines) names.add(e.getName());
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
        listView.setAdapter(adapter);
    }
}