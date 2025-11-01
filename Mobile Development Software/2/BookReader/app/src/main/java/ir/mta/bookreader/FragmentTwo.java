package ir.mta.bookreader;

import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

public class FragmentTwo extends Fragment {

    private static final String ARG_BOOK_NAME = "book_name";
    private static final String ARG_BOOK_URL = "book_url";

    private String bookName;
    private String bookUrl;
    private WebView webView;
    private TextView titleView;

    public FragmentTwo() {}

    public static FragmentTwo newInstance(String name, String url) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString(ARG_BOOK_NAME, name);
        args.putString(ARG_BOOK_URL, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bookName = getArguments().getString(ARG_BOOK_NAME);
            bookUrl = getArguments().getString(ARG_BOOK_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        titleView = view.findViewById(R.id.bookTitle);
        webView = view.findViewById(R.id.pdfWebView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient());

        if (bookName != null) {
            titleView.setText(bookName);
        }

        if (bookUrl != null) {
            loadPdf(bookName, bookUrl);
        }

        return view;
    }

    public void loadPdf(String name, String url) {
        if (titleView != null) {
            titleView.setText((name == null ? "Loading..." : name));
        }
        if (webView == null) return;
        String encoded = Uri.encode(url);
        String viewer = "https://docs.google.com/gview?embedded=true&url=" + encoded;
        webView.loadUrl(url);
    }

    @Override
    public void onDestroyView() {
        if (webView != null) {
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView = null;
        }
        super.onDestroyView();
    }
}
