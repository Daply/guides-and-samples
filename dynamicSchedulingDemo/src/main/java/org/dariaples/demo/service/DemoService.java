package org.dariaples.demo.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dariaples.demo.model.SmallScheduledTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

@Service
public class DemoService {

  private final Logger logger = LogManager.getLogger();

  @Autowired
  private TaskScheduler taskScheduler;
  private List<ScheduledFuture<?>> tasks = new ArrayList<>();

  private List<SmallScheduledTask> exampleValues =
          Arrays.asList(
                  new SmallScheduledTask("123 (runs every minute)", "0 */1 * * * *"),
                  new SmallScheduledTask("456 (runs 5 minutes)", "0 */5 * * * *"),
                  new SmallScheduledTask("789 (runs 10 minutes)", "0 */10 * * * *")
          );

  /**
   * Running creating all scheduled tasks job at the start of the day at 00:00
   */
  @Scheduled(cron = "0 0 0 * * *")
  public void runScheduledTasks() {
    logger.info("Started job on creating scheduled tasks");

    exampleValues.forEach(e -> tasks.add(
            taskScheduler.schedule(() ->
                    logger.info(
                      "Executing {} object value with cron {} at time {}",
                      e.getValue(),
                      e.getCron(),
                      getCurrentTimestamp()
                    ),
                    new CronTrigger(e.getCron())
            )
    ));
  }

  /**
   * Running stop all scheduled tasks job at the end of the day at 23:59
   */
  @Scheduled(cron = "0 59 23 * * *")
  public void stopScheduledTasks() {
    logger.info("Started job on stopping all scheduled tasks");

    tasks.forEach(task -> task.cancel(true));
  }

  private String getCurrentTimestamp() {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }
}
