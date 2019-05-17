package galaxy.inmemory;

import java.util.Map;
import java.util.Optional;

import galaxy.application.model.SymbolMapping;
import galaxy.application.repository.Repository;

public class InMemorySymbolMappingRepository implements Repository<String,SymbolMapping>
{

  private final Map<String,SymbolMapping> storage;

  public InMemorySymbolMappingRepository(
    Map<String, SymbolMapping> storage)
  {
    this.storage = storage;
  }

  @Override
  public Optional<SymbolMapping> findBy(String digit)
  {
    SymbolMapping symbolMapping = storage.get(digit);
    return Optional.ofNullable(symbolMapping);
  }

  @Override
  public void add(SymbolMapping mapping)
  {
    storage.put(mapping.digit, mapping);
  }
}
