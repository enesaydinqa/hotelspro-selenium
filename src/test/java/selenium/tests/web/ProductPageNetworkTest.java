package selenium.tests.web;

import context.annotations.Description;
import context.base.AbstractAnyWorkTest;
import context.flag.ParallelExecutable;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class ProductPageNetworkTest extends AbstractAnyWorkTest {
    private static final Logger logger = Logger.getLogger(ProductPageNetworkTest.class);


    @Before
    public void init() throws Exception {
        super.init(true);

    }

    @Test
    @Description("This area test description")
    @Category(ParallelExecutable.class)
    public void testAmywork() {

    }

}
