package com.isbasi.repository;

import com.isbasi.dto.EmailDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<EmailDto, String> {


}