package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Point;
import kr.ac.hansung.model.Store;

@Repository
public class StoreDao {

		private JdbcTemplate jdbcTemplate;

		@Autowired
		public void setDataSource(DataSource dataSource) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		
		// 검색 글자를 포함한 이름을 가진 매장 객체 리스트 리턴
		public List<Store> getStores(String name) {

			String sqlStatement = "select * from store where name like ?"; // ?=placeholder (밑에  "%" + name + "%" 이게 들어감)

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

		// 층과 이름으로 조회한 매장 객체 리턴
		public Store getStoreByFloornName(int floor, String name) {
			
			String sqlStatement = "select * from store where name=? and floor=?";

			return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name, floor}, new RowMapper<Store>() {

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

		// 층과 이름으로 조회한 매장 위치 리턴
		public Point getStorePoint(int floor, String name) {
			
			String sqlStatement = "select * from store where name=? and floor=?";

			return jdbcTemplate.queryForObject(sqlStatement, new Object[] {name, floor}, new RowMapper<Point>() {

				@Override
				public Point mapRow(ResultSet rs, int rowNum) throws SQLException {

					Point point = new Point();

					point.setPoint(rs.getInt("row"), rs.getInt("col"));
					
					return point;
				}
			});
		}
		
}
