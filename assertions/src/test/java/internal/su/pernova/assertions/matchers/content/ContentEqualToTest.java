package internal.su.pernova.assertions.matchers.content;

import static java.nio.file.Files.write;

import static su.pernova.assertions.Assertions.assertThat;
import static su.pernova.assertions.Matchers.equalTo;
import static su.pernova.assertions.Matchers.is;
import static su.pernova.assertions.Subjects.contentOf;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ContentEqualToTest {

	@Test
	void contentOfPath(@TempDir Path tmpDir) throws Exception {
		Path file = tmpDir.resolve("file.txt");
		byte[] content = new byte[] { 1, 2, 3, 4, 5 };
		write(file, content);
		assertThat(contentOf(file), is(equalTo(content)));
	}
}
