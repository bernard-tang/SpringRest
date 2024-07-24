package com.example.accessingdatajpa.util.payload;

import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.List;

@Component
public class ResourceUtil {
	
	public Resource convertObjectToResource(Object obj) {
		Resource resource = new Resource();

        // Get all fields from the object's class (including private fields)
        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                // Ensure we can access private fields
                field.setAccessible(true);

                // Put field name and value into the map
                resource.put(field.getName(), field.get(obj));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return resource;
    }
	
	public <T extends List<?>> Bundle convertListObjectToBundle(T list, int size) {
		Bundle bundle = new Bundle();
		for(Object obj : list) {
			bundle.getEntry().add(convertObjectToResource(obj));
		}
		bundle.setTotal(size);
		return bundle;
	}
	
}