package kr.co.hanip.favorite.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FavoriteGetDto {
    private int storeId;
    private int userId;
}
