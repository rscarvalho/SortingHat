package io.github.rscarvalho.sortinghat.media;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.AsyncTask;

import java.util.Timer;
import java.util.TimerTask;

import io.github.rscarvalho.sortinghat.data.HogwartsHouse;

public class HouseClipPlayer extends AsyncTask<HogwartsHouse, Void, Void> implements MediaPlayer.OnCompletionListener {
  private final Context context;
  private final MediaPlayer.OnCompletionListener onCompletionListener;
  private MediaPlayer mediaPlayer;
  private float playerVolume = 1.0f;
  private float speed = 0.05f;

  public HouseClipPlayer(Context context, MediaPlayer.OnCompletionListener onCompletionListener) {
    this.context = context;
    this.onCompletionListener = onCompletionListener;
  }

  @Override
  protected Void doInBackground(HogwartsHouse... params) {
    HogwartsHouse house = params[0];
    mediaPlayer = MediaPlayer.create(context, house.getClipId());
    mediaPlayer.setOnCompletionListener(this);
    final Timer fadeOutTimer = new Timer("clip-fade-out", false);

    int fadeOut = 1000;
    int interval = 20;
    int duration = mediaPlayer.getDuration();
    speed = fadeOut / interval / 1000.0f;

    TimerTask fadeOutTask = new TimerTask() {
      @Override
      public void run() {
        updateVolume(-1.0f);
        if (playerVolume <= 0) {
          fadeOutTimer.cancel();
          fadeOutTimer.purge();
        }
      }
    };

    mediaPlayer.start();
//    fadeOutTimer.schedule(fadeOutTask, duration - fadeOut - 1000, interval);
    return null;
  }

  private void updateVolume(float delta) {
    playerVolume += Math.max(Math.min(speed * delta, 1.0f), 0f);
    mediaPlayer.setVolume(playerVolume, playerVolume);
  }

  @Override
  public void onCompletion(MediaPlayer mp) {
    if (onCompletionListener != null) {
      onCompletionListener.onCompletion(mp);
    }
    mp.release();
  }
}
