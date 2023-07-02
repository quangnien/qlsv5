/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.qlsv5.entity.DiemEntity;

public interface DiemRepository extends MongoRepository<DiemEntity, String> {
    int countDiemById(String id);

    @Query(value = "{maSv: ?0, maLopTc: ?1 }", count = true)
    Long countByMaSvAndMaLopTc(String maSv, String maLopTc);

    @Query(value = "{ _id: { $ne: ?0 }, maSv: ?1, maLopTc: ?2}", count = true)
    Long countByMaSvAndMaLopTcExcludingId(String id, String maSv, String maLopTc);

    List<DiemEntity> getListDiemByMaLopTc(String maLopTc);
    DiemEntity getDiemById(String id);

    List<DiemEntity> getListDiemByMaSv(String maSv);

    int countDiemByMaSv(String maSv);
    int countDiemByMaLopTc(String maLopTc);

    Page<DiemEntity> findAllByMaLopTc(String maLopTc, Pageable pageable);

    List<DiemEntity> findAllByMaSv(String maSv);
    List<DiemEntity> findAllByMaLopTc(String maLopTc);

    DiemEntity getDiemByMaSvAndMaLopTc(String maSv, String maLopTc);

}
