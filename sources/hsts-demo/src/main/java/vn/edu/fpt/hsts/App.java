/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 9/23/2015.
 */
package vn.edu.fpt.hsts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.edu.fpt.hsts.common.util.AnalyticDataTask;

import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
public class App {
    public static void main(String[] args) {
        TimerTask task = new AnalyticDataTask();
        Timer timer = new Timer();
        timer.schedule(task, 10000, 1000*60*60*24);
        SpringApplication.run(App.class, args);
    }
}