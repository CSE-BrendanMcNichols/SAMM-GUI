package backEnd.test;

import backEnd.Major;
import backEnd.MajorList;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class MajorListTester {

    private UUID csUUID; 
    private UUID cisUUID;

    @BeforeEach
    public void setup() {
        MajorList majorList = MajorList.getInstance();
        majorList.getMajors().clear();

        csUUID = UUID.randomUUID();
        Major csMajor = new Major("Computer Science");
        csMajor.setUuid(csUUID);
        majorList.addMajor(csMajor);

        cisUUID = UUID.randomUUID();
        Major cisMajor = new Major("Computer Information Systems");
        cisMajor.setUuid(cisUUID);
        majorList.addMajor(cisMajor);
    }

    @Test
    public void testSingletonInstance() {
        MajorList instance1 = MajorList.getInstance();
        MajorList instance2 = MajorList.getInstance();
        assertSame(instance1, instance2, "Expected both instances to be the same object (singleton)");
    }

    @Test
    public void testAddAndGetMajorByName() {
        String newMajorName = "New Major";
        Major newMajor = new Major(newMajorName);
        newMajor.setUuid(UUID.randomUUID());
        MajorList.getInstance().addMajor(newMajor);

        Major retrievedMajor = MajorList.getInstance().getMajor(newMajorName);
        assertNotNull(retrievedMajor, "Major should have been added and retrievable by name");
        assertEquals(newMajorName, retrievedMajor.getMajor(), "The name of the retrieved major should match the added major");
    }

    @Test
    public void testEditMajor() {
        Major updatedCSMajor = new Major("CS Updated");
        updatedCSMajor.setUuid(csUUID); 
        MajorList.getInstance().editMajor(updatedCSMajor);

        Major retrievedUpdatedMajor = MajorList.getInstance().getMajor("CS Updated");
        assertNotNull(retrievedUpdatedMajor, "Updated major should be retrievable with the new name");
        assertEquals(csUUID, retrievedUpdatedMajor.getUuid(), "The UUID of the updated major should remain the same");
    }

    @Test
    public void testFindMajorByUUID() {
        assertTrue(MajorList.getInstance().findMajor(csUUID), "Should find CS major by UUID");
        assertFalse(MajorList.getInstance().findMajor(UUID.randomUUID()), "Should not find a major for a random UUID");
    }

    @Test
    public void testGetMajorByUUID() {
        Major csMajor = MajorList.getInstance().getMajor(csUUID);
        assertNotNull(csMajor, "Should retrieve CS major by UUID");
        assertEquals("Computer Science", csMajor.getMajor(), "The retrieved major should be 'Computer Science'");

        Major randomMajor = MajorList.getInstance().getMajor(UUID.randomUUID());
        assertNull(randomMajor, "Retrieving a major with a random UUID should return null");
    }
}
