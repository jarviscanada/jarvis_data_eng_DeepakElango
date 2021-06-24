package ca.jrvs.apps.trading.service;

import ca.jrvs.apps.trading.dao.QuoteDao;
import ca.jrvs.apps.trading.dao.TraderDao;
import ca.jrvs.apps.trading.model.domain.Trader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TraderAccountService {

    private TraderDao traderDao;
    private AccountDao accountDao;
    private PositionDao positionDao;
    private SecurityOrderDao securityOrderDao;

    public TraderAccountService(TraderDao traderDao, AccountDao accountDao, PositionDao positionDao,
                                SecurityOrderDao securityOrderDao) {
        this.traderDao = traderDao;
        this.accountDao = accountDao;
        this.positionDao = positionDao;
        this.securityOrderDao = securityOrderDao;
    }

    /**
     * Create a new trader an initialize a new account with 0 amount
     * - validate user input (all fields must be non empty)
     * - create a trader
     * - create an account
     * - create, setup, and return a new traderAccountView
     * Assumption: to simplify the logic, each trader has only one account where traderId ==
     * accountId
     */
    public TraderAccountView createTraderAndAccount(Trader trader) {

    }

    /**
     * A trader can be deleted if it has no open position and 0 cash balance
     * - validate trader ID
     * - get trader account by traderId and check account balance
     * - get positions by accountId and check positions
     * - delete all securityOrders, account, trader (in this order)
     */
    public void deleteTraderById(Integer traderId) {

    }

    /**
     * Deposit a fund to an account by traderId
     * - validate user input
     * - account = accountDao.findByTraderId
     * - accountDao.updateAmountById
     */
    public Account deposit(Integer traderId, Double fund) {

     }

    /**
     * Withdraw a fund to an account by traderId
     * - validate user input
     * - account = accountDao.findByTraderId
     * - accountDao.updateAmountById
     */
    public Account withdraw(Integer traderId, Double fund) {

    }

}
