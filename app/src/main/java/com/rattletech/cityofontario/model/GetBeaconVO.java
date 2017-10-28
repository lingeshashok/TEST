package com.rattletech.cityofontario.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class GetBeaconVO implements Serializable, Parcelable {
	
	private static final long serialVersionUID = 1L;
	
    public String uuid;
	public String minor;
	public String major;
	public String rssi;
	public String measuredpower;
	public String txmt_power;
	public String Rssi;

	public int getRef_count() {
		return ref_count;
	}

	public void setRef_count(int ref_count) {
		this.ref_count = ref_count;
	}

	public int ref_count=0;

	public String getTmp_uuid() {
		return tmp_uuid;
	}

	public void setTmp_uuid(String tmp_uuid) {
		this.tmp_uuid = tmp_uuid;
	}

	public String tmp_uuid;

	public String getTxmt_power() {
		return txmt_power;
	}

	public void setTxmt_power(String txmt_power) {
		this.txmt_power = txmt_power;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double distance;

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public String selected;

	public String getMeasuredpower() {
		return measuredpower;
	}

	public void setMeasuredpower(String measuredpower) {
		this.measuredpower = measuredpower;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getMinor() {
		return minor;
	}

	public void setMinor(String minor) {
		this.minor = minor;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getRssi() {
		return rssi;
	}

	public void setRssi(String rssi) {
		this.rssi = rssi;
	}
	public GetBeaconVO() {
		super();
	}

	public GetBeaconVO(Parcel in) {
		// TODO Auto-generated constructor stub
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(uuid);
		dest.writeString(minor);
		dest.writeString(major);
		dest.writeString(rssi);
		dest.writeString(measuredpower);
		dest.writeString(selected);
		dest.writeDouble(distance);
	}
	private void readFromParcel(Parcel in) {  
		uuid= in.readString();
		minor= in.readString();
		major= in.readString();
		rssi= in.readString();
		measuredpower=in.readString();
		selected=in.readString();
		distance=in.readDouble();
	}
	public static final Creator<GetBeaconVO> CREATOR = new Creator<GetBeaconVO>() {

		public GetBeaconVO createFromParcel(Parcel in) {
			return new GetBeaconVO(in);
		}

		public GetBeaconVO[] newArray(int size) {
			return new GetBeaconVO[size];
		}

	};

}
