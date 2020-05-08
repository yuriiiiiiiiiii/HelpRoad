package kr.ac.hansung.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.model.Point;
import kr.ac.hansung.model.Position;

@Service
public class ConversionService {

	
	/*public List<Store> getCurrent(String name) {
		return storeDao.getStores(name);
	}*/
	
	
	 public Point mappingMap(double lat, double lon) { // lat = 현재 위도, lon = 현재 경도

	      Point cur = new Point(); // 행렬에서 현재 위치
	      Point p1 = new Point(); // 맨 왼쪽 상단 첫 지점
	      Point p2 = new Point(); // 맨 왼쪽 하단 첫 지점

	      p1.setPoint(37.560603, 126.980320); // 시작점
	      p2.setPoint(37.560020, 126.980275); // 시작점의 맨 하단점

	      double distance1 = distance(p1.getLat(), p1.getLon(), lat, lon); // 현재 위치 gps 받아서 넣기!!!!!!!!!!!!
	      double distance2 = distance(p2.getLat(), p2.getLon(), lat, lon); // 현재 위치 gps 받아서 넣기!!!!!!!!!!!!

	      double x = (distance1 * distance1 - distance2 * distance2 + 65 * 65) / (65*2); // 현재 위치에 해당하는 행렬 y좌표
	      double y = Math.sqrt(distance1 * distance1 - x * x);

	      cur.setPoint((int) x, (int) y);

	      return cur;
	   }

	   /**
	    * 두 지점간의 거리 계산 https://fruitdev.tistory.com/189
	    *
	    * @param lat1 지점 1 위도
	    * @param lon1 지점 1 경도
	    * @param lat2 지점 2 위도
	    * @param lon2 지점 2 경도
	    * @return
	    */
	 
	   private static double distance(double lat1, double lon1, double lat2, double lon2) {

	      double theta = lon1 - lon2;
	      double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
	            + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

	      dist = Math.acos(dist);
	      dist = rad2deg(dist);
	      dist = dist * 60 * 1.1515 * 1609.344;

	      return (dist);
	   }

	   // This function converts decimal degrees to radians
	   private static double deg2rad(double deg) {
	      return (deg * Math.PI / 180.0);
	   }

	   // This function converts radians to decimal degrees
	   private static double rad2deg(double rad) {
	      return (rad * 180 / Math.PI);
	   }

}
