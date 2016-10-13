/**
 * @author Yao.Jinwei
 * @date 2016/10/13 13:45
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class ItalicCommand implements ICommand {

    private Document document;
    private String previousText;
    public ItalicCommand(Document document) {
        this.document = document;
        previousText = document.getText();
    }

    @Override
    public void execute() {
        System.out.println("加斜体");
        document.italicSelection();
    }

    @Override
    public void undoExecute() {
        System.out.println("去斜体");
        this.document.setText(previousText);
    }
}
