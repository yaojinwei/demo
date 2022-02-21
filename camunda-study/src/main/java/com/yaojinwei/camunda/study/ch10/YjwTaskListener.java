package com.yaojinwei.camunda.study.ch10;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class YjwTaskListener implements TaskListener {
    public void notify(DelegateTask delegateTask) {

        System.out.println("##############");
        System.out.println(delegateTask.getId());
        System.out.println(delegateTask.getAssignee());
        System.out.println(delegateTask.getProcessDefinitionId());
        System.out.println(delegateTask.getCandidates());
        String eventName = delegateTask.getEventName();
        if (eventName.equals("create")) {
//            delegateTask.setAssignee("zhangsanfeng");
//            delegateTask.addCandidateUser();
        }
        System.out.println("##############");
    }
}
