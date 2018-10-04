package com.yunjae.tweetservice.repository;

import com.yunjae.tweetservice.entity.Tweet;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;

public interface TweetRepository extends ReactiveCassandraRepository<Tweet, String> {
}
