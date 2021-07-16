package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.model.domain.Quote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class QuoteServiceIntTest {

  @Autowired private QuoteService quoteService;

  @Autowired private QuoteDao quoteDao;
  private Quote savedQuote;

  @Before
  public void setUp() throws Exception {
    quoteDao.deleteAll();
  }

  @Test
  public void findIexQuoteByTicker() {
    assertEquals("AAPL", quoteService.findIexQuoteByTicker("AAPL").getSymbol());
  }

  @Test
  public void updateMarketData() {
    savedQuote.setAskPrice(10d);
    savedQuote.setAskSize(10);
    savedQuote.setBidPrice(10.2d);
    savedQuote.setBidSize(10);
    savedQuote.setTicker("AAPL");
    savedQuote.setLastPrice(10.1d);
    quoteDao.save(savedQuote);
    quoteService.updateMarketData();

    assertEquals(savedQuote.getTicker(), quoteDao.findById(savedQuote.getTicker()).get().getTicker());
  }

  @Test
  public void saveQuotes() {
    List<String> ticker = Collections.singletonList("AAPL");
    assertEquals(quoteService.saveQuotes(ticker).size(), 1);
  }

  @Test
  public void saveQuote() {
    assertEquals("AAPL",quoteService.saveQuote("AAPL").getTicker());
  }

  @Test
  public void findAllQuotes() {
    assertNotNull(quoteService.findAllQuotes().size());
    assertEquals(1,quoteService.findAllQuotes().size());
  }
}
