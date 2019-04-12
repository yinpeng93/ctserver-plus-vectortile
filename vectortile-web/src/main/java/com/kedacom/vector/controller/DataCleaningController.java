package com.kedacom.vector.controller;

import com.baomidou.mybatisplus.mapper.Condition;
import com.kedacom.common.response.ResponseMessage;
import com.kedacom.vector.entity.Province;
import com.kedacom.vector.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kedacom.common.contants.VectortileContants.*;

/**
 * @Auther: YinPeng
 * @Date: 2019/3/22 0022 09:50
 * @Description: 数据清洗controller
 */
@RestController
@RequestMapping("/dataclean")
@Slf4j
public class DataCleaningController {
    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private IHamletService hamletService;

    @Autowired
    private IPoiService poiService;

    @Autowired
    private IRoadService roadService;

    @Autowired
    private ITextService textService;

    @Autowired
    private IBackgroundPolygonService backgroundPolygonService;

    @Autowired
    private IDivisionService divisionService;

    public static Map<String,String> provinceMap = new HashMap<>();

    /**
     * 整理每个省的数据
     * @param pinYin 省份拼音
     * @return
     */
    @RequestMapping("/sortData")
    public ResponseMessage sortData(String pinYin){

        List<Province> provinceList = null;
        Long start = System.currentTimeMillis();
        try {
            provinceList = provinceService.selectList(Condition.create());
        } catch (Exception e) {
            log.error("查询省份信息失败！",e);
            return ResponseMessage.error("查询省份信息失败！");
        }
        provinceList.forEach(province -> provinceMap.put(province.getPinyin(),province.getAdminId().substring(0,2)));


        Province province = null;
        try {
            province = provinceService.queryProvinceByPinyin(pinYin);
        } catch (Exception e) {
            log.error("根据拼音查询省份信息失败！",e);
            return ResponseMessage.error("根据拼音查询省份信息失败！");
        }
        List<String> provinceNames = Arrays.asList(province.getRelationNames().split(","));

        try {
            sortHamletData(pinYin,provinceNames);
        } catch (Exception e) {
            log.error("整理" + pinYin + "：hamlet数据失败！",e);
            return ResponseMessage.error("整理" + pinYin + "：hamlet数据失败！",e);
        }

        try {
            sortPoiData(pinYin,provinceNames);
        } catch (Exception e) {
            log.error("整理" + pinYin + "：poi数据失败！",e);
            return ResponseMessage.error("整理" + pinYin + "：poi数据失败！",e);
        }

        try {
            sortRoadData(pinYin,provinceNames);
        } catch (Exception e) {
            log.error("整理" + pinYin + "：road数据失败！",e);
            return ResponseMessage.error("整理" + pinYin + "：road数据失败！",e);
        }

        try {
            sortTextData(pinYin,provinceNames);
        } catch (Exception e) {
            log.error("整理" + pinYin + "：road数据失败！",e);
            return ResponseMessage.error("整理" + pinYin + "：road数据失败！",e);
        }

        try {
            sortBackgroundPolygon(pinYin,provinceNames);
        } catch (Exception e) {
            log.error("整理" + pinYin + "：background数据失败！",e);
            return ResponseMessage.error("整理" + pinYin + "：background数据失败！",e);
        }

        try {
            sortDivision(pinYin,provinceNames);
        } catch (Exception e) {
            log.error("整理" + pinYin + "：division数据失败！",e);
            return ResponseMessage.error("整理" + pinYin + "：division数据失败！",e);
        }
        Long end = System.currentTimeMillis();
        log.info("整理" + pinYin + "数据总耗时：" + (end - start)/1000.0 + "s");
        return ResponseMessage.ok("数据整理成功！");
    }

    private void sortDivision(String pinYin, List<String> provinceNames) {
        Long start = System.currentTimeMillis();
        provinceNames.forEach(provinceName -> {
            divisionService.insertDivisionList(provinceName + DIVISION_SUFFIX,pinYin + DIVISION_SUFFIX,provinceMap.get(pinYin));
            divisionService.deleteDivisionList(provinceName + DIVISION_SUFFIX,provinceMap.get(pinYin));
            divisionService.insertDivisionList(pinYin + DIVISION_SUFFIX,provinceName + DIVISION_SUFFIX,provinceMap.get(provinceName));
            divisionService.deleteDivisionList(pinYin + DIVISION_SUFFIX,provinceMap.get(provinceName));
        });
        Long end = System.currentTimeMillis();
        log.info("整理" + pinYin + ":division数据，耗时" + (end - start)/1000.0 + "s");
    }

