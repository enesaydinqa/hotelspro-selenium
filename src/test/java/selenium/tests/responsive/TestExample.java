package selenium.tests.responsive;

import context.annotations.Description;
import context.base.AbstractAnyWorkResponsiveTest;
import context.flag.ParallelExecutable;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class TestExample extends AbstractAnyWorkResponsiveTest
{

    @Before
    public void init() throws Exception
    {
        super.init();

    }

    @Test
    @Description("This area test description")
    @Category(ParallelExecutable.class)
    public void testAnywork()
    {
    }

}
