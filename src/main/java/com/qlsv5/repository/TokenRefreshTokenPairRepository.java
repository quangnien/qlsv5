/*******************************************************************************
 * Class        ：AbstactAccountRepository
 * Created date ：2018/01/31
 * Lasted date  ：2018/01/31
 * Author       ：phatvt
 * Change log   ：2018/01/31：01-00 phatvt create a new
 ******************************************************************************/
package com.qlsv5.repository;

import com.qlsv5.entity.SinhVienEntity;
import com.qlsv5.entity.TokenRefreshTokenPairEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TokenRefreshTokenPairRepository extends MongoRepository<TokenRefreshTokenPairEntity, String> {
    TokenRefreshTokenPairEntity findTokenRefreshTokenPairByJti(String jti);
}
