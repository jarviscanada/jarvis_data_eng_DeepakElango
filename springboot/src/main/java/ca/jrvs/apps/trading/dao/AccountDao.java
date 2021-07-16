package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class AccountDao extends JdbcCrudDao<Account> {

  private static final String TABLE_NAME = "account";
  private static final String ID_COLUMN_NAME = "id";
  private static final String TRADER_ID = "trader_id";
  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public AccountDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert =
        new SimpleJdbcInsert(dataSource)
            .withTableName(TABLE_NAME)
            .usingGeneratedKeyColumns(ID_COLUMN_NAME);
  }

  @Override
  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  @Override
  public SimpleJdbcInsert getSimpleJdbcInsert() {
    return simpleJdbcInsert;
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

  @Override
  public String getIdColumnName() {
    return ID_COLUMN_NAME;
  }

  public static String getTraderId() {
    return TRADER_ID;
  }

  @Override
  Class<Account> getEntityClass() {
    return Account.class;
  }

  @Override
  public int updateOne(Account account) {
    return jdbcTemplate.update(
        "UPDATE " + getTableName() + " SET amount=? WHERE " + getIdColumnName() + "=?",
        account.getAmount(),
        account.getTraderId());
  }

  public Optional<Account> findByTraderId(Integer id) {
    try {
      Account account =
          jdbcTemplate.queryForObject(
              "SELECT * FROM " + getTableName() + " WHERE " + getTraderId() + " =?",
              BeanPropertyRowMapper.newInstance(Account.class),
              id);
      if (account != null) {
        return Optional.of(account);
      }
    } catch (Exception e) {
      return Optional.empty();
    }
    return Optional.empty();
  }

  @Override
  public <S extends Account> Iterable<S> saveAll(Iterable<S> iterable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(Account account) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteAll(Iterable<? extends Account> iterable) {
    throw new UnsupportedOperationException();
  }
}
