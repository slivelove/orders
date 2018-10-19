package cn.chinaunicom.srigz.orders.Service;

import cn.chinaunicom.srigz.orders.Entity.GoodsInfo;
import cn.chinaunicom.srigz.orders.dao.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@EnableAutoConfiguration
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    /**
     * 根据商品编码查询商品信息
     * @param goodsSku
     * @return
     */
    public GoodsInfo getGoodsByGooodsSku(String goodsSku){
        Optional<GoodsInfo> goods = goodsRepository.findById(goodsSku);
        return goods.get();
    }
}
