/*
 * This file is part of cruzeira and it's licensed under the project terms.
 */
package org.cruzeira.tests;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Callable;

@Controller
public class TestController {

    @RequestMapping("/simple")
    public @ResponseBody String simple() {
        return "simple";
    }

    @RequestMapping("/asyncSimple")
    public @ResponseBody Callable<String> asyncSimple() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "asyncSimple";
            }
        };
    }

    @RequestMapping("/asyncRuntimeException")
    public Callable<String> asyncRuntimeException() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new RuntimeException("On purpose");
            }
        };
    }

    @RequestMapping("/asyncException")
    public Callable<String> asyncException() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new Exception("On purpose");
            }
        };
    }

    @RequestMapping("/runtimeException")
    public String runtimeException() {
        throw new RuntimeException("On purpose");
    }

    @RequestMapping("/exception")
    public String exception() throws Exception {
        throw new Exception("On purpose");
    }

    @RequestMapping("/error")
    public void error(HttpServletResponse response) {
        try {
            response.sendError(501, "On purpose: Not Implemented");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/printWriter")
    public void printWriter(HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.append("Using print writer response");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
