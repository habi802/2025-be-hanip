package kr.co.hanip.order;

import kr.co.hanip.order.model.OrderPostDto;
import kr.co.hanip.order.model.OrderPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderMapper orderMapper;

    public int saveOrder(OrderPostReq req, int SessionMemberId) {
        return orderMapper.save(req);
    }
}
