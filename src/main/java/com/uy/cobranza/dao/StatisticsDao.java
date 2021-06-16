package com.uy.cobranza.dao;

import com.uy.cobranza.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StatisticsDao  extends JpaRepository<Statistics, Integer> {



    @Query(value = "SELECT s.method_name as methodName, COUNT(s.method_name) as countOfCallsToMethod\n" +
            "FROM  statistics s \n" +
            "WHERE s.creation_date >= :since AND s.creation_date< :until\n" +
            "GROUP BY  methodName"
    , nativeQuery = true)
    List<StatisticsCallsMethodDto> listCalledMethods(@Param("since")String sinceDate ,@Param("until") String untilDate);

    @Query(value = "SELECT s.method_name as methodName, COUNT(s.method_name) as countOfCallsToMethod\n" +
            "FROM  statistics s \n" +
            "WHERE s.creation_date >= :since AND s.creation_date< :until\n" +
            "GROUP BY  methodName \n" +
            "ORDER BY countOfCallsToMethod DESC \n" +
            "LIMIT 1"
            , nativeQuery = true)
    StatisticsCallsMethodDto getTheMostCalledMethod(@Param("since")String sinceDate ,@Param("until") String untilDate);


    @Query(value = "SELECT s.method_name as methodName, COUNT(s.method_name) as countOfCallsToMethod\n" +
            "FROM  statistics s \n" +
            "WHERE s.creation_date >= :since AND s.creation_date< :until\n" +
            "GROUP BY  methodName \n" +
            "ORDER BY countOfCallsToMethod  \n" +
            "LIMIT 1"
            , nativeQuery = true)
    StatisticsCallsMethodDto getTheLeastCalledMethod(@Param("since")String sinceDate ,@Param("until") String untilDate);


    @Query(value = "SELECT s.method_name as methodName, AVG(s.run_time) as runTimeAvg\n" +
            "FROM  statistics s \n" +
            "WHERE s.creation_date >= :since AND s.creation_date< :until\n" +
            "GROUP BY  methodName "
            , nativeQuery = true)
    List<StatisticsRunTimeAvgDto> listRunTimeAvg(@Param("since")String sinceDate ,@Param("until") String untilDate);

    @Query(value ="SELECT s.method_name as methodName, AVG(s.run_time) as runTimeAvg\n" +
            "FROM  statistics s \n" +
            "WHERE s.creation_date >= :since AND s.creation_date< :until\n" +
            "GROUP BY  methodName \n" +
            "ORDER BY runTimeAvg   \n" +
            "LIMIT 1"
            , nativeQuery = true)
    StatisticsRunTimeAvgDto getFastestRunTimeAvg(@Param("since")String sinceDate ,@Param("until") String untilDate);



    @Query(value ="SELECT s.method_name as methodName, AVG(s.run_time) as runTimeAvg\n" +
            "FROM  statistics s \n" +
            "WHERE s.creation_date >= :since AND s.creation_date< :until\n" +
            "GROUP BY  methodName \n" +
            "ORDER BY runTimeAvg DESC  \n" +
            "LIMIT 1"
            , nativeQuery = true)
    StatisticsRunTimeAvgDto getSlowerRunTimeAvg(@Param("since")String sinceDate ,@Param("until") String untilDate);



    public static interface StatisticsCallsMethodDto {
        String getMethodName();
        Integer getCountOfCallsToMethod();
    }


    public static interface StatisticsRunTimeAvgDto {
        String getMethodName();
        Double getRunTimeAvg();
    }



}
