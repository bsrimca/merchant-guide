package galaxy.console.command;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import galaxy.application.model.RomanDigit;
import galaxy.application.model.SymbolMapping;
import galaxy.application.usecase.declareSymbol.DeclareSymbol;

public class DeclareSymbolCommand implements Command
{
  private final Pattern pattern = Pattern.compile("([a-zA-Z]+)\\sis\\s"+ RomanDigit.eitherOneDigit());

  private final DeclareSymbol useCase;

  public DeclareSymbolCommand(DeclareSymbol useCase)
  {
    this.useCase = useCase;
  }

  @Override
  public boolean recognize(String commandPattern)
  {
    return pattern.matcher(commandPattern).matches();
  }

  @Override
  public void execute(String commandPattern)
  {
    SymbolMapping symbolMapping = parse(commandPattern);
    useCase.execute(symbolMapping);
  }

  private SymbolMapping parse(String commandPattern)
  {
    Matcher matcher = pattern.matcher(commandPattern);
    matcher.matches();
    String symbolName = matcher.group(1);
    RomanDigit numeral = RomanDigit.valueOf(matcher.group(2));
    return new SymbolMapping(symbolName, numeral);
  }

}
