package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteDaoIntTest {

  @Autowired private QuoteDao quoteDao;
  private Quote savedQuote;

  @Before
  public void insertOne() throws Exception {
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setTicker("AAPL");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
  }

  @After
  public void deleteOne() throws Exception {
    quoteDao.deleteById(savedQuote.getTicker());
  }

  @Test
  public void findById() {
    assertEquals(savedQuote.getTicker(), quoteDao.findById("AAPL"));
  }

  @Test
  public void existsById() {
    assertTrue(quoteDao.existsById("AAPL"));
  }

  @Test
  public void findAll() {
    List<Quote> savedQuote = quoteDao.findAll();
    assertEquals(savedQuote.size(), 1);
  }

  @Test
  public void count() {
    assertEquals(quoteDao.count(), 1);
  }
}
