package rscarvalho.bitbucket.org.sortinghat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import rscarvalho.bitbucket.org.sortinghat.data.HogwartsHouse;

public class MainActivity extends AppCompatActivity {
  private TextView houseNameTextView;
  private ImageView houseImageView;
  private HogwartsHouse selectedHouse = null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
  }
}
