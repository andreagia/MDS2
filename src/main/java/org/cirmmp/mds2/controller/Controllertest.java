package org.cirmmp.mds2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by andrea on 27/04/2017.
 */
@Controller
public class Controllertest {

    @CrossOrigin
    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String rmShell(){
        return "helloworld";
    }


}