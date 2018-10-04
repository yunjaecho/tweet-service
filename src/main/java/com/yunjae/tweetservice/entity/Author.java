package com.yunjae.tweetservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

//@Table("author")
@UserDefinedType("author")
@Data
@AllArgsConstructor
public class Author {
    @PrimaryKey
    private String id;
}