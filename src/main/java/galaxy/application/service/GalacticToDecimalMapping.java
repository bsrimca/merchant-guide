package galaxy.application.service;

import java.util.Optional;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.model.RomanDigit;
import galaxy.application.model.RomanNumber;
import galaxy.application.model.SymbolMapping;
import galaxy.application.repository.Repository;

public class GalacticToDecimalMapping
{
  private final Repository<String,SymbolMapping> repository;
  private final RomanToDecimalConverter romanToDecimalConverter;

  public GalacticToDecimalMapping(
    Repository<String, SymbolMapping> repository, RomanToDecimalConverter romanToDecimalConverter)
  {
    this.repository = repository;
    this.romanToDecimalConverter = romanToDecimalConverter;
  }

  public Optional<Integer> map(GalacticSymbol symbol)
  {
    RomanDigit[] digit = new RomanDigit[symbol.word.length];
    boolean notFullyMapped = map(symbol, digit);
    if (notFullyMapped)
      return Optional.empty();
    RomanNumber number = new RomanNumber(digit);
    return Optional.of(Integer.valueOf(romanToDecimalConverter.convert(number)));
  }

  private boolean map(GalacticSymbol symbol, RomanDigit[] num)
  {
    for(int i = 0; i<symbol.word.length; i++)
    {
      Optional<SymbolMapping> numeral = repository.findBy(symbol.word[i]);
      if(!numeral.isPresent())
      {
        return true;
      }
      num[i]= numeral.get().roman;
    }
    return false;
  }

}
