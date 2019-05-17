package galaxy.inmemory;

import org.junit.Before;
import org.junit.Test;

import galaxy.application.model.RomanDigit;
import galaxy.application.model.SymbolMapping;
import galaxy.application.repository.Repository;
import galaxy.inmemory.InMemorySymbolMappingRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InMemorySymbolMappingRepositoryTest
{
  private Repository<String,SymbolMapping> underTest;
  private final static Map<String,SymbolMapping> emptyMap = new HashMap<>();
  private final static Map<String,SymbolMapping> mapWithOnlyGrok = new HashMap<>();

  @Before
  public void setup()
  {
    mapWithOnlyGrok.put("grok",new SymbolMapping("grok", RomanDigit.C));
  }

  @Test
  public void add()
  {
    assertThat(emptyMap.containsKey("newAdd"),is(false));
    underTest = new InMemorySymbolMappingRepository(emptyMap);
    underTest.add(new SymbolMapping("newAdd", RomanDigit.C));
    assertThat(emptyMap.containsKey("newAdd"),is(true));
  }

  @Test
  public void symbolNotFound()
  {
    underTest = new InMemorySymbolMappingRepository(emptyMap);
    Optional<SymbolMapping> emptyReturn = underTest.findBy("unknown");
    assertThat(emptyReturn,is(Optional.empty()));

    underTest = new InMemorySymbolMappingRepository(mapWithOnlyGrok);
    emptyReturn = underTest.findBy("unknown");
    assertThat(emptyReturn,is(Optional.empty()));
  }

  @Test
  public void symbolFound()
  {
    underTest = new InMemorySymbolMappingRepository(mapWithOnlyGrok);
    Optional<SymbolMapping> emptyReturn = underTest.findBy("grok");
    assertThat(emptyReturn,is(Optional.of(new SymbolMapping("grok", RomanDigit.C))));
  }
}
