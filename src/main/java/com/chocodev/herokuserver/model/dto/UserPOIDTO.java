package com.chocodev.herokuserver.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="user_poi")
public class UserPOIDTO {
	@Id
	@Column(name="user_poi_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer id;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="longitude")	
	private Double longitude;
	
	@Column(name="bearing")
	private Double bearing;
	
	@Column(name="dateAdded")
	private int dateAdded;

	@Column(name="user_id")
	private String userId;
	
	@Column(name="speed")
	private Double speed;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getBearing() {
		return bearing;
	}

	public void setBearing(Double bearing) {
		this.bearing = bearing;
	}

	public int getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(int dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	
}
