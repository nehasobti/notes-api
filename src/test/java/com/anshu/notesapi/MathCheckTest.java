package com.anshu.notesapi;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MathCheckTest {

	@Test
	void addition() {
		// Deliberately wrong: 1 + 1 is 2, not 3. CI should catch this.
		assertEquals(2, 1 + 1, "fixed");
	}

}
