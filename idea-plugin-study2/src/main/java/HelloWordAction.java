import com.google.common.collect.Lists;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;

import java.util.List;

/**
 * @author Yao Jinwei (yjw0909 AT gmail DOT com)
 */
public class HelloWordAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        //获取当前在操作的工程上下文
        Project project = e.getData(PlatformDataKeys.PROJECT);

        //获取当前操作的类文件
        PsiFile psiFile = e.getData(CommonDataKeys.PSI_FILE);
        //获取当前类文件的路径
        String classPath = psiFile.getVirtualFile().getPath();
        String title = "Hello World!";

        //显示对话框
        Messages.showMessageDialog(project, classPath, title, Messages.getInformationIcon());
    }

}
