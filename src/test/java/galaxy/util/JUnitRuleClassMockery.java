package galaxy.util;

import org.jmock.integration.junit4.JUnitRuleMockery;
import org.jmock.lib.legacy.ClassImposteriser;

public class JUnitRuleClassMockery extends JUnitRuleMockery
{
  {
    setImposteriser(ClassImposteriser.INSTANCE);
  };
}
