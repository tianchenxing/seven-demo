package com.tong.sevencore.service.impl;


import com.tong.sevencore.dto.CoalQualityWebDTO;

/**
 * @Author: SevenTian
 * @Description:
 * @Create_Date: 9:04 2019/6/26
 * @Modified_Author:
 * @Modified_Date:
 */
public class ThreadTest {
    public static void main(String[] args){
//        ThreadOne t1 = new ThreadOne();
//        ThreadOne t2 = new ThreadOne();
//        t1.start();
//        t2.start();
        RunThread r1 = new RunThread();
        Thread tr1 = new Thread(r1);
        Thread tr2 = new Thread(r1);
        tr1.start();
        tr2.start();

    }

}

class ThreadOne extends Thread{
public void run(){
    CoalQualityWebDTO coalQualityWebDTO = new CoalQualityWebDTO();
    System.out.println(coalQualityWebDTO.getCoalQualityProduceAnalysisDataDomain());
}
}

class RunThread implements Runnable{

    @Override
    public void run() {
        CoalQualityWebDTO coalQualityWebDTO = new CoalQualityWebDTO();
        System.out.println(coalQualityWebDTO.getCoalQualityProduceAnalysisDataDomain());
    }
}
