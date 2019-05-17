package galaxy.console.command;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import galaxy.application.model.GalacticSymbol;
import galaxy.application.model.Metal;
import galaxy.application.usecase.SymbolNotFoundException;
import galaxy.application.usecase.queryMetal.MetalNotFoundException;
import galaxy.application.usecase.queryMetal.QueryMetal;
import galaxy.application.usecase.queryMetal.QueryMetalRequest;
import galaxy.application.usecase.queryMetal.QueryMetalResponse;
import galaxy.console.command.QueryMetalCommand;
import galaxy.console.display.DisplayMetal;
import galaxy.util.JUnitRuleClassMockery;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class QueryMetalCommandTest
{

  @Rule
  public JUnitRuleMockery context = new JUnitRuleClassMockery();

  @Mock
  private QueryMetal useCase;
  @Mock
  private DisplayMetal display;

  private QueryMetalCommand underTest;

  @Before
  public void setup()
  {
    underTest = new QueryMetalCommand(useCase,display);
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
    boolean recognize = underTest.recognize("how many Credits is glob prok Silver ?");
    assertThat(recognize, is(true));
  }

  @Test
  public void succesfulExecution()
  {
    GalacticSymbol param = new GalacticSymbol(new String[] {"glob", "prok"});
    QueryMetalRequest request = new QueryMetalRequest(
      Metal.Silver,param
    );
    QueryMetalResponse param1 = new QueryMetalResponse(
      Metal.Silver,param, BigDecimal.valueOf(68)
    );

    context.checking(new Expectations()
    {{
      oneOf(useCase).execute(with(request));
      will(returnValue(param1));
      oneOf(display).show(with(param1));
    }});
    underTest.execute("how many Credits is glob prok Silver ?");
  }

  @Test
  public void unknownSymbol()
  {
    GalacticSymbol param = new GalacticSymbol(new String[] {"glob", "prok"});
    QueryMetalRequest request = new QueryMetalRequest(
      Metal.Silver,param
    );

    context.checking(new Expectations()
    {{
      oneOf(useCase).execute(with(request));
      will(throwException(new SymbolNotFoundException()));
      oneOf(display).symbolNotFound();
    }});
    underTest.execute("how many Credits is glob prok Silver ?");
  }

  @Test
  public void unknownMetal()
  {
    GalacticSymbol param = new GalacticSymbol(new String[] {"glob", "prok"});
    QueryMetalRequest request = new QueryMetalRequest(
      Metal.Silver,param
    );

    context.checking(new Expectations()
    {{
      oneOf(useCase).execute(with(request));
      will(throwException(new MetalNotFoundException()));
      oneOf(display).metalNotFound();
    }});
    underTest.execute("how many Credits is glob prok Silver ?");
  }
}
