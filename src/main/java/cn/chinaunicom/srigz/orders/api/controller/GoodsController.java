package cn.chinaunicom.srigz.orders.api.controller;

import cn.chinaunicom.srigz.orders.Service.GoodsService;
import cn.chinaunicom.srigz.orders.common.utils.ActionHelper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Api(value = "商品管理" ,tags = {"商品的接口"})
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 根据商品编码查询商品信息
     * @param goodsSku
     * @return
     */
    public Map getGoodsByGooodsSku(String goodsSku){
        return ActionHelper.responseOk(goodsService.getGoodsByGooodsSku(goodsSku));
    }
}
