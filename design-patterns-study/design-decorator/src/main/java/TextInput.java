/**
 * TextInput组件
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class TextInput implements Component {
    private String name;
    private String value;

    public TextInput(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String paint() {
        return "<input type=\"text\" name=\"" + this.name + "\" value=\"" + this.value + "\" />";
    }
}
