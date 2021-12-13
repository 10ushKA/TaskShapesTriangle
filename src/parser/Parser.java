package parser;

import java.io.FileNotFoundException;
import java.util.List;

public interface Parser<T, K> {
    List<T> parse(K obj) throws FileNotFoundException;
}
