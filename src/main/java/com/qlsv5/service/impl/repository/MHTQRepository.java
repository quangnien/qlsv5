package com.qlsv5.service.impl.repository;

import com.qlsv5.entity.MHTQEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MHTQRepository extends MongoRepository<MHTQEntity, String> {
//    int countMHTQByMaMHTQ(String maMHTQ);
    int countMHTQById(String id);

//    @Query(value = "{'maMh': ?0, '_id': {$ne: ?1}}", count = true)
//    Long countMHTQByMaMHTQAndNotId(String maMh, String id);

    @Query(value = "{ 'maMh': ?0, 'maMHTQ': ?1 }", count = true)
    Long countMHTQByMaMhAndMaMHTQ(String maMh, String maMHTQ);

    List<MHTQEntity> findAllByMaMh(String maMh);
    List<MHTQEntity> findAllByMaMHTQ(String maMHTQ);

//    Page<MHTQEntity> findAllByMaKhoa(String maKhoa, Pageable pageable);

//    List<MHTQEntity> findByTenMhLikeIgnoreCase(String keySearch);

}
