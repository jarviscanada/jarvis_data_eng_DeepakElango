package ca.jrvs.apps.trading.model.domain;

public class TraderAccountView {

  private Account account;
  private Trader trader;

  public TraderAccountView(Trader trader, Account account) {
    this.trader = trader;
    this.account = account;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  public Trader getTrader() {
    return trader;
  }

  public void setTrader(Trader trader) {
    this.trader = trader;
  }
}
