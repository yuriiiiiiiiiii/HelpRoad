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
		
		//이름 부분 받아서 층이랑 이름 보내고싶어
		// query and return a multiple objects
		public List<Store> getStores(String name) {

			String sqlStatement = "select * from store where name like ?"; // ?=placeholder (밑에  "%" + name +		 "%" 이게 들어감)

			return jdbcTemplate.query(sqlStatement, new Object[] {"%" + name + "%"}, new RowMapper<Store>() {

				@Override
				public Store mapRow(ResultSet rs, int rowNum) throws SQLException {

					Store store = new Store();

					store.setFloor(rs.getInt("floor"));
					store.setName(rs.getString("name"));
					store.setRow(rs.getInt("row"));
					store.setCol(rs.getInt("col"));
					
					return store;
				}
			});
		}
		
		/*	
		 //층, 이름 받아서 (도착)위치를 찾아서 지도로 띄워야해
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
}
