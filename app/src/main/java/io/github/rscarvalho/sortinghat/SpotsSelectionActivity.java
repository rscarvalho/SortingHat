package io.github.rscarvalho.sortinghat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.github.rscarvalho.sortinghat.data.HogwartsHouse;

public class SpotsSelectionActivity extends AppCompatActivity {
  private int numberOfSpots = 0;
  private FloatingActionButton fab;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_spots_selection);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    final EditText spotsEditText = (EditText) findViewById(R.id.spotsEditText);
    spotsEditText.setOnKeyListener(new View.OnKeyListener() {
      @Override
      public boolean onKey(View v, int keyCode, KeyEvent event) {
        updateSpots(spotsEditText.getText().toString());
        return false;
      }
    });

    fab = (FloatingActionButton)findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent i = new Intent(SpotsSelectionActivity.this, HouseChooserActivity.class);
        i.putIntegerArrayListExtra(HouseChooserActivity.HOUSES_EXTRA, getSortingHatActions());
        startActivity(i);
      }
    });
  }

  private void updateSpots(String text) {
    if (text == null || text.isEmpty()) {
      numberOfSpots = 0;
    } else {
      numberOfSpots = Integer.parseInt(text);
    }

    fab.setVisibility(numberOfSpots > 0 ? View.VISIBLE : View.INVISIBLE);
  }

  private ArrayList<Integer> getSortingHatActions() {
    int randomSpots = numberOfSpots - 1;
    int evenSpots = randomSpots / HogwartsHouse.values().length;
    int oddSpots = randomSpots % HogwartsHouse.values().length;

    ArrayList<Integer> allHouses = new ArrayList<>();
    List<Integer> randomIndices = new ArrayList<>(oddSpots);

    for (int i = 0; i < HogwartsHouse.values().length; i++) {
      if (i == HogwartsHouse.GRYFFINDOR.ordinal()) {
        continue;
      }
      randomIndices.add(i);
    }

    for (int i = 0; i < evenSpots; i++) {
      for (int j = 0; j < HogwartsHouse.values().length; j++) {
        allHouses.add(j);
      }
    }

    if (oddSpots > 0) {
      Collections.shuffle(randomIndices);
      for (int i = 0; i < oddSpots; i++) {
        allHouses.add(randomIndices.get(i));
      }
    }

    Collections.shuffle(allHouses);
    allHouses.add(HogwartsHouse.GRYFFINDOR.ordinal());
    return allHouses;
  }
}
