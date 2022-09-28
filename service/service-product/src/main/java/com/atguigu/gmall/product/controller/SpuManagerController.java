package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseSaleAttr;
import com.atguigu.gmall.model.product.SpuImage;
import com.atguigu.gmall.model.product.SpuInfo;
import com.atguigu.gmall.product.service.ManagerService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/admin/product")
public class SpuManagerController {


    @Autowired
    public ManagerService managerService;

    @GetMapping("/spuImageList/{spuId}")
    public Result spuImageList(@PathVariable Long spuId) {

List<SpuImage> spuImageList = managerService.spuImageList(spuId);

return Result.ok(spuImageList);
    }



    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuInfo spuInfo) {


        managerService.saveSpuInfo(spuInfo);

return Result.ok();
    }





    /**
     * 获取销售属性
     * @return
     */
    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList() {

    List<BaseSaleAttr> baseSaleAttrList =  managerService.baseSaleAttrList();
        return Result.ok(baseSaleAttrList);
    }





    @GetMapping("/{page}/{limit}")
public Result getSpuInfoPage(@PathVariable Long page,
                             @PathVariable Long limit,
                             SpuInfo spuInfo){
//封装参数
        Page<SpuInfo> infoPage = new Page<>(page,limit);

//结果
IPage<SpuInfo> infoIPage = managerService.getSpuInfo(spuInfo,infoPage);

return Result.ok(infoIPage);
    }







}
