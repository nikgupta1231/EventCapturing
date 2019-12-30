package com.example.eventcapturing.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.eventcapturing.beans.Event;

public interface EventRepo extends MongoRepository<Event, String> {

	Optional<Event> findByUserid(int userid);

	List<Event> findAllByUserid(int userid);

	List<Event> findByUseridAndEventTsBetween(int userid, long from, long to);

	List<Event> findByUseridAndNounAndVerb(int userid, String noun, String verb);

}
