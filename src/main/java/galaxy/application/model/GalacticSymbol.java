package galaxy.application.model;

import java.util.Arrays;

public class GalacticSymbol
{
  public String[] word;

  public GalacticSymbol(String[] word)
  {
    this.word = word;
  }

  public GalacticSymbol(String full)
  {
    this.word = full.split(" ");
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    GalacticSymbol that = (GalacticSymbol) o;

    return Arrays.equals(word, that.word);
  }

  @Override
  public int hashCode()
  {
    return Arrays.hashCode(word);
  }

  @Override
  public String toString()
  {
    return String.join(" ", word);
  }
}
