package galaxy.application.repository;


import java.util.Optional;

public interface Repository<Key,Value>
{
  Optional<Value> findBy(Key metal);

  void add(Value metalValue);
}
