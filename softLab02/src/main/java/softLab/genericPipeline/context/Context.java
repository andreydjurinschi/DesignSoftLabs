package softLab.genericPipeline.context;

import java.util.Collections;
import java.util.List;

public class Context<T> {
    private List<T> data;

    public Context(List<T> data) {
        this.data = (data!=null) ? data : Collections.EMPTY_LIST;
    }

    public List<T> getData(){
        return data;
    }

    public int count(){
        return data.size();
    }

    public Context<T> returnNew(List<T> data){
        return new Context<>(data);
    }
}

