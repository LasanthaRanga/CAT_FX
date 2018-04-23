/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modle;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;

import java.util.logging.Logger;

/**
 *
 * @author RM.LasanthaRanga@gmail.com
 */
public class MyLogger {

    // static Logger logger = Logger.getLogger(MyLogger.class.getName());
//    public static void main(String[] args) {
//        try {
//            LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
//        } catch (SecurityException | IOException e1) {
//            e1.printStackTrace();
//        }
//        logger.setLevel(Level.FINE);
//        logger.addHandler(new ConsoleHandler());
//        //adding custom handler
//        logger.addHandler(new MyHandler());
//        try {
//            //FileHandler file name with max size and number of log files limit
//            Handler fileHandler = new FileHandler("logger.log", 100000, 10);
//            fileHandler.setFormatter(new MyFormatter());
//            //setting custom filter for FileHandler
//            fileHandler.setFilter(new MyFilter());
//            logger.addHandler(fileHandler);
//
//            for (int i = 0; i < 1000; i++) {
//                //logging messages
//                logger.log(Level.INFO, "Msg" + i);
//            }
//            logger.log(Level.CONFIG, "Config data");
//        } catch (SecurityException | IOException e) {
//            e.printStackTrace();
//        }
//    }
    public static void loggerWrite(Logger logger, Level level, String message) {
        
        logger.setLevel(level);
        logger.addHandler(new ConsoleHandler());
        logger.addHandler(new MyHandler());
        try {
            Handler fileHandler = new FileHandler("C:\\cat\\log\\CAT LOG.log", 100000, 1);
            fileHandler.setFormatter(new MyFormatter());
            fileHandler.setFilter(new MyFilter());
            logger.addHandler(fileHandler);
            logger.log(level, message);
        } catch (IOException | SecurityException e) {
            e.printStackTrace();
        }
    }

}
