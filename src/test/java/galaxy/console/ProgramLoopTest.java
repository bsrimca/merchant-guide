package galaxy.console;

import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import galaxy.console.ProgramLoop;
import galaxy.console.command.Command;
import galaxy.util.JUnitRuleClassMockery;

import java.io.PrintStream;
import java.io.StringReader;

public class ProgramLoopTest
{
  @Rule
  public JUnitRuleMockery context = new JUnitRuleClassMockery();

  @Mock
  private Command command1;

  @Mock
  private Command command2;

  @Mock
  private PrintStream outStream;

  private ProgramLoop underTest;

  @Before
  public void setup()
  {
    underTest = new ProgramLoop(command1, command2);
  }

  @Test
  public void allCommandsAreRecognized() throws Exception
  {
    context.checking(new Expectations(){{
      allowing(command1).recognize("command1");
      will(returnValue(true));
      allowing(command1).recognize("command2");
      will(returnValue(false));
      allowing(command2).recognize("command2");
      will(returnValue(true));
      allowing(command2).recognize("command1");
      will(returnValue(false));
      oneOf(command1).execute("command1");
      oneOf(command2).execute("command2");
    }});

    underTest.run(new StringReader("command1\ncommand2"),outStream);
  }


  @Test
  public void ifCommandNotRecognized_notExecuted() throws Exception
  {
    context.checking(new Expectations(){{
      allowing(command1).recognize("command1");
      will(returnValue(true));
      allowing(command2).recognize("command1");
      will(returnValue(false));
      allowing(command1).recognize("not recognized");
      will(returnValue(false));
      allowing(command2).recognize("not recognized");
      will(returnValue(false));
      oneOf(command1).execute("command1");
      oneOf(outStream).println(with(any(String.class)));
      never(command2).execute("not recognized");
    }});

    underTest.run(new StringReader("command1\nnot recognized"),outStream);
  }
}