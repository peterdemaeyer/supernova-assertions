package su.pernova.assertions;

public interface Description {

	Description appendText(CharSequence text);

	Description appendArgument(Object argument);
}
