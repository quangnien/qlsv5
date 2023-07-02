/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.service.impl.repository;

import com.qlsv5.entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SinhVienRepository extends MongoRepository<SinhVienEntity, String> {
    int countSinhVienByMaSv(String maSinhVien);
    int countSinhVienByEmail(String email);
    int countSinhVienById(String id);

    @Query(value = "{'maSinhVien': ?0, '_id': {$ne: ?1}}", count = true)
    Long countSinhVienByMaSvAndNotId(String maSinhVien, String id);
    @Query(value = "{'email': ?0, '_id': {$ne: ?1}}", count = true)
    Long countSinhVienByEmailAndNotId(String email, String id);

    int countSinhVienByMaLop(String maLop);

    List<SinhVienEntity> getListSinhVienByMaLop(String maLop);
    Page<SinhVienEntity> findAllByMaLop(String maLop, Pageable pageable);

    SinhVienEntity findByMaSv(String maSv);
}
