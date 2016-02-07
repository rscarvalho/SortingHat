package rscarvalho.bitbucket.org.sortinghat;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

public class HouseChooserActivity extends AppCompatActivity {
  public static final String HOUSES_EXTRA = "houses";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_house_chooser);

    Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    ArrayList<Integer> sortedHouses = getIntent().getIntegerArrayListExtra(HOUSES_EXTRA);

    Bundle fragmentBundle = new Bundle(1);
    fragmentBundle.putSerializable(HOUSES_EXTRA, sortedHouses);
    HouseChooserFragment fragment = new HouseChooserFragment();
    fragment.setArguments(fragmentBundle);

    getFragmentManager()
        .beginTransaction()
        .add(R.id.house_chooser_fragment_container, fragment)
        .commit();
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
