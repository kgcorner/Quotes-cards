package com.kgcorner.vachan.viewers.listview.fragments.quotes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.kgcorner.sdk.models.Quote;
import com.kgcorner.vachan.BaseApplication;
import com.kgcorner.vachan.R;
import com.kgcorner.vachan.io.Store;
import com.kgcorner.vachan.viewers.listview.viewholder.quotes.QuoteCardAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.arjsna.swipecardlib.SwipeCardView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuotesListFragment.OnFragmentInteractionListener} interface
 * create an instance of this fragment.
 */
public class QuotesListFragment extends Fragment implements QuotesView {

    private static final String TAG = "QuotesListFragment";

    @Inject
    QuotesPresenter presenter;

    @BindView(R.id.quotesContainer)
    SwipeCardView quoteContainer;

    private Unbinder unbinder;

    private OnFragmentInteractionListener mListener;

    private List<Quote> quotes = new ArrayList<>();
    private ArrayAdapter<Quote> quotesAdapter = null;
    private Store store;

    public QuotesListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) getActivity().getApplication())
                .getAppComponent().inject(this);
        presenter.setView(this);
        if(store == null)
            store = ((BaseApplication) getActivity().getApplication()).getStore();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quotes_list, container, false);
        //presenter.getQuotes(store.getLatestFetchedPage());
        unbinder = ButterKnife.bind(this, view);
        quotesAdapter = new QuoteCardAdapter(getContext(), R.layout.quote_card, quotes);
        quoteContainer.setAdapter(quotesAdapter);

        int minCardExpected = getContext().getResources()
                .getInteger(R.integer.min_card_count_in_stack);
        quoteContainer.setMinStackInAdapter(minCardExpected);
        setFlingListener();
        return view;
    }

    private void setFlingListener() {
        quoteContainer.setFlingListener(new SwipeCardView.OnCardFlingListener() {
            @Override
            public void onCardExitLeft(Object dataObject) {
            }

            @Override
            public void onCardExitRight(Object dataObject) {
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                Log.d(TAG, "onAdapterAboutToEmpty: Adapter is about to empty");
                presenter.getQuotes(store.getLatestFetchedPage());
                String toastText = getContext().getString(R.string.get_more_quote);
                Toast.makeText(getContext(), toastText, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {

            }

            @Override
            public void onCardExitTop(Object dataObject) {

            }

            @Override
            public void onCardExitBottom(Object dataObject) {

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void loadQuotes(List<Quote> quotes) {
        if(quotes != null || quotes.size()>0) {
            this.quotes.addAll(quotes);
            this.quotesAdapter.notifyDataSetChanged();
            int page = store.getLatestFetchedPage();
            page++;
            store.setLatestFetchPage(page);
        }
    }

    @Override
    public void showError(Throwable e) {
        Log.e(TAG, "showError: ", e);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
