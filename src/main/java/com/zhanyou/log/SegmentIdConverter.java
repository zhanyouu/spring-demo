/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.zhanyou.log;

import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.pattern.ConverterKeys;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

/**
 * {@link SegmentIdConverter} is a log4j2 plugin, by annotation as {@link Plugin}. It convert the pattern key: segmentId.
 * Use '%segmentId' in log4j2's config. '%segmentId' will output as SID:xxxx
 * <p>
 */
@Plugin(name = "SegmentIdConverter", category = "Converter")
@ConverterKeys({"segmentId"})
public class SegmentIdConverter extends LogEventPatternConverter {

    /**
     * Constructs an instance of LoggingEventPatternConverter.
     *
     * @param name  name of converter.
     * @param style CSS style for output.
     */
    protected SegmentIdConverter(String name, String style) {
        super(name, style);
    }

    public static SegmentIdConverter newInstance(String[] options) {
        return new SegmentIdConverter("segmentId", "segmentId");
    }

    @Override
    public void format(LogEvent event, StringBuilder toAppendTo) {
        toAppendTo.append(TraceContext.segmentId());
    }

}
