package com.qlsv5.service.impl.repository;

import com.qlsv5.entity.TuanEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;

public interface TuanRepository extends MongoRepository<TuanEntity, String> {

    TuanEntity findByTuanAndNam(int weekNumber, int localDate);

}
