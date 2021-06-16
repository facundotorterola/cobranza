package com.uy.cobranza.controller;

import com.uy.cobranza.dao.StatisticsDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Statistics;

import com.uy.cobranza.service.statistics.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Statistics> list() {
        return statisticsService.listStatistics();
    }

    @RequestMapping(value = "/calledMethods/{dateString}", method = RequestMethod.GET)
    public List<StatisticsDao.StatisticsCallsMethodDto> listCalledMethods(@PathVariable("dateString") String dateString) throws BusinessException {
        return statisticsService.listCalledMethods(dateString);
    }

    @RequestMapping(value = "/calledMethod/most/{dateString}", method = RequestMethod.GET)
    public StatisticsDao.StatisticsCallsMethodDto getTheMostCalledMethod(@PathVariable("dateString") String dateString) throws BusinessException {
        return statisticsService.getTheMostCalledMethod(dateString);
    }


    @RequestMapping(value = "/calledMethod/least/{dateString}", method = RequestMethod.GET)
    public StatisticsDao.StatisticsCallsMethodDto getTheLeastCalledMethod(@PathVariable("dateString") String dateString) throws BusinessException {
        return statisticsService.getTheLeastCalledMethod(dateString);
    }



    @RequestMapping(value = "/runTimeAvg/{dateString}", method = RequestMethod.GET)
    public List<StatisticsDao.StatisticsRunTimeAvgDto> listRunTimeAvg(@PathVariable("dateString") String dateString) throws BusinessException {
        return statisticsService.listRunTimeAvg(dateString);
    }

    @RequestMapping(value = "/runTimeAvg/fastest/{dateString}", method = RequestMethod.GET)
    public StatisticsDao.StatisticsRunTimeAvgDto getFastestRunTimeAvg(@PathVariable("dateString") String dateString) throws BusinessException {
        return statisticsService.getFastestRunTimeAvg(dateString);
    }


    @RequestMapping(value = "/runTimeAvg/slowest/{dateString}", method = RequestMethod.GET)
    public StatisticsDao.StatisticsRunTimeAvgDto getSlowerRunTimeAvg(@PathVariable("dateString") String dateString) throws BusinessException {
        return statisticsService.getSlowerRunTimeAvg(dateString);
    }


}