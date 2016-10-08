package simple;

/**
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public class SmallBrush extends Brush {

    public SmallBrush(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.println("Samll brush and " + this.color.bepaint() + " drawing");
    }
}
