package com.example.hw;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.junit.jupiter.api.BeforeAll;

@MockitoSettings(strictness = Strictness.LENIENT)
public class MyMockitoSettings {

    @BeforeAll
    static void beforeAll() {
        System.setProperty("mockito.mock-maker-inline", "org.mockito.internal.creation.bytebuddy.InlineByteBuddyMockMaker");
    }
}
