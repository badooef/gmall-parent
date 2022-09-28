package com.atguigu.gmall.product.controller;

import com.atguigu.gmall.common.result.Result;
import com.atguigu.gmall.model.product.BaseTrademark;
import com.atguigu.gmall.model.product.CategoryTrademarkVo;
import com.atguigu.gmall.product.service.BaseCategoryTrademarkService;
import com.atguigu.gmall.product.service.BaseTrademarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/admin/product/baseCategoryTrademark")
@RestController
public class BaseCategoryTradeMarkController {

    @Autowired
    private BaseCategoryTrademarkService baseCategoryTrademarkService;

    @PostMapping("/save")
public Result save(@RequestBody CategoryTrademarkVo categoryTrademarkVo){

        baseCategoryTrademarkService.save(categoryTrademarkVo);

        return Result.ok();

    }



    /**
     * 根据category3Id选取可选品牌列表
     * @param category3Id
     * @return
     */
    @GetMapping("/findCurrentTrademarkList/{category3Id}")
    public Result findCurrentTrademarkList(@PathVariable Long category3Id){
        List<BaseTrademark> baseTrademarkList = baseCategoryTrademarkService.findCurrentTrademarkList(category3Id);


        return Result.ok(baseTrademarkList);
    }


@GetMapping("/findTrademarkList/{category3Id}")
public Result findTrademarkList(@PathVariable Long category3Id){

List<BaseTrademark> baseTrademarkList = baseCategoryTrademarkService.findTrademarkList(category3Id);

return Result.ok(baseTrademarkList);
}

@DeleteMapping("/remove/{category3Id}/{trademarkId}")
public Result remove(@PathVariable Long category3Id,
                     @PathVariable Long trademarkId
                     ){

baseCategoryTrademarkService.remove(category3Id,trademarkId);


return Result.ok();
}
}
