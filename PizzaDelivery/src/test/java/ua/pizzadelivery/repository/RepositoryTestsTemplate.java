package ua.pizzadelivery.repository;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.JVM)
public class RepositoryTestsTemplate extends
		AbstractTransactionalJUnit4SpringContextTests {

}
