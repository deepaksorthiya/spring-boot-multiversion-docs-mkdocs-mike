package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.LockSupport;

@Service
public class RestApiService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestApiService.class);

    private final TaskScheduler taskScheduler;
    private AsyncTaskExecutor applicationTaskExecutor;
    private ScheduledFuture<?> scheduledFuture;

    public RestApiService(TaskScheduler taskScheduler, AsyncTaskExecutor applicationTaskExecutor) {
        this.taskScheduler = taskScheduler;
        this.applicationTaskExecutor = applicationTaskExecutor;
    }

    @Async
    public void doAsyncTask() {
        // Simulate a long-running task
        LOGGER.info("Async task Running in thread: {}", Thread.currentThread());
        try {
            applicationTaskExecutor.execute(() -> LOGGER.info("Async Task executed In :: {}", Thread.currentThread()));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("Async task completed in thread: {}", Thread.currentThread());
    }

    @Async
    public void doScheduleAsyncTask() {
        // Simulate a long-running task
        LOGGER.info("Schedule Async task Running in thread: {}", Thread.currentThread());
        try {
            this.scheduledFuture = taskScheduler.scheduleAtFixedRate((() -> LOGGER.info("Schedule Task executed :: {}", Thread.currentThread())), Duration.ofSeconds(3));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        LOGGER.info("Schedule Async task completed in thread: {}", Thread.currentThread());
    }

    public void cancelScheduleAsyncTask() {
        if (scheduledFuture != null) {
            LOGGER.info("Async task Cancel Running in thread: {}", Thread.currentThread());
            scheduledFuture.cancel(true);
            LOGGER.info("Schedule Async task Cancel completed in thread: {}", Thread.currentThread());
        }
    }

    public void asyncWithCompletableFuture() throws ExecutionException, InterruptedException {
        Future<Long> future = applicationTaskExecutor.submit(() -> {
            LockSupport.parkNanos(5 * 1_000_000_000L);
            System.out.println("Current thread: " + Thread.currentThread());
            return Thread.currentThread().threadId();
        });

        System.out.println(future.get());

        // Use Spring's TaskScheduler to schedule a Runnable and set the result in a
        // CompletableFuture
        CompletableFuture<Integer> randomValueFuture = new CompletableFuture<>();
        Runnable randomTask = () -> {
            int randomValue = new Random().nextInt(100); // random value between 0-99
            System.out.println("Random value: " + randomValue);
            randomValueFuture.complete(randomValue);
        };
        taskScheduler.schedule(randomTask, Instant.now().plusSeconds(10));

        // Get the random value after execution
        System.out.println("Scheduled random value: " + randomValueFuture.get());
    }

    //@Scheduled(fixedRate = 2000)
    //@Async
    public void scheduledTask() {
        LOGGER.info("SchedulerTask started... {}", Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
