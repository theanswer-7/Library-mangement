package com.it.dao;

import com.it.pojo.Blog;
import org.apache.ibatis.annotations.Param;

public interface BlogMapper {
    // 嵌套结果映射
    Blog getBlogWithCommentByResult(@Param("bid") Integer bid);

    // 嵌套查询
    Blog getBlogWithCommentBySelect(@Param("bid") Integer bid);
}
