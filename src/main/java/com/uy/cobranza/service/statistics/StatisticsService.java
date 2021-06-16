package com.uy.cobranza.service.statistics;

import com.uy.cobranza.dao.StatisticsDao;
import com.uy.cobranza.exception.NegocioException;
import com.uy.cobranza.model.Statistics;

import java.util.Date;
import java.util.List;

public interface StatisticsService {

    List<Statistics> listStatistics();

    List<StatisticsDao.StatisticsCallsMethodDto> listCalledMethods(String dateString) throws NegocioException;

    StatisticsDao.StatisticsCallsMethodDto getTheMostCalledMethod(String dateString)throws NegocioException;

    StatisticsDao.StatisticsCallsMethodDto getTheLeastCalledMethod(String dateString)throws NegocioException;


    List<StatisticsDao.StatisticsRunTimeAvgDto> listRunTimeAvg(String dateString)throws NegocioException;

    StatisticsDao.StatisticsRunTimeAvgDto getFastestRunTimeAvg(String dateString)throws NegocioException;

    StatisticsDao.StatisticsRunTimeAvgDto getSlowerRunTimeAvg(String dateString)throws NegocioException;

    void addStatistics(Statistics statistics);


}
