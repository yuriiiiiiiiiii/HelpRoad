package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.dao.StoreDao;
import kr.ac.hansung.model.Store;

@Service
public class StoreService {
	
	@Autowired
	private StoreDao storeDao;
	
	public List<Store> getCurrent() {
		return storeDao.getStores();
	}

}
