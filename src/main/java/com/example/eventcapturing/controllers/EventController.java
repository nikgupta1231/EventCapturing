package com.example.eventcapturing.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.eventcapturing.beans.Event;
import com.example.eventcapturing.beans.enums.NounEnum;
import com.example.eventcapturing.beans.enums.VerbEnum;
import com.example.eventcapturing.repo.EventRepo;
import com.google.gson.Gson;

@RestController
@RequestMapping("/")
public class EventController {

	@Autowired
	private Gson gson;

	@Autowired
	private EventRepo eventRepo;

	@RequestMapping(value = "/event/", method = RequestMethod.POST)
	public ResponseEntity<String> eventCapturing(InputStream incomingData) {

		try {
			String jsonString = getJsonStringFromInputStream(incomingData);
			System.out.println("On Server: Received  : " + jsonString);

			if (jsonString == null || jsonString.isEmpty()) {
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}

			Event event = gson.fromJson(jsonString, Event.class);

			if (event == null || !event.isValid())
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

			event.setEventTs(convertStringToTimestamp(event.getTs()));
			System.out.println("Event:" + event);

			// logic-rules goes here

//			1. Trigger a push notification on very first bill pay event for the user
			List<Event> events = eventRepo.findByUseridAndNounAndVerb(event.getUserId(), NounEnum.BILL.getValue(),
					VerbEnum.PAY.getValue());
			if (events == null || events.size() == 0) {
				sendPushNotificationToUser(event.getUserId());
			}

//			2. Alert user if 5 or more bill pay events of total value >= 20000 happen within 5 minutes time window.
			events = eventRepo.findByUseridAndEventTsBetween(event.getUserId(), event.getEventTs() - 1 - 5 * 60 * 1000,
					event.getEventTs() + 1);

			System.out.println("Events:" + events.size());

			float totalAmount = 0;
			int billCount = 0;
			if (events == null || events.size() < 0) {
				System.out.println("No older events found.");
			} else {
				for (Event thisEvent : events) {
					if (isBillPayment(thisEvent)) {
						totalAmount += thisEvent.getProperties().getValue();
						billCount += 1;
					}
				}
			}

			System.out.println("totalAmount:" + totalAmount + "\n billCount: " + billCount);

			if (totalAmount >= 20000 || billCount >= 5) {
				alertUser(event.getUserId());
			}

			eventRepo.save(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<String>(HttpStatus.OK);
	}

	private boolean isBillPayment(Event event) {
		return event.getVerb().equals(VerbEnum.PAY.getValue()) && event.getNoun().equals(NounEnum.BILL.getValue());
	}

	public String getJsonStringFromInputStream(InputStream incomingData) {
		StringBuilder builder = new StringBuilder();
		String line;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			while ((line = in.readLine()) != null) {
				builder.append(line); // appending into string builder
			}

			if (builder != null && builder.toString() != null)
				return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public long convertStringToTimestamp(String strDate) {
		try {
			DateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
			Date date = formatter.parse(strDate);
			return date.getTime();
		} catch (Exception e) {
			System.out.println("Exception :" + e);
		}
		return 0;
	}

	public void sendPushNotificationToUser(int userId) {
		System.out.println("Send push notification to user:" + userId);
	}

	public void alertUser(int userId) {
		System.out.println("Send alert to user:" + userId);
	}

}
