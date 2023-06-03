package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.domain.Book;
import org.springframework.stereotype.Repository;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}

