package com.yunjae.tweetservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

//@Table("hashtag")
@UserDefinedType("hashTag")
@Data
@AllArgsConstructor
public class HashTag {
    @PrimaryKey
    private String id;
}
