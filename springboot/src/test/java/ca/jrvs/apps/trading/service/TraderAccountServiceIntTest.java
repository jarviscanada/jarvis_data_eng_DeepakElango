package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.AccountDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Account;
import ca.jrvs.apps.trading.model.domain.Trader;
import ca.jrvs.apps.trading.model.domain.TraderAccountView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class TraderAccountServiceIntTest {

    private Trader savedTrader;
    private Account account;
    private TraderAccountView savedView;
    @Autowired
    private TraderAccountService traderAccountService;
    @Autowired
    private TraderDao traderDao;
    @Autowired
    private AccountDao accountDao;

    @Before
    public void setUp() throws Exception {
        savedTrader = new Trader();
        savedTrader.setFirstName("Deepak");
        savedTrader.setLastName("Elango");
        savedTrader.setCountry("CANADA");
        savedTrader.setDob(new Date(System.currentTimeMillis()));
        savedTrader.setEmail("deelango96@gmail.ca");
        traderDao.save(savedTrader);
    }

    @After
    public void tearDown() throws Exception {
        accountDao.deleteAll();
        traderDao.deleteAll();
    }

    @Test
    public void createTraderAndAccount() {
        assertEquals(1, traderDao.count());
    }

    @Test
    public void deleteTraderById() {
        traderAccountService.deleteTraderById(savedTrader.getId());
        assertEquals(0, traderDao.count());
    }

    @Test
    public void deposit() {
        traderAccountService.deposit(savedTrader.getId(), 1000.00);
        assertEquals(new Double(1000), account.getAmount());
    }

    @Test
    public void withdraw() {
        traderAccountService.deposit(savedTrader.getId(), 1000.00);
        traderAccountService.withdraw(savedTrader.getId(), 100.00);
        assertEquals(new Double(1000-100), account.getAmount());
    }
}