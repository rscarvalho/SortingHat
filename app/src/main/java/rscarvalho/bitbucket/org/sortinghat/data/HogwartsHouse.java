package rscarvalho.bitbucket.org.sortinghat.data;

import rscarvalho.bitbucket.org.sortinghat.R;

public enum HogwartsHouse {
  GRYFFINDOR(R.string.house_gryffindor, R.drawable.crest_gryffindor, R.raw.clip_gryffindor),
  SLYTHERIN(R.string.house_slytherin, R.drawable.crest_slytherin, R.raw.clip_slytherin),
  HUFFLEPUFF(R.string.house_hufflepuff, R.drawable.crest_hufflepuff, R.raw.clip_hufflepuff),
  RAVENCLAW(R.string.house_ravenclaw, R.drawable.crest_ravenclaw, R.raw.clip_ravenclaw);

  private final int nameId;
  private final int crestId;
  private final int clipId;

  HogwartsHouse(int nameId, int crestId, int clipId) {
    this.nameId = nameId;
    this.crestId = crestId;
    this.clipId = clipId;
  }

  public int getNameId() {
    return nameId;
  }

  public int getCrestId() {
    return crestId;
  }

  public int getClipId() {
    return clipId;
  }
}
