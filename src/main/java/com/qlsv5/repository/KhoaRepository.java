/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.service.impl.repository;

import com.qlsv5.entity.KhoaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface KhoaRepository extends MongoRepository<KhoaEntity, String> {

    int countKhoaByMaKhoa(String maKhoa);
    int countKhoaById(String id);

    @Query(value = "{'maKhoa': ?0, '_id': {$ne: ?1}}", count = true)
    Long countKhoaByMaKhoaAndNotId(String maKhoa, String id);

    @Query(value = "{'email': ?0, '_id': {$ne: ?1}}", count = true)
    Long countKhoaByEmailAndNotId(String email, String id);

    int countKhoaByEmail(String email);
}
