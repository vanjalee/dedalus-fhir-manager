package org.acme.getting.started;

import io.quarkus.test.junit.NativeImageTest;

/**
 * @author Vanja Bisanovic
 */
@NativeImageTest
public class NativePatientJacksonResourceIT extends PatientJacksonResourceTest {

    // Execute the same tests but in native mode.
}