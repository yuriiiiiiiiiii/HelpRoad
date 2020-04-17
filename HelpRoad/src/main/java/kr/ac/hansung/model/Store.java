package kr.ac.hansung.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Store {
	
	private int floor;
	private String name;
	private String location;
	
	public void setFloor(int floor) {
		this.floor=floor;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setLocation(String location) {
		this.location=location;
	}
}
