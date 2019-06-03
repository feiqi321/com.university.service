package com.ovft.configure.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ovft.configure.sys.bean.EduCart;
import com.ovft.configure.sys.bean.EduCartExample;
import com.ovft.configure.sys.dao.EduBookGoodsMapper;
import com.ovft.configure.sys.dao.EduCartMapper;
import com.ovft.configure.sys.service.EduCartService;
import com.ovft.configure.sys.vo.EduCartVo;
import com.ovft.configure.sys.vo.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author vvtxw
 * @create 2019-05-17 13:23
 */
@Service
public class EduCartServiceImpl implements EduCartService {
    @Resource
    private EduCartMapper eduCartMapper;

    @Resource
    private EduBookGoodsMapper eduBookGoodsMapper;

    @Override
    public void addCart(Integer goodsId, Integer goodsNum, Integer userId) {
        EduCartExample example = new EduCartExample();
        example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
        List<EduCart> eduCarts = eduCartMapper.selectByExample(example);
        if (eduCarts == null || eduCarts.size() == 0) {
            EduCart eduCart = new EduCart();
            eduCart.setGoodsId(goodsId);
            eduCart.setUserId(userId);
            eduCart.setNum(goodsNum);
            eduCartMapper.insert(eduCart);
            return;
        }

        if (eduCarts.size() == 1) {
            EduCart eduCart = eduCarts.get(0);
            eduCart.setNum(goodsNum + eduCart.getNum());
            eduCartMapper.updateByPrimaryKeySelective(eduCart);
        }
        if (eduCarts.size() > 1) {
            throw new RuntimeException("cart数据有误，请清空后在操作，错误在" + goodsId + "" + userId);
        }

    }

    @Override
    public PageBean showAllCartByUserId(Integer userId) {
        Page<Object> pageAll = PageHelper.startPage(1, 4);
        List<Object> eduCarts = eduCartMapper.selectCartByUserId(userId);

        BigDecimal accountAllMoney = new BigDecimal(0);
        for (Object eduCart : eduCarts) {
            EduCartVo eduCart1 = (EduCartVo) eduCart;
            accountAllMoney = accountAllMoney.add(eduCart1.getShopPrice());
        }
      /*  Map<String, Object> map = new HashMap<>();
        map.put("accountAllMoney", accountAllMoney);
        eduCarts.add(map);*/
        long total = pageAll.getTotal();
        return new PageBean(total, eduCarts, accountAllMoney);
    }

    @Override
    public long countCartNum(Integer userId) {
        EduCartExample eduCartExample = new EduCartExample();
        eduCartExample.createCriteria().andUserIdEqualTo(userId);
        long count = eduCartMapper.countByExample(eduCartExample);
        return count;
    }

    @Override
    public Integer countNum(Integer userId, Integer goodsId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("goods_id", goodsId);
        Integer num = eduCartMapper.countNum(map);
        return num;
    }

    @Override
    public Integer updateCart(EduCart cart) {
        return eduCartMapper.updateByPrimaryKeySelective(cart);
    }

    @Override
    public EduCartVo queryOrderInfoFromCart(Integer userId, Integer goodsId) {
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", userId);
        map.put("goods_id", goodsId);
        EduCartVo eduCartVo = eduCartMapper.queryOrderInfoFromCart(map);
        return eduCartVo;
    }
}
