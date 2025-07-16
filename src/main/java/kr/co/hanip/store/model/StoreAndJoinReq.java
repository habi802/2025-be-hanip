package kr.co.hanip.store.model;

import kr.co.hanip.user.model.UserJoinReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StoreAndJoinReq {
    private StorePostReq storePostReq;
    private UserJoinReq userJoinReq;
}
