package com.uy.cobranza.service.statistics;

import com.uy.cobranza.dao.StatisticsDao;
import com.uy.cobranza.exception.BusinessException;
import com.uy.cobranza.model.Statistics;
import com.uy.cobranza.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl  implements  StatisticsService{

    @Autowired
    private StatisticsDao statisticsDao;


    private Map<String, String > getDateAndTomorrowDateStr(String strDate) throws BusinessException {
        Date date = null;
        try {
            date = DateUtils.dateToString(strDate);
        } catch (ParseException e) {
            throw new BusinessException("Formato incorrecto de fecha "+strDate);
        }
        Date tomorrowFromDate = DateUtils.getTomorrowByDate(date);
        String tomorrowStr = DateUtils.formatDateWithoutHour(tomorrowFromDate);
        Map<String ,String> mapStringDate = new HashMap<>();
        mapStringDate.put("dateStr",strDate);
        mapStringDate.put("tomorrowDateStr",tomorrowStr);
        return mapStringDate;
    }
    @Override
    public List<Statistics> listStatistics() {
        return statisticsDao.findAll();
    }

    @Override
    public List<StatisticsDao.StatisticsCallsMethodDto> listCalledMethods(String  dateString) throws BusinessException {

        Map<String, String > mapStringDate = this.getDateAndTomorrowDateStr(dateString);
        return statisticsDao.listCalledMethods(mapStringDate.get("dateStr"),mapStringDate.get("tomorrowDateStr"));
    }

    @Override
    public StatisticsDao.StatisticsCallsMethodDto getTheMostCalledMethod(String dateString) throws BusinessException {
        Map<String, String > mapStringDate = this.getDateAndTomorrowDateStr(dateString);

        return statisticsDao.getTheMostCalledMethod(mapStringDate.get("dateStr"),mapStringDate.get("tomorrowDateStr"));
    }

    @Override
    public StatisticsDao.StatisticsCallsMethodDto getTheLeastCalledMethod(String dateString) throws BusinessException {
        Map<String, String > mapStringDate = this.getDateAndTomorrowDateStr(dateString);

        return statisticsDao.getTheLeastCalledMethod(mapStringDate.get("dateStr"),mapStringDate.get("tomorrowDateStr"));

    }

    @Override
    public List<StatisticsDao.StatisticsRunTimeAvgDto> listRunTimeAvg(String dateString) throws BusinessException {
        Map<String, String > mapStringDate = this.getDateAndTomorrowDateStr(dateString);

        return statisticsDao.listRunTimeAvg(mapStringDate.get("dateStr"),mapStringDate.get("tomorrowDateStr"));
    }

    @Override
    public StatisticsDao.StatisticsRunTimeAvgDto getFastestRunTimeAvg(String dateString) throws BusinessException {
        Map<String, String > mapStringDate = this.getDateAndTomorrowDateStr(dateString);

        return statisticsDao.getFastestRunTimeAvg(mapStringDate.get("dateStr"),mapStringDate.get("tomorrowDateStr"));
    }

    @Override
    public StatisticsDao.StatisticsRunTimeAvgDto getSlowerRunTimeAvg(String dateString) throws BusinessException {
        Map<String, String > mapStringDate = this.getDateAndTomorrowDateStr(dateString);

        return statisticsDao.getSlowerRunTimeAvg(mapStringDate.get("dateStr"),mapStringDate.get("tomorrowDateStr"));
    }


    @Override
    public void addStatistics(Statistics statistics) {
        statisticsDao.save(statistics);
    }
}
