package kr.co.hanip.favorite;

import kr.co.hanip.favorite.model.FavoriteGetDto;
import kr.co.hanip.favorite.model.FavoriteGetRes;
import kr.co.hanip.favorite.model.FavoritePostReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FavoriteMapper {
    int save(FavoritePostReq req);
    List<FavoriteGetRes> findAllByUserId(int userId);
    Integer findByStoreIdAndUserId(FavoriteGetDto dto);
    int deleteByUserIdAndStoreId(int userId, int storeId);

}
