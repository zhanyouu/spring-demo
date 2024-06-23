package com.zhanyou.log;


import groovy.text.SimpleTemplateEngine;
import groovy.text.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class LogFormatter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFormatter.class);


    private final Logger log;

    private final Template template;

    private LogFormatter(Logger log, Template template){
        this.log = log;
        this.template = template;
    }


    public static LogFormatter newLogger(String name){
        List<String> value = LoggerConf.getValue(name);
        if(value == null || value.isEmpty()){
            return newLogger(name, new String[0]);
        }

        return newLogger(name, value.toArray(new String[0]));
    }

    static LogFormatter newLogger(String name, String... value){
        Logger log = LoggerFactory.getLogger("atta." + name);

        if(value == null || value.length == 0){ //it's not defined.
            LOGGER.info("Creating LogFormatter {} without template", log.getName());
            return new LogFormatter(log, null);
        }

        Template template = null;
        try {
            template = newTemplate(value);
            LOGGER.debug("Creating LogFormatter {} with template", log.getName());
            return new LogFormatter(log, template);
        } catch (Exception e) {
            LOGGER.error("Failed to resolve log template for logger {}", name, e);
            return new LogFormatter(log, null);
        }
    }




    private static Template newTemplate(String[] value) throws Exception {
        int length = 0;
        for(String e : value){
            length += e.length();
        }

        StringBuilder text = new StringBuilder(length + value.length);

        for(String e : value){
            text.append(e).append(",");
        }

        text.setLength(text.length() - 1);
        SimpleTemplateEngine templateEngine = new SimpleTemplateEngine();
        return templateEngine.createTemplate(text.toString());
    }

    private String make(Map<String, ?> ctx, boolean succeeded, long timeElapsed1, long timeElapsed2){

        String message = template == null ? "" : template.make(ctx).toString();

        StringBuilder sb = new StringBuilder(message.length() + 128);

        //appendTracingInfo(sb);

        appendResultInfo(sb, succeeded, timeElapsed1, timeElapsed2);
        // construct biz log template
        sb.append("(").append(message).append(")");

        return sb.toString();
    }

    /**
     * Example: (Y,5ms)
     */
    private static void appendResultInfo(StringBuilder sb, boolean succeeded, long timeElapsed1, long timeElapsed2){
        sb.append("(");
        sb.append(BooleanCode.getByValue(succeeded).code).append(",").append(timeElapsed1).append("ms,").append(timeElapsed2);
        sb.append("ms)");
    }


    void log(Map<String, ?> ctx, boolean succeeded, long timeElapsed1, long timeElapsed2){
        log.info(make(ctx, succeeded, timeElapsed1, timeElapsed2));
    }

    public void log(Object... args){
        log(true, 0, args);
    }

    public void log(boolean succeeded, long timeElapsed, Object... args){
        if (args == null || args.length == 0) {
            log(Collections.emptyMap(), succeeded, timeElapsed);
            return;
        }

        Map<String, Object> ctx = new HashMap<>();

//        if (args.length == 1) {
//            ctx.put("param", args[0]);
//            log(ctx, succeeded, timeElapsed);
//            return;
//        }

        ctx.put("param", args);
        log(ctx, succeeded, timeElapsed);
    }
}
