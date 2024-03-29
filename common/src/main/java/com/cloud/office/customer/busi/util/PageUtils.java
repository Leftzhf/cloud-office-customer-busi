package com.cloud.office.customer.busi.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cloud.office.customer.busi.vo.PageVo;
import com.cloud.office.customer.busi.vo.ResultVo;

import java.util.List;

/**
 * 分页工具类
 *
 */
public class PageUtils {

    public static <T> PageVo<T> getPageVo(IPage<T> page) {
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setList(page.getRecords());
        pageVo.setTotalCount(page.getTotal());
        pageVo.setPageSize(page.getSize());
        pageVo.setCurrentPage(page.getCurrent());
        pageVo.setTotalPage(page.getPages());
        return pageVo;
    }

    public static <T,E> PageVo<T> getPageVo(IPage<E> page, List<T> list) {
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setList(list);
        pageVo.setTotalCount(page.getTotal());
        pageVo.setPageSize(page.getSize());
        pageVo.setCurrentPage(page.getCurrent());
        pageVo.setTotalPage(page.getPages());
        return pageVo;
    }

    public static <T> PageVo<T> getPageVo(List<T> list, long currentPage, long totalPage, long pageSize, long totalCount) {
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setList(list);
        pageVo.setTotalCount(totalCount);
        pageVo.setPageSize(pageSize);
        pageVo.setCurrentPage(currentPage);
        pageVo.setTotalPage(totalPage);
        return pageVo;
    }

    public static <T> ResultVo getPageResult(IPage<T> page) {
        return ResultVo.success(getPageVo(page));
    }

}
