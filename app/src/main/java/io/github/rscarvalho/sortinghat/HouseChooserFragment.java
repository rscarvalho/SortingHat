package io.github.rscarvalho.sortinghat;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Stack;

import io.github.rscarvalho.sortinghat.data.HogwartsHouse;
import io.github.rscarvalho.sortinghat.media.HouseClipPlayer;

public class HouseChooserFragment extends Fragment {
  private final View.OnClickListener sortButtonClickListener = new SortButtonClickListener();
  private final MediaPlayerListener mediaPlayerListener = new MediaPlayerListener();
  private HouseClipPlayer houseClipPlayer;

  private ImageView houseImageView;
  private HogwartsHouse selectedHouse = null;
  private Button sortButton;
  private View rootView;
  private final Stack<HogwartsHouse> sortedHouses = new Stack<>();

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_house_chooser, container, false);
    sortButton = (Button) rootView.findViewById(R.id.sort_button);
    houseImageView = (ImageView) rootView.findViewById(R.id.crest_image);
    houseClipPlayer = new HouseClipPlayer(rootView.getContext(), mediaPlayerListener);
    sortButton.setOnClickListener(sortButtonClickListener);

    ArrayList<Integer> houseIndices = getArguments().getIntegerArrayList(HouseChooserActivity.HOUSES_EXTRA);
    for (int i : houseIndices) {
      sortedHouses.push(HogwartsHouse.values()[i]);
    }

    return rootView;
  }

  private void updateScreenWithHouse(HogwartsHouse house) {
    if (house != null) {
      houseImageView.setImageResource(house.getCrestId());
      sortButton.setEnabled(false);
      houseClipPlayer = new HouseClipPlayer(rootView.getContext(), mediaPlayerListener);
      houseClipPlayer.execute(house);
    } else {
      houseImageView.setImageResource(R.drawable.crest_hogwarts);
      sortButton.setText(R.string.start_over_button_label);
    }
  }

  private class MediaPlayerListener implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    @Override
    public void onPrepared(MediaPlayer mp) {
      mp.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
      sortButton.post(new Runnable() {
        @Override
        public void run() {
          sortButton.setEnabled(true);
        }
      });
    }
  }

  private class SortButtonClickListener implements View.OnClickListener {
    @Override
    public void onClick(View v) {
      if (!sortedHouses.isEmpty()) {
        selectedHouse = sortedHouses.pop();
        updateScreenWithHouse(selectedHouse);
      } else if (selectedHouse == null) {
        NavUtils.navigateUpFromSameTask(HouseChooserFragment.this.getActivity());
      } else {
        selectedHouse = null;
        updateScreenWithHouse(null);
      }
    }
  }
}
