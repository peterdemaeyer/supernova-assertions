package su.pernova.assertions;

/**
 * The subject of an assertion of the form "assert that &lt;subject&gt; &lt;has relation to&gt; &lt;expected&gt;".
 * This class implements a <em>prototype</em> design pattern, which means that every instance serves both as subject and
 * as factory for other similar subjects with a different actual object.
 */
public interface Subject extends Describable {

	boolean match(Matcher matcher);
}
