package com.yunjae.tweetservice.runner;

import com.yunjae.tweetservice.entity.Author;
import com.yunjae.tweetservice.entity.Tweet;
import com.yunjae.tweetservice.repository.TweetRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
class TweetRunner implements ApplicationRunner {

    private final TweetRepository tweetRepository;

    TweetRunner(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {



        Author viktor = new Author("viktorklang");
        Author jboner = new Author("jboner");
        Author josh = new Author("starbuxman");

        Flux<Tweet> tweets = Flux.just(
                new Tweet(viktor, "woot, @Konrad will be talking #enterprise #integration done right!"),
                new Tweet(viktor, "#scala implicits can easily be used to model capabilities, but can they encode obligations easily?"),
                new Tweet(viktor, "this is so cool! #akka"),
                new Tweet(jboner, "cross data center replication of event sourced #akka actors is soon available (using #crdts and more!)"),
                new Tweet(josh, "a reminder: @SpringBoot lets you pair program with the #Spring team. #bootiful"),
                new Tweet(josh, "whatever your next platform is, don't build it yourself.\n\n" +
                        "Even companies with the $$ and the motivation to do it fail. A LOT. #bootiful")
        );
        this
                .tweetRepository
                .deleteAll()
                .thenMany(tweets.flatMap(tweetRepository::save))
                .thenMany(tweetRepository.findAll())
//					.subscribeOn(Schedulers.fromExecutor(Executors.newSingleThreadExecutor()))
                .subscribe(System.out::println);
    }
}
