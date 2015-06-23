package com.wxservice.framework.util;

import java.util.List;



public class BaiduMapJsonBean {
	
/*	{
	    "status":"OK",
	    "result":{
	        "location":{
	            "lng":118.192735,
	            "lat":24.495322
	        },
	        "precise":1,
	        "confidence":80,
	        "level":"\u9053\u8def"
	    }
	}*/
	
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
		String precise;
		String confidence;
		String level;
		
		
		public Location getLocation() {
			return location;
		}
		public void setLocation(Location location) {
			this.location = location;
		}
		public String getPrecise() {
			return precise;
		}
		public void setPrecise(String precise) {
			this.precise = precise;
		}
		public String getConfidence() {
			return confidence;
		}
		public void setConfidence(String confidence) {
			this.confidence = confidence;
		}
		public String getLevel() {
			return level;
		}
		public void setLevel(String level) {
			this.level = level;
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

	
}