    private void sortBackgroundPolygon(String pinYin, List<String> provinceNames) {
        Long start = System.currentTimeMillis();
        provinceNames.forEach(provinceName -> {
            backgroundPolygonService.insertBackgroundPolygonList(provinceName + BACKGROUND_POLYGON_SUFFIX,pinYin + BACKGROUND_POLYGON_SUFFIX,provinceMap.get(pinYin));
            backgroundPolygonService.deleteBackgroundPolygonList(provinceName + BACKGROUND_POLYGON_SUFFIX,provinceMap.get(pinYin));
            backgroundPolygonService.insertBackgroundPolygonList(pinYin + BACKGROUND_POLYGON_SUFFIX,provinceName + BACKGROUND_POLYGON_SUFFIX,provinceMap.get(provinceName));
            backgroundPolygonService.deleteBackgroundPolygonList(pinYin + BACKGROUND_POLYGON_SUFFIX,provinceMap.get(provinceName));
        });
        Long end = System.currentTimeMillis();
        log.info("整理" + pinYin + ":backgroundPolygon数据，耗时" + (end - start)/1000.0 + "s");
    }

    private void sortTextData(String pinYin, List<String> provinceNames) {
        Long start = System.currentTimeMillis();
        provinceNames.forEach(provinceName -> {
            textService.insertTextList(provinceName + TEXT_SUFFIX,pinYin + TEXT_SUFFIX,provinceMap.get(pinYin));
            textService.deleteTextList(provinceName + TEXT_SUFFIX,provinceMap.get(pinYin));
            textService.insertTextList(pinYin + TEXT_SUFFIX,provinceName + TEXT_SUFFIX,provinceMap.get(provinceName));
            textService.deleteTextList(pinYin + TEXT_SUFFIX,provinceMap.get(provinceName));
        });
        Long end = System.currentTimeMillis();
        log.info("整理" + pinYin + ":text数据，耗时" + (end - start)/1000.0 + "s");
    }

    private void sortRoadData(String pinYin, List<String> provinceNames) {
        Long start = System.currentTimeMillis();
        provinceNames.forEach(provinceName ->{
            roadService.insertRoadList(provinceName + ROAD_SUFFIX,pinYin + ROAD_SUFFIX,provinceMap.get(pinYin));
            roadService.deleteRoadList(provinceName + ROAD_SUFFIX,provinceMap.get(provinceName),provinceMap.get(pinYin));
            roadService.insertRoadList(pinYin + ROAD_SUFFIX,provinceName + ROAD_SUFFIX,provinceMap.get(provinceName));
            roadService.deleteRoadList(pinYin + ROAD_SUFFIX,provinceMap.get(pinYin),provinceMap.get(provinceName));
        });
        Long end = System.currentTimeMillis();
        log.info("整理" + pinYin + ":road数据，耗时" + (end - start)/1000.0 + "s");
    }

    /**
     * 整理poi数据
     * @param pinYin
     * @param provinceNames
     */
    private void sortPoiData(String pinYin, List<String> provinceNames) {
        Long start = System.currentTimeMillis();
        provinceNames.forEach(provinceName -> {
            poiService.insertPoiList(provinceName + POI_SUFFIX,pinYin + POI_SUFFIX,provinceMap.get(pinYin));
            poiService.deletePoiList(provinceName + POI_SUFFIX,provinceMap.get(pinYin));
            poiService.insertPoiList(pinYin + POI_SUFFIX,provinceName + POI_SUFFIX,provinceMap.get(provinceName));
            poiService.deletePoiList(pinYin + POI_SUFFIX,provinceMap.get(provinceName));
        });
        Long end = System.currentTimeMillis();
        log.info("整理" + pinYin + ":poi数据，耗时" + (end - start)/1000.0 + "s");
    }

    /**
     * 整理hamlet数据
     * @param pinYin
     * @param provinceNames
     */
    private void sortHamletData(String pinYin, List<String> provinceNames) {
        Long start = System.currentTimeMillis();
        provinceNames.forEach(provinceName -> {
            hamletService.insertHamletList(provinceName + HAMLET_SUFFIX,pinYin + HAMLET_SUFFIX,provinceMap.get(pinYin));
            hamletService.deleteHamletList(provinceName + HAMLET_SUFFIX,provinceMap.get(pinYin));
            hamletService.insertHamletList(pinYin + HAMLET_SUFFIX,provinceName + HAMLET_SUFFIX,provinceMap.get(provinceName));
            hamletService.deleteHamletList(pinYin + HAMLET_SUFFIX,provinceMap.get(provinceName));
        });
        Long end = System.currentTimeMillis();
        log.info("整理" + pinYin + ":hamlet数据，耗时" + (end - start)/1000.0 + "s");
    }
}
