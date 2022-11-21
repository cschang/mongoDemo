package com.line.mongoDemo.repository;

import com.line.mongoDemo.entity.LineUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends MongoRepository<LineUser,String> {
}
