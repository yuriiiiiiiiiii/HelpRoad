package kr.ac.hansung.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Position {
	
	private double latitude;
	private double longitude;
	private int startFloor;
	private int floor;
	private String name;
}
