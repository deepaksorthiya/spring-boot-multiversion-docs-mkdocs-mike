package com.example;

import com.example.model.Post;
import com.example.repository.PostRepository;
import com.example.service.RestApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
@Async
@Order(1)
public class InitialDataLoader implements ApplicationRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(InitialDataLoader.class);

    private final PostRepository postRepository;
    private final RestApiService restApiService;

    public InitialDataLoader(PostRepository postRepository, RestApiService restApiService) {
        this.postRepository = postRepository;
        this.restApiService = restApiService;
    }

    @Override
    public void run(ApplicationArguments args) throws ExecutionException, InterruptedException {
        LOGGER.info("InitialDataLoader Running in thread: {}", Thread.currentThread());
        for (int i = 0; i < 10; i++) {
            Post post = new Post("title" + i, "content" + i);
            postRepository.save(post);
        }
        System.out.println("########################");
        restApiService.asyncWithCompletableFuture();
    }
}
