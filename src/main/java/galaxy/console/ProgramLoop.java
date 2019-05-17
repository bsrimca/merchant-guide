package galaxy.console;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

import galaxy.console.command.Command;

public class ProgramLoop
{
  private final List<Command> commands;

  public ProgramLoop(Command... commands)
  {
    this.commands = Arrays.asList(commands);
  }

  public void run(Reader reader, PrintStream stream)
  {
    new BufferedReader(reader)
      .lines()
      .forEach((String line) ->
        {
          parse(line, stream);
        }
      );
  }

  private void parse(String line, PrintStream stream)
  {
    boolean notParseable = true;
    for(Command cmd: commands)
    {
      if(cmd.recognize(line))
      {
        cmd.execute(line);
        notParseable = false;
      }
    }
    if(notParseable)
    {
      stream.println("I have no idea what you are talking about");
    }
  }

}
