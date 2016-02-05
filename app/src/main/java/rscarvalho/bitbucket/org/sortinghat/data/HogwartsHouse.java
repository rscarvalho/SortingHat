package rscarvalho.bitbucket.org.sortinghat.data;

import rscarvalho.bitbucket.org.sortinghat.R;

public enum HogwartsHouse {
  GRYFFINDOR(R.string.house_gryffindor, R.drawable.crest_gryffindor),
  SLYTHERIN(R.string.house_slytherin, R.drawable.crest_slytherin),
  HUFFLEPUFF(R.string.house_hufflepuff, R.drawable.crest_hufflepuff),
  RAVENCLAW(R.string.house_ravenclaw, R.drawable.crest_ravenclaw);

  private final int nameId;
  private final int crestId;

  HogwartsHouse(int nameId, int crestId) {
    this.nameId = nameId;
    this.crestId = crestId;
  }

  public int getNameId() {
    return nameId;
  }

  public int getCrestId() {
    return crestId;
  }
}
