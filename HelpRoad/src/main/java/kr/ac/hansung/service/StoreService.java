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
	
	public List<Store> getCurrent(String name) {
		return storeDao.getStores(name);
	}
	
	public Store getStoreByfloorNname(int floor, String name) {
		return storeDao.getStoreByFloornName(floor, name);
	}
	
	public Point getStorePoint(int floor, String name) {
		return storeDao.getStorePoint(floor, name);
	}

}
