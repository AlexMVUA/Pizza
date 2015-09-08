package ua.pizzadelivery.repository;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;


@ContextConfiguration(locations = {"classpath:/repositoryContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class ITRepositoryTestsTemplate extends RepositoryTestsTemplate {

}
