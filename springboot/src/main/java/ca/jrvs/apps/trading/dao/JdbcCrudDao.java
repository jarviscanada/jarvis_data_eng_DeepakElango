package ca.jrvs.apps.trading.dao;

import ca.jrvs.apps.trading.model.domain.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class JdbcCrudDao<T extends Entity<Integer>> implements CrudRepository<T, Integer> {

  private static final Logger logger = LoggerFactory.getLogger(JdbcCrudDao.class);

  public abstract JdbcTemplate getJdbcTemplate();

  public abstract SimpleJdbcInsert getSimpleJdbcInsert();

  public abstract String getTableName();

  public abstract String getIdColumnName();

  abstract Class<T> getEntityClass();

  /**
   * Save an entity and update auto-generated integer ID
   */
  @Override
  public <S extends T> S save(S entity) {
    if (existsById(entity.getId())) {
      if (updateOne(entity) != 1) {
        throw new DataRetrievalFailureException("Unable to update quote");
      }
    } else {
      addOne(entity);
    }
    return entity;
  }

  /** Helper method that saves one entity */
  private <S extends T> void addOne(S entity) {
    SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(entity);

    Number newId = getSimpleJdbcInsert().executeAndReturnKey(parameterSource);
    entity.setId(newId.intValue());
  }

  /** Helper method that updates one entity */
  public abstract int updateOne(T entity);

  @Override
  public Optional<T> findById(Integer id) {
    Optional<T> entity = Optional.empty();
    String selectSql = "SELECT * FROM " + getTableName() + " WHERE " + getIdColumnName() + "=?";

    try {
      entity =
          Optional.ofNullable(
              (T)
                  getJdbcTemplate()
                      .queryForObject(
                          selectSql, BeanPropertyRowMapper.newInstance(getEntityClass()), id));
    } catch (IncorrectResultSizeDataAccessException e) {
      logger.debug("Cannot find trader id: " + id, e);
    }
    return entity;
  }

  @Override
  public boolean existsById(Integer id) {
    return findById(id).isPresent();
  }

  @Override
  public List<T> findAll() {
    return getJdbcTemplate()
        .query(
            "SELECT * FROM " + getTableName(), BeanPropertyRowMapper.newInstance(getEntityClass()));
  }

  @Override
  public List<T> findAllById(Iterable<Integer> ids) {
    List<T> listOfAllItems = new ArrayList<>();
    for (int id : ids) {
      listOfAllItems.add(findById(id).get());
    }
    return listOfAllItems;
  }

  @Override
  public void deleteById(Integer id) {
    getJdbcTemplate().update("DELETE FROM " + getTableName() + " WHERE " + id + " =?", id);
  }

  @Override
  public long count() throws NullPointerException {
    return getJdbcTemplate().queryForObject("SELECT COUNT(*) FROM " + getTableName(), long.class);
  }

  @Override
  public void deleteAll() {
    getJdbcTemplate().update("DELETE FROM " + getTableName());
  }
}
