package com.cloud.office.customer.busi;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * mybatis-plus 代码生成器
 *
 * @author leftleft
 * @date 2023/04/03
 */
public class Generator {

    public static void main(String[] args) {
        generate();
    }
    private static void generate() {
        FastAutoGenerator.create("jdbc:mysql://127.0.0.1:3306/customer?characterEncoding=utf-8&useSSL=false", "root", "123456")
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author("leftleft").fileOverride().enableSwagger().outputDir(scanner.apply("请输入路径(绝对路径)？")))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent(scanner.apply("请输入生成的包名？")))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("gmt_create", FieldFill.INSERT),
                                new Column("gmt_modify", FieldFill.INSERT),
                                new Column("is_delete", FieldFill.INSERT)
                        ).build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();

    }
// 处理 all 情况
        protected static List<String> getTables(String tables) {
            return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
        }
    }
