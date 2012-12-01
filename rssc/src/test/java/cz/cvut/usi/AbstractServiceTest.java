package cz.cvut.usi;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tomas Mano <tomasmano@gmail.com>
 */
@ContextConfiguration("/context-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public abstract class AbstractServiceTest {
    
}
