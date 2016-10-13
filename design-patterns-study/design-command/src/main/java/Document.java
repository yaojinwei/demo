/**
 * 接收者对象receiver
 * 真正执行命令的对象
 * @author Yao.Jinwei
 * @date 2016/10/13 13:48
 * @Copyright(c) Beijing LeFinance Software Co.,LTD
 */
public class Document {

    private String text;

    public Document(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        System.out.println("------------" + text + "-------------");
    }

    void boldSelection(){
        setText("<b>" + text + "</b>");
    }

    void italicSelection(){
        setText("<i>" + text + "</i>");
    }
}
