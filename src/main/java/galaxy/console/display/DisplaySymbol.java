package galaxy.console.display;

import java.io.PrintStream;

import galaxy.application.usecase.querySymbol.QuerySymbolResponse;

public class DisplaySymbol
{
  private final PrintStream printStream;

  public DisplaySymbol(PrintStream printStream)
  {
    this.printStream = printStream;
  }

  public void show(QuerySymbolResponse response)
  {
    printStream.println(response.symbol+" is "+response.value);
  }

  public void symbolNotFound()
  {
    printStream.println("Sorry, I cannot find one of the symbol");
  }

}
