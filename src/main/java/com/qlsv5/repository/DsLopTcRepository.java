/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.LopEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;

public interface DsLopTcRepository extends MongoRepository<DsLopTcEntity, String> {
    int countDsLopTcByMaLopTc(String maLopTc);
    int countDsLopTcById(String id);

    @Query(value = "{'maLopTc': ?0, '_id': {$ne: ?1}}", count = true)
    Long countDsLopTcByMaLopTcAndNotId(String maLopTc, String id);

    List<DsLopTcEntity> getListDsLopByMaLop(String maLop);

    DsLopTcEntity getDsLopTcByMaLopTc(String maLopTc);

    List<DsLopTcEntity> findByMaGvAndTimeBdLessThanEqualAndTimeKtGreaterThanEqual(String maGv, Date timeBegin, Date timeEnd);

}
