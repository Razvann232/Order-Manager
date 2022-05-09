package bll.validators;

/**
 * Interface which contains the "validate" generic method.
 * @author Pinzariu Razvan-George
 * @param <T> class type
 */
public interface Validator<T> {
    void validate(T t);
}
