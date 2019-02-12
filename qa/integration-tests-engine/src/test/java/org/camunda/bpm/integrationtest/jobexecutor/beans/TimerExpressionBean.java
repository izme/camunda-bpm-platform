/*
 * Copyright © 2013-2019 camunda services GmbH and various authors (info@camunda.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.bpm.integrationtest.jobexecutor.beans;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.cdi.annotation.ProcessVariable;
import org.camunda.bpm.engine.exception.NullValueException;
import org.camunda.bpm.engine.runtime.VariableInstance;

/**
 * @author Tobias Metzke
 *
 */
@Named
public class TimerExpressionBean implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Inject
  @ProcessVariable
  private Object timerExpression;
  
  @Inject
  RuntimeService runtimeService;

  public String getTimerDuration() {
    if (timerExpression == null) {
      VariableInstance variable = runtimeService
          .createVariableInstanceQuery()
          .variableName("timerExpression")
          .singleResult();
      if (variable != null) {
        timerExpression = String.valueOf(variable.getValue());
      }
    }
    if (timerExpression == null) {
      throw new NullValueException("no variable 'timerExpression' found");
    }
    return String.valueOf(timerExpression);
  }
}
