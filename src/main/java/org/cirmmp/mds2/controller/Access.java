package org.cirmmp.mds2.controller;


import com.google.gson.Gson;
import org.cirmmp.mds2.chart.FileS2;
import org.cirmmp.mds2.chart.arrayBean;
import org.cirmmp.mds2.services.RunAnalysis;
import org.cirmmp.mds2.services.RunAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


@Controller
@RequestMapping("/welcome")
public class Access  {

    Logger logger = LoggerFactory.getLogger(Access.class);

    @Autowired
    RunAnalysis runAnalysis;


    @Autowired
    RunAsync runAsync;

    @Autowired
    ServletContext context;

    @RequestMapping("helloAsync")
    public Callable<String> sayHelloAsync() {
        logger.info("Entering controller");

        Callable<String> asyncTask = new Callable<String>() {

            @Override
            public String call() throws Exception {
                return runAsync.doSlowWork("traceroute www.google.com");
            }
        };

        logger.info("Leaving  controller");
        return asyncTask;
    }


    @RequestMapping(value="runShell",method = RequestMethod.GET)
    public ModelAndView runShell() throws Exception {


        String fullPath = context.getRealPath("/WEB-INF/classes/script/create_bv_inpt.py");
        String path = fullPath.substring(0, fullPath.lastIndexOf("/")+1);
        String webinf = fullPath.substring(0, fullPath.lastIndexOf("WEB-INF")+7);

        logger.info("SONO IN");
        logger.info("/bin/bash " + path + "runcpptraj.sh" );
        logger.info(webinf);


        //Future<String> page1 = runAnalysis.executeCommand("traceroute www.google.com");
        Future<String> page1 = runAnalysis.executeCommand("/bin/bash " + path + "runcpptraj.sh" );
        logger.info("execute");

         String risp = "pippo";

        if (page1.isDone()) {
            risp = page1.get(1L, TimeUnit.SECONDS);
            logger.info("FINITO");
        } else {
            logger.info("ASPETTO");
            risp = "sta' girando";
        }

        logger.info(risp);

        logger.info("ESCO");

        ModelAndView model = new ModelAndView("helloworld");
        model.addObject("msg", risp);

        return model;
    }

    @RequestMapping(value = "/dashboard")
    public @ResponseBody
    String ShowUserDetails()  {
        Gson gson = new Gson();

        FileS2 s2 = new FileS2();

        try {
            FileReader filein = new FileReader("/tmp/MDS2/tmp/ired_res.json");
            s2 = gson.fromJson(filein, FileS2.class);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex2) {
            ex2.printStackTrace();
        }
//        arrayBean o1 = new arrayBean();
//        o1.setPeriod();
//        o1.setIpad();
//        o1.setIphone();
//        o1.setItouch();

        String jsonString = gson.toJson(s2);
        logger.info("JSON");
        logger.info(jsonString);


        return gson.toJson(s2);
    }

}