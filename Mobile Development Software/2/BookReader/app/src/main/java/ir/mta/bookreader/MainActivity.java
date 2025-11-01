package ir.mta.bookreader;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements FragmentOne.OnBookSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, FragmentOne.newInstance(this))
                        .commit();
            } else {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_one_container, FragmentOne.newInstance(this));
                ft.replace(R.id.fragment_two_container, new FragmentTwo());
                ft.commit();
            }
        }
    }

    @Override
    public void onBookSelected(String bookName, String bookUrl) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            FragmentTwo fragmentTwo = (FragmentTwo) getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_two_container);
            if (fragmentTwo != null) {
                fragmentTwo.loadPdf(bookName, bookUrl);
                return;
            }
        }
        FragmentTwo fragmentTwo = FragmentTwo.newInstance(bookName, bookUrl);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragmentTwo)
                .addToBackStack(null)
                .commit();
    }
}
