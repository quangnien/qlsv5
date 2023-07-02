/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.qlsv5.entity.ChiTietLopTcEntity;

public interface ChiTietLopTcRepository extends MongoRepository<ChiTietLopTcEntity, String> {
//    int countLopByMaLop(String maLop);
    int countChiTietLopTcById(String id);

    @Query(value = "{ maLopTc: ?0, tiet: ?1, thu: ?2 }", count = true)
    Long countByMaLopTcAndTietAndThu(String maLopTc, int tiet, String thu);

    @Query(value = "{ _id: { $ne: ?0 }, maLopTc: ?1, tiet: ?2, thu: ?3 }", count = true)
    Long countByMaLopTcAndTietAndThuExcludingId(String id, String maLopTc, int tiet, String thu);

    List<ChiTietLopTcEntity> getListChiTietLopTcByMaLopTc(String maLopTc);
    List<ChiTietLopTcEntity> findAllByMaLopTc(String maLopTc, Sort sort);

//    @Query(value = "{'maLop': ?0, '_id': {$ne: ?1}}", count = true)
//    Long countLopByMaLopAndNotId(String maLop, String id);

    int countChiTietLopTcByMaLopTc(String maLopTc);

}
