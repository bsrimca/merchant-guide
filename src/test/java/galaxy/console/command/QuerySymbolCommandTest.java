package galaxy.console.command;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.usecase.SymbolNotFoundException;
import galaxy.application.usecase.querySymbol.QuerySymbol;
import galaxy.application.usecase.querySymbol.QuerySymbolResponse;
import galaxy.console.command.QuerySymbolCommand;
import galaxy.console.display.DisplaySymbol;
import galaxy.util.JUnitRuleClassMockery;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QuerySymbolCommandTest
{

  @Rule
  public JUnitRuleMockery context = new JUnitRuleClassMockery();

  @Mock
  private QuerySymbol useCase;
  @Mock
  private DisplaySymbol displaySymbol;

  private QuerySymbolCommand underTest;

  @Before
  public void setup()
  {
    underTest = new QuerySymbolCommand(useCase, displaySymbol);
  }

  @Test
  public void unparseableCommand()
  {
    boolean recognize = underTest.recognize("this is not parsable");
    assertThat(recognize, is(false));
  }

  @Test
  public void parseableCommand()
  {
    boolean recognize = underTest.recognize("how much is pish tegj glob glob ?");
    assertThat(recognize, is(true));
  }

  @Test
  public void succesfulExecution()
  {
    GalacticSymbol param = new GalacticSymbol(new String[] {"pish", "tegj", "glob", "glob"});
    QuerySymbolResponse param1 = new QuerySymbolResponse(new GalacticSymbol("pish tegj glob glob"),42);

    context.checking(new Expectations()
    {{
      oneOf(useCase).execute(with(param));
      will(returnValue(param1));
      oneOf(displaySymbol).show(with(param1));
    }});
    underTest.execute("how much is pish tegj glob glob ?");
  }

  @Test
  public void unknownSymbol()
  {
    GalacticSymbol param = new GalacticSymbol(new String[] {"pish", "tegj", "glob", "glob"});
    QuerySymbolResponse param1 = new QuerySymbolResponse(new GalacticSymbol("pish tegj glob glob"),42);

    context.checking(new Expectations()
    {{
      oneOf(useCase).execute(with(param));
      will(throwException(new SymbolNotFoundException()));
      never(displaySymbol).show(with(param1));
      oneOf(displaySymbol).symbolNotFound();
    }});
    underTest.execute("how much is pish tegj glob glob ?");
  }
}
