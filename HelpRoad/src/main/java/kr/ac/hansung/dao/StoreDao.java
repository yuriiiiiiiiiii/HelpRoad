package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Store;

@Repository
public class StoreDao {

		private JdbcTemplate jdbcTemplate;

		@Autowired
		public void setDataSource(DataSource dataSource) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
	
		
		
		
	/*	public int getRowCount() {

			String sqlStatement = "select count(*) from store";
			return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
		}

	
		// query and return a single object
		public Store getStore(int floor, String name) {

			String sqlStatement = "select * from store where floor=?, name=?"; // placeholder

			return jdbcTemplate.queryForObject(sqlStatement, new Object[] { floor, name }, new RowMapper<Store>() {

				@Override
				public Store mapRow(ResultSet rs, int rowNum) throws SQLException {

					Store store = new Store();

					store.setFloor(rs.getInt("floor"));
					store.setName(rs.getString("name"));
					store.setLocation(rs.getString("location"));
					
					return store;
				}
			});
		}*/
		
		// query and return a multiple objects
		public List<Store> getStores() {

			String sqlStatement = "select * from store"; // placeholder

			return jdbcTemplate.query(sqlStatement, new RowMapper<Store>() {

				@Override
				public Store mapRow(ResultSet rs, int rowNum) throws SQLException {

					Store store = new Store();

					store.setFloor(rs.getInt("floor"));
					store.setName(rs.getString("name"));
					store.setLocation(rs.getString("location"));
					
					return store;
				}
			});
		}
/*
		public boolean insert(Store store) {

			String name = store.getName();
			String email = store.getEmail();
			String text = store.getText();

			String sqlStatement = "insert into store (floor, name, location) values (?, ?, ?)";

			return (jdbcTemplate.update(sqlStatement, new Object[] { name, email, text }) == 1);
		}

		public boolean update(Store store) {

			int id = store.getId();
			String name = storer.getName();
			String email = store.getEmail();
			String text = store.getText();

			String sqlStatement = "update store set floor=?, name=?, location=? where floor=?, name=?"; 

			return (jdbcTemplate.update(sqlStatement, new Object[] { name, email, text, id }) == 1);
		}

		public boolean delete(int id) {

			String sqlStatement = "delete from shinsegea where floor=?, name=?";

			return (jdbcTemplate.update(sqlStatement, new Object[] { id }) == 1);

		}*/

}
