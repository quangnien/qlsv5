/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import com.qlsv5.entity.DsLopTcEntity;
import com.qlsv5.entity.MonHocEntity;
import com.qlsv5.entity.MonHocEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface MonHocRepository extends MongoRepository<MonHocEntity, String> {
    int countMonHocByMaMh(String maMonHoc);
    int countMonHocById(String id);

    @Query(value = "{'maMonHoc': ?0, '_id': {$ne: ?1}}", count = true)
    Long countMonHocByMaMhAndNotId(String maMonHoc, String id);

    MonHocEntity getMonHocByMaMh(String maMh);

    Page<MonHocEntity> findAllByMaKhoa(String maKhoa, Pageable pageable);

    List<MonHocEntity> findByTenMhLikeIgnoreCase(String keySearch);

}
