/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.service.impl.repository;

import com.qlsv5.dto.TkbDto;
import com.qlsv5.entity.GiangVienEntity;
import com.qlsv5.entity.SinhVienEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface GiangVienRepository extends MongoRepository<GiangVienEntity, String> {
    int countGiangVienByMaGv(String maGiangVien);
    int countGiangVienById(String id);
    int countGiangVienByEmail(String email);
    List<GiangVienEntity> getListGiangVienByMaKhoa(String maKhoa);
    @Query(value = "{'maGiangVien': ?0, '_id': {$ne: ?1}}", count = true)
    Long countGiangVienByMaGvAndNotId(String maGiangVien, String id);
    @Query(value = "{'email': ?0, '_id': {$ne: ?1}}", count = true)
    Long countGiangVienByEmailAndNotId(String email, String id);

    int countGiangVienByMaKhoa(String maKhoa);

    GiangVienEntity findByMaGv(String maGv);

    Page<GiangVienEntity> findAllByMaKhoa(String maKhoa, Pageable pageable);

}
