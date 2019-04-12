package com.kedacom.vector.controller;

import com.kedacom.common.response.ResponseMessage;
import com.kedacom.vector.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/20 0020 13:48
 * @Description:
 */
@RestController
@RequestMapping("/province")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @RequestMapping("/list")
    public ResponseMessage provinceList(){
        return ResponseMessage.ok(provinceService.queryAllProvince());
    }
}
