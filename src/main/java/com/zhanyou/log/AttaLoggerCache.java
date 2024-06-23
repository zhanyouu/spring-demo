package com.zhanyou.log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 */
class AttaLoggerCache {

    public static final AttaLoggerCache INSTANCE = new AttaLoggerCache();

    private AttaLoggerCache() {

    }

    private Map<AttaLogger, LogFormatter> cache = new ConcurrentHashMap<>();

    public LogFormatter getLogger(AttaLogger logger) throws Exception {

        LogFormatter entry = cache.get(logger);
        if (entry != null) {
            return entry;
        }

        entry = LogFormatter.newLogger(logger.name(), logger.value());

        cache.put(logger, entry);

        return entry;
    }

}
