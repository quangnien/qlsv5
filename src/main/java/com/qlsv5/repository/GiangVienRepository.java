/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.SinhVienEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface GiangVienRepository extends MongoRepository<GiangVienEntity, String> {
    int countGiangVienByMaGv(String maGiangVien);
    int countGiangVienById(String id);
    int countGiangVienByEmail(String email);
    List<GiangVienEntity> getListGiangVienByMaKhoa(String maKhoa);
    @Query(value = "{'maGiangVien': ?0, '_id': {$ne: ?1}}", count = true)
    Long countGiangVienByMaGvAndNotId(String maGiangVien, String id);
    @Query(value = "{'email': ?0, '_id': {$ne: ?1}}", count = true)
    Long countGiangVienByEmailAndNotId(String email, String id);
}
