package atm.atm;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import atm.atm.service.AtmService;

@SpringBootTest
class AtmApplicationTests {

	@Autowired
	private AtmService atmService;
	
	@Test
	public void shouldReturnDispenseAmount_ifValueisWithinLimit(){
		try {
			int availableCash = atmService.availableCash();
			int amountToDispense = 230;
			atmService.dispense(amountToDispense);
			availableCash = availableCash-amountToDispense;
			assertEquals(availableCash, atmService.availableCash());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Failed", "");
		}
		
	}

	@Test
	public void shouldReturnDispenseAmount_ifValueisWithinLimit2(){
		try {
			int availableCash = atmService.availableCash();
			int amountToDispense = 130;
			atmService.dispense(amountToDispense);
			availableCash = availableCash-amountToDispense;
			assertEquals(availableCash, atmService.availableCash());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Failed", "");
		}
		
	}

	@Test
	public void shouldReturnError_ifValueisOutOfRange(){
		int amountToDispense = 930;
		try {
			int availableCash = atmService.availableCash();
			
			atmService.dispense(amountToDispense);
			availableCash = availableCash-amountToDispense;
			assertEquals(availableCash, atmService.availableCash());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Unable to dispense amount for the request amount $"+amountToDispense+". Please request refill amount",e.getMessage());
		}
	}

	@Test
	public void shouldReturnError_ifValueisInvalid(){
		int amountToDispense = 51;
		try {
			int availableCash = atmService.availableCash();
			
			atmService.dispense(amountToDispense);
			availableCash = availableCash-amountToDispense;
			assertEquals(availableCash, atmService.availableCash());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Unable to dispense amount for the request amount $"+amountToDispense+". Accepted denomination in $100,$50,$20,$10,$5",e.getMessage());
		}
	}
}


