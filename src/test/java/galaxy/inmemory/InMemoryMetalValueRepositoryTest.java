package galaxy.inmemory;

import org.junit.Before;
import org.junit.Test;

import galaxy.application.model.Metal;
import galaxy.application.model.MetalValue;
import galaxy.application.repository.Repository;
import galaxy.inmemory.InMemoryMetalValueRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InMemoryMetalValueRepositoryTest
{
  private Repository<Metal,MetalValue> underTest;
  private final static Map<Metal,MetalValue> emptyMap = new HashMap<>();
  private final static Map<Metal,MetalValue> mapWithOnlyIron = new HashMap<>();

  @Before
  public void setup()
  {
    mapWithOnlyIron.put(Metal.Iron,new MetalValue(Metal.Iron, BigDecimal.valueOf(10)));
  }

  @Test
  public void add()
  {
    assertThat(emptyMap.containsKey(Metal.Gold),is(false));
    underTest = new InMemoryMetalValueRepository(emptyMap);
    underTest.add(new MetalValue(Metal.Gold,BigDecimal.valueOf(10)));
    assertThat(emptyMap.containsKey(Metal.Gold),is(true));
  }

  @Test
  public void metalNotFound()
  {
    underTest = new InMemoryMetalValueRepository(emptyMap);
    Optional<MetalValue> emptyReturn = underTest.findBy(Metal.Gold);
    assertThat(emptyReturn,is(Optional.empty()));

    underTest = new InMemoryMetalValueRepository(mapWithOnlyIron);
    emptyReturn = underTest.findBy(Metal.Silver);
    assertThat(emptyReturn,is(Optional.empty()));
  }

  @Test
  public void symbolFound()
  {
    underTest = new InMemoryMetalValueRepository(mapWithOnlyIron);
    Optional<MetalValue> emptyReturn = underTest.findBy(Metal.Iron);
    assertThat(emptyReturn,is(Optional.of(new MetalValue(Metal.Iron,BigDecimal.valueOf(10)))));
  }
}
