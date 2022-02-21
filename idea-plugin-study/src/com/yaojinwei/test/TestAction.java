package com.yaojinwei.test;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class TestAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        Messages.showMessageDialog("Hello World !", "Information", Messages.getInformationIcon());

        // TODO: insert action logic here
        final Editor mEditor = e.getData(PlatformDataKeys.EDITOR);
        if (null == mEditor) {
            return;
        }

        //获取当前事件触发时，光标所在的元素
        PsiElement psiElement = e.getData(LangDataKeys.PSI_ELEMENT);
        System.out.println(111);
//        psiElement.
        //如果光标选择的不是类，弹出对话框提醒
//        if (psiElement == null || !(psiElement instanceof PsiClass)) {
//            Messages.showMessageDialog(project, "Please focus on a class", "Generate Failed", null);
//            return;
//        }

//        mEditor.getMarkupModel()
//        SelectionModel model = mEditor.getSelectionModel();
//        final String selectedText = model.get.getSelectedText();
//        if (TextUtils.isEmpty(selectedText)) {
//            return;
//        }

    }

}
