package galaxy.application.model;

public enum RomanDigit
{
  I,
  V,
  X,
  L,
  C,
  D,
  M;

  public static String eitherOneDigit()
  {
    return "(I|V|X|L|C|D|M)";
  }
}
