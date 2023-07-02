/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.service.impl.repository;

import com.qlsv5.entity.KeHoachNamEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface KeHoachNamRepository extends MongoRepository<KeHoachNamEntity, String> {

    int countKeHoachNamByMaKeHoach(String maKeHoach);
    int countKeHoachNamById(String id);

    @Query(value = "{'maKeHoachNam': ?0, '_id': {$ne: ?1}}", count = true)
    Long countKeHoachNamByMaKeHoachNamAndNotId(String maKeHoachNam, String id);

    KeHoachNamEntity getKeHoachNamByMaKeHoach(String maKeHoach);

}
