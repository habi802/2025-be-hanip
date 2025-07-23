package kr.co.hanip.favorite;

import kr.co.hanip.favorite.model.FavoriteGetRes;
import kr.co.hanip.favorite.model.FavoritePostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FavoriteService {
    final FavoriteMapper favoriteMapper;

    public int save(FavoritePostReq req) {
        return favoriteMapper.save(req);
    }

    public List<FavoriteGetRes> findAll(int userId) {
        return favoriteMapper.findAllByUserId(userId);
    }

    public int delete(int userId, int storeId) {
        return favoriteMapper.deleteByUserIdAndStoreId(userId, storeId);
    }
}
