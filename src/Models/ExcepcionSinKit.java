package Models;
import com.fasterxml.jackson.annotation.*;
import java.util.*;
class ExcepcionSinKit extends Exception {
    public ExcepcionSinKit(String message) {
        super(message);
    }
}
