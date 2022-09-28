package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.SkuInfo;
import com.atguigu.gmall.model.product.SpuSaleAttr;
import com.atguigu.gmall.product.service.ManagerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
public class SkuManagerController {

    @Autowired
    private ManagerService managerService;

//上架
    @GetMapping("/onSale/{skuId}")
    public Result onSale(@PathVariable Long skuId) {

        managerService.onSale(skuId);

        return Result.ok();

    }

    @GetMapping("/cancelSale/{skuId}")
    public Result cancelSale(@PathVariable Long skuId) {

        managerService.cancelSale(skuId);

        return Result.ok();
    }


    @GetMapping("/list/{page}/{limit}")
    public Result skuListPage(@PathVariable Long page,
                              @PathVariable Long limit) {

//封装分页对象
        Page<SkuInfo> skuInfoPage = new Page<>(page, limit);
        IPage<SkuInfo> infoIPage = managerService.skuListPage(skuInfoPage);


        return Result.ok(infoIPage);

    }


    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuInfo skuInfo) {


        managerService.saveSkuInfo(skuInfo);


        return Result.ok();
    }


    /**
     * 根据spuId查询销售属性和销售属性值集合
     *
     * @return
     */
    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable Long spuId) {

        List<SpuSaleAttr> spuSaleAttrList = managerService.spuSaleAttrList(spuId);

        return Result.ok(spuSaleAttrList);
    }


}
