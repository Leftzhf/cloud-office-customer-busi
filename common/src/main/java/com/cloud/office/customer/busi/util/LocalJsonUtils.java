package com.cloud.office.customer.busi.util;

import cn.hutool.core.io.FileUtil;

import java.util.Objects;

/**
 * 本地json工具类
 *
 */
public class LocalJsonUtils {

    /**
     * 加载resources下的本地json
     * todo 后期集成到mongodb里
     *
     * @param name 文件名
     * @return json字符串
     */
    public static String loadLocalJson(String name) {
        String fileName = Objects.requireNonNull(LocalJsonUtils.class.getClassLoader().getResource(name)).getPath();
        return FileUtil.loadUtf8(fileName, reader -> {
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
            return json.toString();
        });
    }
}
