package com.isagiongo.meetupsjavagirls.repository;

import com.isagiongo.meetupsjavagirls.models.Talk;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRepository extends MongoRepository<Talk, String> {
}
