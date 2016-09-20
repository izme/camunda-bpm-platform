/*
 * Copyright 2016 camunda services GmbH.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.qa.performance.engine.steps;

import java.util.Date;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.management.MetricsQuery;
import org.camunda.bpm.qa.performance.engine.framework.PerfTestRunContext;

/**
 *
 * @author Christopher Zell <christopher.zell@camunda.com>
 */
public class MetricIntervalStep extends ProcessEngineAwareStep {

  protected String name;
  protected Date startDate;
  protected Date endDate;

  public MetricIntervalStep(String name, Date startDate, Date endDate, ProcessEngine processEngine) {
    super(processEngine);
    this.name = name;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  @Override
  public void execute(PerfTestRunContext context) {
    MetricsQuery metricsQuery = this.processEngine.getManagementService().createMetricsQuery();

    if (name != null) {
      metricsQuery.name(name);
    }

    if (startDate != null) {
      metricsQuery.startDate(startDate);
    }

    if (endDate != null) {
      metricsQuery.endDate(endDate);
    }

    metricsQuery.interval();
  }
}