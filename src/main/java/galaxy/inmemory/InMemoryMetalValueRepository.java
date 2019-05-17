package galaxy.inmemory;

import java.util.Map;
import java.util.Optional;

import galaxy.application.model.Metal;
import galaxy.application.model.MetalValue;
import galaxy.application.repository.Repository;

public class InMemoryMetalValueRepository implements Repository<Metal,MetalValue>
{

  private final Map<Metal,MetalValue> storage;

  public InMemoryMetalValueRepository(
    Map<Metal,MetalValue> storage)
  {
    this.storage = storage;
  }

  @Override
  public void add(MetalValue metalValue)
  {
    storage.put(metalValue.metal, metalValue);
  }

  @Override
  public Optional<MetalValue> findBy(Metal metal)
  {
    MetalValue value = storage.get(metal);
    return Optional.ofNullable(value);
  }

}
