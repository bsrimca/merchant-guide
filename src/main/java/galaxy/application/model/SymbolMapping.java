package galaxy.application.model;

import java.util.Objects;

public class SymbolMapping
{
  public String digit;
  public RomanDigit roman;

  public SymbolMapping(String digit, RomanDigit roman)
  {
    this.digit = digit;
    this.roman = roman;
  }

  @Override
  public int hashCode()
  {
    return super.hashCode();
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SymbolMapping sym = (SymbolMapping) obj;
    return Objects.equals(sym.digit, digit) &&
      Objects.equals(sym.roman,roman);
  }

}
