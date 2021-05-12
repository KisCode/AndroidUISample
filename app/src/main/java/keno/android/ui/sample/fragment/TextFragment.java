package keno.android.ui.sample.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import keno.android.ui.sample.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TextFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static TextFragment newInstance(int index) {
        TextFragment fragment = new TextFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_text, container, false);
        final TextView textView = root.findViewById(R.id.tv_title);
        textView.setText("Page:" + getArguments().getInt(ARG_SECTION_NUMBER));
        return root;
    }
}