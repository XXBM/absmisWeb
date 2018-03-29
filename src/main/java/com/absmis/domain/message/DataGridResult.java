package com.absmis.domain.message;

import java.util.List;

/**
 * @Author: LihuaHuang
 * @Description:
 * @Date: Created in 14:57 2017/9/7.
 * @Modified by:
 */
public class DataGridResult {
    Long total;    // 总的记录数
    List<?> rows;  // 数据集

    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
    public List<?> getRows() {
        return rows;
    }
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

}
