package ru.dtimofeev.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;
import ru.dtimofeev.springapp.domain.Rice;

import java.util.concurrent.ForkJoinPool;


@IntegrationComponentScan
@ComponentScan
public class SpringappApplication {

    public static void main(String[] args) throws InterruptedException {

        ApplicationContext context = SpringApplication.run(SpringappApplication.class, args);

        DecisionMaker dm = context.getBean(DecisionMaker.class);

        ForkJoinPool pool = ForkJoinPool.commonPool();

        while (true) {
            Thread.sleep(10000);

            pool.execute(() -> {
                Rice rice = getRice();
                dm.cook(rice);
                System.out.println("Cleaning the kitchen ... ");
                System.out.println();
            });

        }

    }

    private static Rice getRice() {
        return new Rice(Math.random() < 0.5);
    }

}
