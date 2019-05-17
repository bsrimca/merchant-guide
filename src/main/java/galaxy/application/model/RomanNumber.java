package galaxy.application.model;

import java.util.Arrays;

import galaxy.application.service.InvalidRomanNumberException;

public class RomanNumber
{
  public RomanDigit[] digit;

  public RomanNumber(RomanDigit[] digit)
  {
    this.digit = digit;
  }

  public RomanNumber(String full)
  {
    digit = new RomanDigit[full.length()];
    for(int i=0; i<full.length(); i++)
    {
      try
      {
        digit[i]=RomanDigit.valueOf(full.charAt(i)+"");
      }
      catch (IllegalArgumentException ex)
      {
        throw new InvalidRomanNumberException();
      }
    }
  }

  @Override
  public String toString()
  {
    String[] stringDigit = new String[digit.length];
    for(int i=0; i<stringDigit.length; i++)
    {
      stringDigit[i] = digit[i].toString();
    }
    return String.join("", stringDigit);
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    RomanNumber number = (RomanNumber) o;

    return Arrays.equals(digit, number.digit);
  }

  @Override
  public int hashCode()
  {
    return Arrays.hashCode(digit);
  }
}
