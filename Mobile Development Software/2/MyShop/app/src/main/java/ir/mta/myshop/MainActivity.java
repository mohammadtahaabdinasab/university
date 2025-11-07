package ir.mta.myshop;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerItems;
    private SwitchMaterial switchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerItems = findViewById(R.id.recyclerItems);
        switchLayout = findViewById(R.id.switchLayout);
        recyclerItems.setAdapter(new ProductAdapter(createProducts()));
        applyLayoutManager(switchLayout.isChecked());
        switchLayout.setOnCheckedChangeListener((buttonView, isChecked) -> applyLayoutManager(isChecked));
    }

    private void applyLayoutManager(boolean gridSelected) {
        int orientation = getResources().getConfiguration().orientation;
        if (gridSelected) {
            int spanCount = orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
            recyclerItems.setLayoutManager(new GridLayoutManager(this, spanCount));
        } else {
            recyclerItems.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private List<ProductItem> createProducts() {
        List<ProductItem> items = new ArrayList<>();
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "کتاب درسی", "مجموعه تمرین‌های ترم پاییز", true));
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "دفتر کلاس", "جلد سخت، ۱۰۰ برگ", false));
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "کالای دیجیتال", "گوشی هوشمند اقتصادی", true));
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "لپ‌تاپ دانشجویی", "پردازنده Core i5 نسل ۱۲", true));
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "کوله‌پشتی", "طراحی ارگونومیک مخصوص دانشگاه", false));
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "ماگ قهوه", "چاپ لوگوی دانشگاه", true));
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "هدفون", "بی‌سیم با نویزگیر", false));
        items.add(new ProductItem(R.drawable.ic_launcher_foreground, "چراغ مطالعه", "قابلیت تنظیم شدت نور", true));
        return items;
    }
}