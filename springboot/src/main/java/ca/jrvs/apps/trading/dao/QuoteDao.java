package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Quote;

import java.util.*;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteDao implements CrudRepository<Quote, String> {

  private static final String TABLE_NAME = "quote";
  private static final String ID_COLUMN_NAME = "ticker";

  private static final Logger logger = LoggerFactory.getLogger(QuoteDao.class);
  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert simpleJdbcInsert;

  @Autowired
  public QuoteDao(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
    simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName(TABLE_NAME);
  }

  @Override
  public Quote save(Quote quote) {
    if (existsById(quote.getId())) {
      int updateRowNo = updateOne(quote);
      if (updateRowNo != 1) {
        throw new DataRetrievalFailureException("Unable to update quote: " + quote.getId());
      }
    } else {
      addOne(quote);
    }
    return quote;
  }

  /** Helper method that saves one quote */
  private void addOne(Quote quote) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(quote);
    int row = simpleJdbcInsert.execute(parameterSource);
    if (row != 1) {
      throw new IncorrectResultSizeDataAccessException("Failed to insert", 1, row);
    }
  }

  /** Helper method that updates one quote */
  private int updateOne(Quote quote) {
    String updateSql =
        "UPDATE quote SET last_price=?, bid_price=?, "
            + "bid_size=?, ask_price=?, ask_size=? WHERE ticker=?";
    return jdbcTemplate.update(updateSql, makeUpdateValues(quote));
  }

  /** Helper method that makes sql update values objects */
  private Object[] makeUpdateValues(Quote quote) {
    return new Object[] {
      quote.getLastPrice(),
      quote.getBidPrice(),
      quote.getBidSize(),
      quote.getAskPrice(),
      quote.getAskSize(),
      quote.getTicker()
    };
  }

  @Override
  public <S extends Quote> List<S> saveAll(Iterable<S> quotes) {
    quotes.forEach(this::save);
    return (List<S>) quotes;
  }

  /** Return all quotes */
  @Override
  public List<Quote> findAll() {
    return jdbcTemplate.query(
        "SELECT * FROM " + TABLE_NAME, BeanPropertyRowMapper.newInstance(Quote.class));
  }

  /** Find a quote by ticker */
  @Override
  public Optional<Quote> findById(String ticker) {
    return Optional.ofNullable(
        jdbcTemplate.queryForObject(
            "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?",
            BeanPropertyRowMapper.newInstance(Quote.class),
            ticker));
  }

  @Override
  public boolean existsById(String ticker) {
    return findById(ticker).isPresent();
  }

  @Override
  public void deleteById(String ticker) {
    jdbcTemplate.update("DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN_NAME + "=?", ticker);
  }

  @Override
  public long count() throws NullPointerException {
    return jdbcTemplate.queryForObject("SELECT COUNT(*) FROM " + TABLE_NAME, Long.class);
  }

  @Override
  public void deleteAll() {
    jdbcTemplate.update("DELETE FROM " + TABLE_NAME);
  }

  @Override
  public Iterable findAllById(Iterable<String> iterable) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void delete(Quote quote) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deleteAll(Iterable<? extends Quote> entities) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
