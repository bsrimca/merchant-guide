package galaxy.application.service;

import java.util.regex.Pattern;

import galaxy.application.model.RomanNumber;

public class RomanToDecimalConverter
{
  public String convert(RomanNumber input)
  {
    String inputString = input.toString();
    validate(inputString);
    char[] inputArray = inputString.toCharArray();
    int sum =0;
    int previous = 1000;
    sum = calculate(inputArray, sum, previous);
    return Integer.toString(sum);
  }

  private int calculate(char[] inputArray, int sum, int previous)
  {
    for(int i=0; i<inputArray.length; i++)
    {
      int value = valueOf(inputArray[i]);

      if(value>previous) {
        sum -= previous;
        value -= previous;
      }
      sum += value;
      previous = value;
    }
    return sum;
  }

  public void validate(String input) {
    check1stConstraintAgainst(input);
    check2ndConstraintAgainst(input);
    check3rdConstraintAgainst(input);
  }

  private Integer valueOf(char val) {
    switch (val) {
      case 'I': return 1;
      case 'V': return 5;
      case 'X': return 10;
      case 'L': return 50;
      case 'C': return 100;
      case 'D': return 500;
      case 'M': return 1000;
      default: throw new InvalidRomanNumberException();
    }
  }

  private void check1stConstraintAgainst(String input) {
    if (tooManyIorXorCorMin(input)) {
      throw new InvalidRomanNumberException();
    }
    if (tooManyVorLorDin(input)) {
      throw new InvalidRomanNumberException();
    }
  }

  private void check2ndConstraintAgainst(String input) {
    if (wrongIsubstractIn(input)) {
      throw new InvalidRomanNumberException();
    }
    if (wrongXsubstractIn(input)) {
      throw new InvalidRomanNumberException();
    }
    if (VisSubtractedIn(input)) {
      throw new InvalidRomanNumberException();
    }
    if (LisSubtractedIn(input)) {
      throw new InvalidRomanNumberException();
    }
    if (DisSubtractedIn(input)) {
      throw new InvalidRomanNumberException();
    }
  }

  private static boolean VisSubtractedIn(String input) {
    Pattern pattern = Pattern.compile("(VX)|(VL)|(VC)|(VD)|(VM)");
    return pattern.matcher(input).matches();
  }

  private static boolean LisSubtractedIn(String input) {
    Pattern pattern = Pattern.compile("(LC)|(LD)|(LM)");
    return pattern.matcher(input).matches();
  }

  private static boolean DisSubtractedIn(String input) {
    Pattern pattern = Pattern.compile("(DM)");
    return pattern.matcher(input).matches();
  }

  private void check3rdConstraintAgainst(String input) {
    String rgxIvalidationBeforeVandX = "(I(I)+V)|(I(I)+X)";
    String rgxXvalidationBeforeLandC = "(X(X)+L)|(X(X)+C)";
    String rgxCvalidationBeforeDandM = "(C(C)+D)|(C(C)+M)";

    Pattern Ipattern = Pattern.compile(rgxIvalidationBeforeVandX
      + "|" + rgxXvalidationBeforeLandC
      + "|" + rgxCvalidationBeforeDandM);

    if (Ipattern.matcher(input).matches()) {
      throw new InvalidRomanNumberException();
    }
  }

  private static boolean wrongXsubstractIn(String input) {
    Pattern pattern = Pattern.compile("(XD)|(XM)");
    return pattern.matcher(input).matches();
  }

  private static boolean wrongIsubstractIn(String input) {
    Pattern pattern = Pattern.compile("(IL)|(IC)|(ID)|(IM)");
    return pattern.matcher(input).matches();
  }

  private static boolean tooManyVorLorDin(String input) {
    Pattern pattern = Pattern.compile("(VV)|(LL)|(DD)");
    return pattern.matcher(input).matches();
  }

  private static boolean tooManyIorXorCorMin(String input) {
    Pattern pattern = Pattern.compile("(IIII)|(XXXX)|(CCCC)|(MMMM)");
    return pattern.matcher(input).matches();
  }}
