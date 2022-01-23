package org.dariaples.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SmallScheduledTask {

  private String value;
  private String cron;

}
