package galaxy.application.usecase;

public interface UseCaseRequestReply<Request, Response>
{
  Response execute(Request command);
}
