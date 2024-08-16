package com.o4.mobility.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * This Banner class prints Owner name when context is refreshed
 *
 * <p>Here the expectation is whenever Application context is initialized or refreshed
 * A handler function will be called which will print simple name</p>
 *
 * @author M. Mazhar Hassan
 * @since 1.0
 * @see ApplicationListener
 */

@Slf4j
@Component
public class StartupBanner implements ApplicationListener<ContextRefreshedEvent>
{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

    log.error("\n\n" +
            ".___  ___.      ___      ________   __    __       ___      .______      \n" +
            "|   \\/   |     /   \\    |       /  |  |  |  |     /   \\     |   _  \\     \n" +
            "|  \\  /  |    /  ^  \\   `---/  /   |  |__|  |    /  ^  \\    |  |_)  |    \n" +
            "|  |\\/|  |   /  /_\\  \\     /  /    |   __   |   /  /_\\  \\   |      /     \n" +
            "|  |  |  |  /  _____  \\   /  /----.|  |  |  |  /  _____  \\  |  |\\  \\----.\n" +
            "|__|  |__| /__/     \\__\\ /________||__|  |__| /__/     \\__\\ | _| `._____|\n" +
            "                                                                         \n\n" +
            "************************(Application Context Refreshed)*************************\n\n\n");
    }
}