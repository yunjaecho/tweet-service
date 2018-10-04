package com.yunjae.tweetservice.config;

import com.yunjae.tweetservice.entity.HashTag;
import com.yunjae.tweetservice.entity.Tweet;
import com.yunjae.tweetservice.service.TweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RouterConfigration {

    @Autowired
    private TweetService service;

    /*private final TweetService service;

    public RouterConfigration(TweetService service) {
        this.service = service;
    }*/

    @Bean
    RouterFunction<ServerResponse> routes() {
        return route(GET("/tweets"),req -> ok().body(service.getAllTweets(), Tweet.class))
                .andRoute(GET("/hashtags"), req -> ok().body(service.getAllHashTags(), HashTag.class));


    }
}
