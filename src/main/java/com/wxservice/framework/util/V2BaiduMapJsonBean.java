package com.wxservice.framework.util;

import java.util.List;



public class V2BaiduMapJsonBean {
	
	/*
	 
{
	 
 "status":0,
 "result":{
	    "location":{"lng":118.32298698151,"lat":24.983424030115},
	    "formatted_address":"福建省泉州市南安市",
	    "business":"",
	    "addressComponent":{
	         "city":"泉州市",
	         "district":"南安市",
	         "province":"福建省",
	         "street":"",
	         "street_number":""},
	    "pois":[],
	    "cityCode":134
	      }
}
	 
	 
	 * */
	
	String status;
	
	Result result;
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	class Result{
		Location location;
		String formatted_address;
		String business;
		AddressComponent addressComponent;
		
		
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public String getFormatted_address() {
			return formatted_address;
		}
		public void setFormatted_address(String formatted_address) {
			this.formatted_address = formatted_address;
		}
		public String getBusiness() {
			return business;
		}
		public void setBusiness(String business) {
			this.business = business;
		}
		public AddressComponent getAddressComponent() {
			return addressComponent;
		}
		public void setAddressComponent(AddressComponent addressComponent) {
			this.addressComponent = addressComponent;
		}
		
		
	}
	
	class Location{
		String lng;
		String lat;
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		
	}
 
   class AddressComponent{
	   String city;
	   String district;
	   String province;
	   String street;
	   String street_number;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getStreet_number() {
		return street_number;
	}
	public void setStreet_number(String street_number) {
		this.street_number = street_number;
	}
	   
   }
	
}
