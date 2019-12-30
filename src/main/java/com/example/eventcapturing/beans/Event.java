package com.example.eventcapturing.beans;

public class Event {

	private int userid;
	private String ts;
	private String latlong;
	private String noun;
	private String verb;
	private long eventTs;
	private int timespent;
	private Properties properties;

	public int getUserId() {
		return userid;
	}

	public void setUserId(int userId) {
		this.userid = userId;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getLatlong() {
		return latlong;
	}

	public void setLatlong(String latlong) {
		this.latlong = latlong;
	}

	public String getNoun() {
		return noun;
	}

	public void setNoun(String noun) {
		this.noun = noun;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb;
	}

	public int getTimespent() {
		return timespent;
	}

	public void setTimespent(int timespent) {
		this.timespent = timespent;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public long getEventTs() {
		return eventTs;
	}

	public void setEventTs(long eventTs) {
		this.eventTs = eventTs;
	}

	public boolean isValid() {
		return (ts != null || !ts.isEmpty()) && userid > 0;
	}

	public Event(int userId, String ts, String latlong, String noun, String verb, int timespent,
			Properties properties) {
		super();
		this.userid = userId;
		this.ts = ts;
		this.latlong = latlong;
		this.noun = noun;
		this.verb = verb;
		this.timespent = timespent;
		this.properties = properties;
	}

	public Event() {
		super();
	}

	@Override
	public String toString() {
		return "Event [userId=" + userid + ", ts=" + ts + ", latlong=" + latlong + ", noun=" + noun + ", verb=" + verb
				+ ", eventTs=" + eventTs + ", timespent=" + timespent + ", properties=" + properties + "]";
	}

}
