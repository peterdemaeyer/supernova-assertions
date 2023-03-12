package su.pernova.assertions;

public interface Matcher extends Describable {

	boolean match(Object actual);
}
