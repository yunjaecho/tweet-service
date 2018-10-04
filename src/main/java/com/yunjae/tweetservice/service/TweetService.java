package com.yunjae.tweetservice.service;

import akka.japi.function.Function;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Source;
import akka.stream.scaladsl.Sink;
import com.yunjae.tweetservice.entity.HashTag;
import com.yunjae.tweetservice.entity.Tweet;
import com.yunjae.tweetservice.repository.TweetRepository;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TweetService {
    private final TweetRepository repository;
    private final ActorMaterializer actorMaterializer;

    public TweetService(TweetRepository repository, ActorMaterializer actorMaterializer) {
        this.repository = repository;
        this.actorMaterializer = actorMaterializer;
    }

    public Publisher<Tweet> getAllTweets() {
        return repository.findAll();
    }

    public Publisher<HashTag> getAllHashTags() {
        return Source
                .fromPublisher(getAllTweets())
                .map(Tweet::getHashTags)
                .reduce(this::join)
                .mapConcat((Function<Set<HashTag>, ? extends Iterable<HashTag>>) hashTags -> hashTags)
                .runWith(Sink.asPublisher(true), this.actorMaterializer);
    }

    private <T> Set<T> join(Set<T> a, Set<T> b) {
        Set<T> set = new HashSet<>();
        set.addAll(a);
        set.addAll(b);
        return set;
    }


}
