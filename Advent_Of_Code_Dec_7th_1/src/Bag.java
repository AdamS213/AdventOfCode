import java.util.ArrayList;
import java.util.List;

public class Bag<T> {
    private List<Bag<T>> children = new ArrayList<Bag<T>>();
    private List<Bag<T>> parents = new ArrayList<Bag<T>>();
    private T data = null;

    public Bag(T data) {
        this.data = data;
    }


    public List<Bag<T>> getChildren() {
        return children;
    }

    public void addParent(Bag<T> parent) {
       parents.add(parent);
    }


    public void addChild(Bag<T> child) {
        child.addParent(this);
        this.children.add(child);
    }

    public boolean containsData(T data) {
        boolean found = false;

        for (Bag<T> child: getChildren()) {
            if(child.getData().equals(data)) {
                found = true;
                break;
            }
            else if(!child.isLeaf()) {
                found = child.containsData(data);
                if(found) {
                    break;
                }
            }
        }

        return found;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return this.parents.size() == 0;
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

}
