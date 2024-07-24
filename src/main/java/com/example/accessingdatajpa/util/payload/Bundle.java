package com.example.accessingdatajpa.util.payload;

import java.util.ArrayList;
import java.util.List;

import com.example.accessingdatajpa.util.payload.Resource;

public class Bundle {
	
	int total = 0;
	String type = "Bundle";
	List<Resource> entry;
	List<Resource> include;
	
	public Bundle() {
		entry = new ArrayList<Resource>();
		include = new ArrayList<Resource>();
	}
	
	public Bundle (List<Resource> entry) {
		this.entry = entry;
		this.total = entry.size();
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Resource> getEntry() {
		return entry;
	}

	public void setEntry(List<Resource> entry) {
		this.entry = entry;
	}

	public List<Resource> getInclude() {
		return include;
	}

	public void setInclude(List<Resource> include) {
		this.include = include;
	}
	
}