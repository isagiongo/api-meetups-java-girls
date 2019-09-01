package com.isagiongo.meetupsjavagirls.repository;

import com.isagiongo.meetupsjavagirls.models.Meetup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetupRepository extends MongoRepository<Meetup, String> {

}
