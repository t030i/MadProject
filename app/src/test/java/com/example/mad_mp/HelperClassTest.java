package com.example.mad_mp;

import junit.framework.TestCase;

public class HelperClassTest extends TestCase {

    HelperClass helperClass;

    public void setUp() throws Exception {
        super.setUp();
        helperClass = new HelperClass();
    }

    public void tearDown() throws Exception {
        helperClass = null;
    }

    public void testGetFirstname() {
        helperClass.setFirstname("John");
        assertEquals("John", helperClass.getFirstname());
    }

    public void testSetFirstname() {
        helperClass.setFirstname("John");
        assertEquals("John", helperClass.getFirstname());

    }

    public void testGetLastname() {
        helperClass.setLastname("Doe");
        assertEquals("Doe", helperClass.getLastname());

    }

    public void testSetLastname() {
        helperClass.setLastname("Doe");
        assertEquals("Doe", helperClass.getLastname());
    }

    public void testGetDateofbirth() {
        helperClass.setDateofbirth("2000-01-01");
        assertEquals("2000-01-01", helperClass.getDateofbirth());
    }

    public void testSetDateofbirth() {
        helperClass.setDateofbirth("2000-01-01");
        assertEquals("2000-01-01", helperClass.getDateofbirth());
    }

    public void testGetUsername() {
        helperClass.setUsername("johndoe");
        assertEquals("johndoe", helperClass.getUsername());
    }

    public void testSetUsername() {
        helperClass.setUsername("johndoe");
        assertEquals("johndoe", helperClass.getUsername());
    }

    public void testGetEmail() {
        helperClass.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", helperClass.getEmail());
    }

    public void testSetEmail() {
        helperClass.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", helperClass.getEmail());
    }

    public void testGetPassword() {
        helperClass.setPassword("password123");
        assertEquals("password123", helperClass.getPassword());
    }

    public void testSetPassword() {
        helperClass.setPassword("password123");
        assertEquals("password123", helperClass.getPassword());
    }

    public void testGetFeedback() {
        helperClass.setFeedback("Great app!");
        assertEquals("Great app!", helperClass.getFeedback());
    }

    public void testSetFeedback() {
        helperClass.setFeedback("Great app!");
        assertEquals("Great app!", helperClass.getFeedback());
    }
}