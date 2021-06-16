package com.uy.cobranza.service.statistics;

import com.uy.cobranza.dao.StatisticsDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Statistics;

import java.util.List;

public interface StatisticsService {

    List<Statistics> listStatistics();

    List<StatisticsDao.StatisticsCallsMethodDto> listCalledMethods(String dateString) throws BusinessException;

    StatisticsDao.StatisticsCallsMethodDto getTheMostCalledMethod(String dateString)throws BusinessException;

    StatisticsDao.StatisticsCallsMethodDto getTheLeastCalledMethod(String dateString)throws BusinessException;


    List<StatisticsDao.StatisticsRunTimeAvgDto> listRunTimeAvg(String dateString)throws BusinessException;

    StatisticsDao.StatisticsRunTimeAvgDto getFastestRunTimeAvg(String dateString)throws BusinessException;

    StatisticsDao.StatisticsRunTimeAvgDto getSlowerRunTimeAvg(String dateString)throws BusinessException;

    void addStatistics(Statistics statistics);


}
