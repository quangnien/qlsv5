/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import com.qlsv5.entity.LopEntity;
import com.qlsv5.entity.SinhVienEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LopRepository extends MongoRepository<LopEntity, String> {
    int countLopByMaLop(String maLop);
    int countLopById(String id);

    @Query(value = "{'maLop': ?0, '_id': {$ne: ?1}}", count = true)
    Long countLopByMaLopAndNotId(String maLop, String id);

    List<LopEntity> getListLopByMaKhoa(String maKhoa);

    int countLopByMaKhoa(String maKhoa);

}
