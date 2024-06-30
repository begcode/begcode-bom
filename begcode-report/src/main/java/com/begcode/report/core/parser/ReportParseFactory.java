package com.begcode.report.core.parser;

import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class ReportParseFactory {

    private static Map<String, Parser> parseMap = new HashMap<>();

    /**
     * 注册解析器
     *
     * @param parser
     */
    public void addParse(Parser parser) {
        if (parser == null || StringUtils.isEmpty(parser.getName())) {
            return;
        }
        ReportParseFactory.parseMap.putIfAbsent(parser.getName(), parser);
    }

    /**
     * 获取解析器
     *
     * @param name
     * @return
     */
    public Parser getParse(String name) {
        return ReportParseFactory.parseMap.get(name);
    }
}
