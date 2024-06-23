package com.zhanyou.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.util.*;


@SuppressWarnings("rawtypes")
public class LoggerConf {

    private static final Logger log = LoggerFactory.getLogger(LoggerConf.class);

    private static final String RESOURCE_PATTERN = "classpath*:META-INF/atta-logger.yml";

    private static Map<String, List<String>> loggerNames = new HashMap<>();

    static {

        Resource[] res = loadResources();

        if(res != null && res.length > 0){
            for(Resource r : res){
                Map<String, Object> yaml = parse(r);
                if(yaml == null){
                    continue;
                }

                for(Map.Entry<String, Object> e : yaml.entrySet()){
                    if(e.getValue() instanceof Collection){
                        putEntry(e.getKey(), (Collection)e.getValue());
                    } else {
                        log.error("Illegal Format - name: {}, file: {}", e.getKey(), r.getDescription());
                    }
                }
            }
        }
    }

    private LoggerConf() {
    }

    public static List<String> getValue(String name){
        return loggerNames.get(name);
    }

    private static Resource[] loadResources()  {

        log.info("Loading {}", RESOURCE_PATTERN);

        ResourcePatternResolver springResolver = new PathMatchingResourcePatternResolver(
                LoggerConf.class.getClassLoader());

        try {
            return springResolver.getResources(RESOURCE_PATTERN);
        } catch (Exception e) {
            log.error("Failed to load {}", RESOURCE_PATTERN, e);
            return null;
        }

    }

    private static void putEntry(String key, Collection value){
        log.info("Caching logging entry {}: {}", key, value);

        if(value == null){
            loggerNames.put(key, Collections.emptyList());
            return;
        }

        List<String> v = new ArrayList<>();
        for(Object e : value){
            v.add(e.toString());
        }
        Object existing = loggerNames.put(key, v);

        if(existing != null){
            log.warn("Existing logging entry {}: {} has been overwritten by {}", key, existing, v);
        }
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> parse(Resource r)  {
        log.info("Parsing {}", r.getDescription());
        Yaml yaml = new Yaml();
        try {
            return (Map)yaml.load(r.getInputStream());
        } catch (IOException e) {
            log.error("Failed to parse {}", r.getDescription(), e);
            return null;
        }
    }




}
