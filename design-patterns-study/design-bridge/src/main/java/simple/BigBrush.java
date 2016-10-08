package simple;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class BigBrush extends Brush {

    public BigBrush(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.println("Big brush and " + this.color.bepaint() + " drawing");
    }
}
