package simple;

/**
 * 刷子接口
 * @author yaojinwei<yjw0909@gmail.com>
 * @since 2016/10/8
 */
public abstract class Brush {
    protected Color color;

    public Brush(Color color) {
        this.color = color;
    }

    abstract void draw();
}
