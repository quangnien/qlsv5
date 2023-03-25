/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import com.qlsv5.entity.DsLopTcEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface DsLopTcRepository extends MongoRepository<DsLopTcEntity, String> {
    int countDsLopTcByMaLopTc(String maLopTc);
    int countDsLopTcById(String id);

    @Query(value = "{'maLopTc': ?0, '_id': {$ne: ?1}}", count = true)
    Long countDsLopTcByMaLopTcAndNotId(String maLopTc, String id);
}
