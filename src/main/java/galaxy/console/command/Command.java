package galaxy.console.command;

public interface Command
{
  boolean recognize(String commandPattern);

  void execute(String commandPattern);
}
