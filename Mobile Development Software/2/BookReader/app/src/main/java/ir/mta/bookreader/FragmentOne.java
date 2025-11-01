package ir.mta.bookreader;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FragmentOne extends Fragment {

    private OnBookSelectedListener mListener;

    public interface OnBookSelectedListener {
        void onBookSelected(String bookName, String bookUrl);
    }

    public FragmentOne() {}

    public static FragmentOne newInstance(OnBookSelectedListener listener) {
        FragmentOne fragment = new FragmentOne();
        fragment.mListener = listener;
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (mListener == null && context instanceof OnBookSelectedListener) {
            mListener = (OnBookSelectedListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ListView listView = view.findViewById(R.id.bookListView);

        final String[] bookNames = {
                "Check and Create User and Group",
                "Internet Marketing",
                "Share Data and Files with MMC",
                "Upgrade to Windows Server 2016"
        };

        final String[] bookUrls = {
                "https://uploadkon.ir/uploads/664e01_25check-and-create-user-and-group.pdf",
                "https://uploadkon.ir/uploads/52d901_25internet-marketing.pdf",
                "https://uploadkon.ir/uploads/76d101_25share-data-and-files-with-mmc.pdf",
                "https://uploadkon.ir/uploads/a41601_25upgrade-to-windows-server-2016.pdf"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, bookNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, v, position, id) -> {
            String selectedName = bookNames[position];
            String selectedUrl = bookUrls[position];

            boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

            if (isLandscape) {
                FragmentTwo fragmentTwo = (FragmentTwo) getActivity()
                        .getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_two_container);

                if (fragmentTwo != null) {
                    fragmentTwo.loadPdf(selectedName, selectedUrl);
                    return;
                }
            }

            if (mListener != null) {
                mListener.onBookSelected(selectedName, selectedUrl);
            }
        });

        return view;
    }
}
