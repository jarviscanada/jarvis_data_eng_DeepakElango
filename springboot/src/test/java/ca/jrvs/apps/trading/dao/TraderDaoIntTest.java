package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.TestConfig;
import ca.jrvs.apps.trading.model.domain.Trader;
import com.google.common.collect.Lists;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestConfig.class})
@Sql({"classpath:schema.sql"})
public class TraderDaoIntTest {
  @Autowired private TraderDao traderDao;

  private Trader savedTrader;

  @Before
  public void insertOne() throws Exception {
    savedTrader = new Trader();
    savedTrader.setFirstName("Deepak");
    savedTrader.setLastName("Elango");
    savedTrader.setCountry("CANADA");
    savedTrader.setDob(new Date(System.currentTimeMillis()));
    savedTrader.setEmail("deelango96@gmail.ca");
    traderDao.save(savedTrader);
  }

  @After
  public void deleteOne() throws Exception {
    traderDao.deleteById(savedTrader.getId());
  }

  @Test
  public void findAllById() {
    List<Trader> traders =
        Lists.newArrayList(traderDao.findAllById(Arrays.asList(savedTrader.getId(), -1)));
    assertEquals(1, traders.size());
    assertEquals(savedTrader.getCountry(), traders.get(0).getCountry());
  }
}