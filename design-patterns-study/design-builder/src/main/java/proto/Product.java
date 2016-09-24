package proto;

/**
 * Created by yaojinwei on 2016/9/24.
 */
public abstract class Product {
    public PartA partA;
    public PartB partB;
    public PartC partC;

    public PartA getPartA() {
        return partA;
    }

    public void setPartA(PartA partA) {
        this.partA = partA;
    }

    public PartB getPartB() {
        return partB;
    }

    public void setPartB(PartB partB) {
        this.partB = partB;
    }

    public PartC getPartC() {
        return partC;
    }

    public void setPartC(PartC partC) {
        this.partC = partC;
    }
}
