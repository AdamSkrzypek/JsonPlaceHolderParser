package controler;

import java.util.List;
import java.util.function.Supplier;

public interface JsonController<T> {
   Supplier<String> fetchAllPosts();
   void persistAllPosts();
   List<T> convertToObject();

}
