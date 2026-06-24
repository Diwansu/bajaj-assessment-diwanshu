package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.BfhlRequest;
import com.bajaj.bfhl.dto.BfhlResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {
    "bfhl.user-id=diwanshu_baskota_16082003",
    "bfhl.email=diwanshu1618.be23@chitkarauniversity.edu.in",
    "bfhl.roll-number=2311981618"
})
public class BfhlServiceImplTest {

    @Autowired
    private BfhlService bfhlService;

    @Test
    public void testExampleA() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("a", "1", "334", "4", "R", "$"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals("diwanshu_baskota_16082003", response.getUserId());
        assertEquals("diwanshu1618.be23@chitkarauniversity.edu.in", response.getEmail());
        assertEquals("2311981618", response.getRollNumber());

        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals(Collections.singletonList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());
        assertEquals(Collections.singletonList("$"), response.getSpecialCharacters());
        assertEquals("339", response.getSum());
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    public void testExampleB() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("2", "a", "y", "4", "&", "-", "*", "5", "92", "b"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("2", "4", "92"), response.getEvenNumbers());
        assertEquals(Collections.singletonList("5"), response.getOddNumbers());
        assertEquals(Arrays.asList("A", "Y", "B"), response.getAlphabets());
        assertEquals(Arrays.asList("&", "-", "*"), response.getSpecialCharacters());
        assertEquals("103", response.getSum());
        assertEquals("ByA", response.getConcatString());
    }

    @Test
    public void testExampleC() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("A", "ABCD", "DOE"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getOddNumbers().isEmpty());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    public void testProcessData_EmptyInput() {
        BfhlRequest request = new BfhlRequest(Collections.emptyList());
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getAlphabets().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());
        assertEquals("0", response.getSum());
        assertEquals("", response.getConcatString());
    }

    @Test
    public void testProcessData_NegativeAndLargeNumbers() {
        BfhlRequest request = new BfhlRequest(Arrays.asList("-5", "10", "100000000000000000000000000000000"));
        BfhlResponse response = bfhlService.processData(request);

        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("10", "100000000000000000000000000000000"), response.getEvenNumbers());
        assertEquals(Collections.singletonList("-5"), response.getOddNumbers());
        assertEquals("100000000000000000000000000000005", response.getSum());
    }
}
