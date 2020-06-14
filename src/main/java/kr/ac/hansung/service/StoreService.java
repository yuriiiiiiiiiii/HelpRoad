package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.StoreDao;
import kr.ac.hansung.model.Point;
import kr.ac.hansung.model.Store;

@Service
public class StoreService {
	
	@Autowired
	private StoreDao storeDao;
	
	public List<Store> getCurrent(String name) {	// 이름으로 매장 찾기
		return storeDao.getStores(name);
	}
	
	public Store getStoreByfloorNname(int floor, String name) {	// 층과 이름으로 매장 찾기
		return storeDao.getStoreByFloornName(floor, name);
	}
	
	public Point getStorePoint(int floor, String name) {	// 층과 이름으로 매장 위치 찾기
		return storeDao.getStorePoint(floor, name);
	}

}
