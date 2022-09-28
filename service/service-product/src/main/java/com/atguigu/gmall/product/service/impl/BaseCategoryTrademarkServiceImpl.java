package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseCategoryTrademark;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.atguigu.gmall.product.mapper.BaseCategoryTrademarkMapper;
import com.atguigu.gmall.product.mapper.BaseTrademarkMapper;
import com.atguigu.gmall.product.service.BaseCategoryTrademarkService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BaseCategoryTrademarkServiceImpl  extends ServiceImpl<BaseCategoryTrademarkMapper,BaseCategoryTrademark> implements BaseCategoryTrademarkService {

    @Autowired
    private BaseCategoryTrademarkMapper baseCategoryTrademarkMapper;
@Autowired
private BaseTrademarkMapper baseTrademarkMapper;





    /**
     * 查询分类关联下的品牌
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseTrademark> findTrademarkList(Long category3Id) {
QueryWrapper<BaseCategoryTrademark> wrapper = new QueryWrapper<>();
wrapper.eq("category3_id",category3Id);
        List<BaseCategoryTrademark> baseCategoryTrademarkList = baseCategoryTrademarkMapper.selectList(wrapper);
        List<Long> collectIds = baseCategoryTrademarkList
                                                      .stream()
                                                      .map(baseCategoryTrademark -> baseCategoryTrademark.getTrademarkId())
                                                      .collect(Collectors.toList());

QueryWrapper<BaseTrademark> wrapper1 = new QueryWrapper<>();
wrapper1.in("id",collectIds);
        List<BaseTrademark> baseTrademarkList = baseTrademarkMapper.selectList(wrapper1);

        return baseTrademarkList;
    }

    @Override
    public void remove(Long category3Id, Long trademarkId) {
        QueryWrapper<BaseCategoryTrademark> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category3_id",category3Id);
        queryWrapper.eq("trademark_id",trademarkId);
        //删除关联
        baseCategoryTrademarkMapper.delete(queryWrapper);
    }

    /**
     * 查询当前分类没有关联的品牌列表
     * @param category3Id
     * @return
     */
    @Override
    public List<BaseTrademark> findCurrentTrademarkList(Long category3Id) {

        QueryWrapper<BaseCategoryTrademark> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("category3_id",category3Id);
        List<BaseCategoryTrademark> baseCategoryTrademarkList = baseCategoryTrademarkMapper.selectList(wrapper1);
        List<Long> collectIds = baseCategoryTrademarkList
                .stream()
                .map(baseCategoryTrademark -> baseCategoryTrademark.getTrademarkId())
                .collect(Collectors.toList());
QueryWrapper<BaseTrademark> queryWrapper = new QueryWrapper<>();
        queryWrapper.notIn("id",collectIds);
        List<BaseTrademark> baseTrademarkList = baseTrademarkMapper.selectList(queryWrapper);
        return baseTrademarkList;
    }

    @Override
    public void save(CategoryTrademarkVo categoryTrademarkVo) {

//获取品牌id集合
        List<Long> trademarkIdList = categoryTrademarkVo.getTrademarkIdList();

        List<BaseCategoryTrademark> baseCategoryTrademarks = trademarkIdList.stream().map(trademarkId -> {
            BaseCategoryTrademark baseCategoryTrademark = new BaseCategoryTrademark();
            baseCategoryTrademark.setCategory3Id(categoryTrademarkVo.getCategory3Id());
            baseCategoryTrademark.setTrademarkId(trademarkId);
            return baseCategoryTrademark;
        }).collect(Collectors.toList());

        this.saveBatch(baseCategoryTrademarks);

    }
}
