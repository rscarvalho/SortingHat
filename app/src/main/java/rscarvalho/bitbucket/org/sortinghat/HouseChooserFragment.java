package rscarvalho.bitbucket.org.sortinghat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rscarvalho.bitbucket.org.sortinghat.data.HogwartsHouse;

public class HouseChooserFragment extends Fragment {
  private TextView houseNameTextView;
  private ImageView houseImageView;
  private HogwartsHouse selectedHouse = null;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_house_chooser, container, false);

    Button sortButton = (Button)view.findViewById(R.id.sort_button);
    houseNameTextView = (TextView)view.findViewById(R.id.house_name_label);
    houseImageView = (ImageView)view.findViewById(R.id.sorting_hat_img);

    sortButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        int value = Long.valueOf(Math.round(Math.random() * (HogwartsHouse.values().length - 1))).intValue();
        selectedHouse = HogwartsHouse.values()[value];
        updateScreenWithHouse(selectedHouse);
      }
    });

    return view;
  }

  private void updateScreenWithHouse(HogwartsHouse house) {
    houseNameTextView.setText(house.getNameId());
    houseImageView.setImageResource(house.getCrestId());
  }
}
