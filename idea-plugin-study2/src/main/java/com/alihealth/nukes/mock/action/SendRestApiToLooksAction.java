package com.alihealth.nukes.mock.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alihealth.nukes.domain.LooksApi;
import com.alihealth.nukes.mock.dto.MockApiDTO;
import com.alihealth.nukes.spring.SpringPicker;
import com.alihealth.nukes.util.HttpUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

import java.io.IOException;
import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class SendRestApiToLooksAction extends AnAction {
    private static Logger logger = Logger.getInstance(SendRestApiToLooksAction.class);
    private SpringPicker picker = new SpringPicker();

    public SendRestApiToLooksAction() {

    }

    @Override
    public void actionPerformed(AnActionEvent e) {

        Project project = e.getProject();
        if (project == null) {
            return;
        }
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }
        //获取当前事件触发时，光标所在的元素
        PsiElement psiElement = e.getData(LangDataKeys.PSI_ELEMENT);
        //如果光标选择的不是类，弹出对话框提醒
        if (psiElement == null || (!(psiElement instanceof PsiMethod) && !(psiElement instanceof PsiClass))) {
            Messages.showMessageDialog(project, "Please focus on a method or a class", "Generate Failed", null);
            return;
        }
        //选择的类是抽象类、泛类、接口
        //message
        // return;

        try {
            if (psiElement instanceof PsiMethod) {
                PsiMethod psiMethod = (PsiMethod) psiElement;
                LooksApi api = picker.pickApi(psiMethod);
                sendMethodToMock(project, api);
            } else if (psiElement instanceof PsiClass) {
                PsiClass psiClass = (PsiClass) psiElement;
                List<LooksApi> looksApiList = picker.pickApi(psiClass);
                for (LooksApi api : looksApiList) {
                    sendMethodToMock(project, api);
                }
            }
        } catch (Exception ex) {
            logger.error(ex);
            Messages.showMessageDialog(project, ex.getMessage(), "Generate Failed", null);
        }
    }

    private void sendMethodToMock(Project project, LooksApi api) {
        try {
            sendToMock(project, MockApiDTO.createInstance(api));
        } catch (IOException ioException) {
            logger.error(ioException);
            throw new RuntimeException(ioException);
        }
    }

    private void sendToMock(Project project, MockApiDTO dto) throws IOException {
        String host = "http://11.164.63.179:8819/api/api/syncApi";
//        String host = "http://30.24.238.36:7001/api/api/syncApi";
        String jsonString = JSON.toJSONString(dto);
        okhttp3.Response httpResp = HttpUtils.post(host, jsonString);
        String responseBody = httpResp.body().string();
        JSONObject jsonObject = JSON.parseObject(responseBody);
        if (!jsonObject.getBoolean("success")) {
            logger.error("LittleBoy return error! ");
            logger.error("request: " + jsonString);
            logger.error("response: " + responseBody);
            Messages.showMessageDialog(project, jsonObject.getString("message"), "Generate Failed", null);
        } else {
            Messages.showMessageDialog(project, "success", "Generate Success", null);
        }
//        System.out.println("com.alihealth.ideaplugin.mock response: " + response.body().string());
    }
    //PsiUtil.extractIterableTypeParameter(type,false);
    //clazz.resolve().getTypeParameters()[0].getText()
    //field.getType().getCanonicalText()
    //clazz.resolve().getTypeParameters()
//((PsiClassReferenceType) returnType).getParameters()
    //PsiType.VOID.equals(returnType)
    //method.hasModifierProperty(PsiModifier.PUBLIC
//        returnTypeElement.get
}
