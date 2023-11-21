package com.report.common.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wl
 */
public class StrUtil extends cn.hutool.core.util.StrUtil {

    public static List<Integer> splitToIntList(String str, String separatorChar) {
        if (isBlank(str)) {
            return new ArrayList<>();
        }
        return Arrays.stream(str.split(separatorChar)).map(Integer::parseInt).collect(Collectors.toList());
    }
}
