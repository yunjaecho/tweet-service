package com.yunjae.tweetservice.entity;

import com.datastax.driver.core.DataType;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Table("tweet")
@Data
public class Tweet {
    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    private UUID id;

    private String text;

    private Author author;

    public Tweet(Author author, String text) {
        id = UUID.randomUUID();
        this.author = author;
        this.text = text;
    }


    public Set<HashTag> getHashTags() {
        return Arrays.stream(this.text.split(" "))
                .filter(t -> t.startsWith("#"))
                .map(word -> new HashTag(
                        word.replaceAll("[^#\\w+]", "")
                                .toLowerCase()))
                .collect(Collectors.toSet());
    }

}
