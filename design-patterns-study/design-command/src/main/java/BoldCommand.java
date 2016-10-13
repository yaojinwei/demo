/**
 * @author Yao.Jinwei
 * @date 2016/10/13 13:45
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class BoldCommand implements ICommand {

    private Document document;
    private String previousText;
    public BoldCommand(Document document) {
        this.document = document;
        previousText = document.getText();
    }

    @Override
    public void execute() {
        System.out.println("字体加粗");
        document.boldSelection();
    }

    @Override
    public void undoExecute() {
        System.out.println("撤销加粗");
        this.document.setText(previousText);
    }
}
