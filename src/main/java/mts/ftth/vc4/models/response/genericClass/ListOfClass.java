package mts.ftth.vc4.models.response.genericClass;

import mts.ftth.vc4.models.Cabinet;

import java.util.ArrayList;
import java.util.List;

public class ListOfClass {

    private List<Cabinet> list;

    public ListOfClass() {
        this.list = new ArrayList<>();
    }

    public void add(Cabinet item) {
        this.list.add(item);
    }

    public Cabinet get(int index) {
        return this.list.get(index);
    }

    public int size() {
        return this.list.size();
    }


}
