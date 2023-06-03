package org.example.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
实体类
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("book")
public class Book {
    @TableId(type = IdType.AUTO)
    private int id;
    private String img;
    private String name;
    private String price;
}

