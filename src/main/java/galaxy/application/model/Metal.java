package galaxy.application.model;

public enum Metal
{
  Gold,
  Silver,
  Iron;

  public static String eitherOneType()
  {
    return Gold+"|"+Silver+"|"+Iron;
  }
}
