package selenium.tests.responsive;

import context.annotations.Description;
import context.base.AbstractAnyWorkResponsiveTest;
import context.flag.ParallelExecutable;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NetworkTestExample extends AbstractAnyWorkResponsiveTest
{
    private static final Logger logger = Logger.getLogger(NetworkTestExample.class);

    @Before
    public void init() throws Exception
    {
        super.init(true);
    }

    @Test
    @Description("This area test description")
    @Category(ParallelExecutable.class)
    public void testAnywork()
    {
    }

}
