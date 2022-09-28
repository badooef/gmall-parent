package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//@CrossOrigin
@RequestMapping("/admin/product/baseTrademark")
@RestController
public class BaseTradermarkController {

    @Autowired
    private BaseTrademarkService baseTrademarkService;


    @GetMapping("/{page}/{limit}")
    public Result getBaseTraderMarkPage(@PathVariable Long page,
                                        @PathVariable Long limit
                                        ){

        Page<BaseTrademark> baseTrademarkPage = new Page<>(page, limit);
        IPage<BaseTrademark> baseTrademarkIPage = baseTrademarkService.getBaseTrademark(baseTrademarkPage);

return Result.ok(baseTrademarkIPage);
    }

    @PostMapping("/save")
public Result save(@RequestBody BaseTrademark baseTrademark){

        baseTrademarkService.save(baseTrademark);
        return Result.ok();
    }
@PutMapping("/update")
public Result update(@RequestBody BaseTrademark baseTrademark){

        baseTrademarkService.updateById(baseTrademark);
        return Result.ok();
}
@GetMapping("/get/{id}")
    public Result get(@PathVariable Long id){

    BaseTrademark baseTrademark = baseTrademarkService.getById(id);
    return Result.ok(baseTrademark);
}
@DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable Long id){
        baseTrademarkService.removeById(id);
        return Result.ok();
}





}
