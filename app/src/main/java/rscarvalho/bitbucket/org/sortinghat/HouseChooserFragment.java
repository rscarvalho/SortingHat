package rscarvalho.bitbucket.org.sortinghat;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rscarvalho.bitbucket.org.sortinghat.data.HogwartsHouse;
import rscarvalho.bitbucket.org.sortinghat.media.HouseClipPlayer;

public class HouseChooserFragment extends Fragment {
  private final View.OnClickListener sortButtonClickListener = new SortButtonClickListener();
  private final MediaPlayerListener mediaPlayerListener = new MediaPlayerListener();
  private HouseClipPlayer houseClipPlayer;

  private TextView houseNameTextView;
  private ImageView houseImageView;
  private HogwartsHouse selectedHouse = null;
  private Button sortButton;
  private View rootView;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    rootView = inflater.inflate(R.layout.fragment_house_chooser, container, false);
    sortButton = (Button) rootView.findViewById(R.id.sort_button);
    houseNameTextView = (TextView) rootView.findViewById(R.id.house_name_label);
    houseImageView = (ImageView) rootView.findViewById(R.id.crest_image);
    houseClipPlayer = new HouseClipPlayer(rootView.getContext(), mediaPlayerListener);

    sortButton.setOnClickListener(sortButtonClickListener);

    return rootView;
  }

  private void updateScreenWithHouse(HogwartsHouse house) {
    houseNameTextView.setText(house.getNameId());
    houseImageView.setImageResource(house.getCrestId());
    sortButton.setEnabled(false);
    houseClipPlayer = new HouseClipPlayer(rootView.getContext(), mediaPlayerListener);
    houseClipPlayer.execute(house);
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
      int value = Long.valueOf(Math.round(Math.random() * (HogwartsHouse.values().length - 1))).intValue();
      selectedHouse = HogwartsHouse.values()[value];
      updateScreenWithHouse(selectedHouse);
    }
  }
}
