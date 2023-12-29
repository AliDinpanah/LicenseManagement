package teck.me.license.utill;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class Converter<T, S> {

    public S convert(T obj1, S obj2) throws IllegalAccessException {
        Field[] fields1 = obj1.getClass().getDeclaredFields();
        Field[] fields2 = obj2.getClass().getDeclaredFields();
        for (Field field1 : fields1) {
            for (Field field2 : fields2) {
                if (field1.getName().equals(field2.getName())) {
                    field2.setAccessible(true);
                    field1.setAccessible(true);
                    field2.set(obj2, field1.get(obj1));
                    break;
                }
            }
        }
        return obj2;
    }

    public List<S> convertList(List<T> obj1,S obj2) throws IllegalAccessException {
        List<S> list=new ArrayList<>();
        for (T obj:obj1){
            list.add(convert(obj,obj2));
        }
        return list;
    }
}

