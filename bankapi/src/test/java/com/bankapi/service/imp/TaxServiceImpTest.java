package com.bankapi.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.bankapi.entities.Transfer;
import com.bankapi.exception.CvcBankApiException;
import com.bankapi.util.DataUtils;
import com.bankapi.util.RequestTransferVOCreator;

@SpringBootTest
@ContextConfiguration(classes = { TaxServiceImp.class })
class TaxServiceImpTest {

	@Autowired
	private TaxServiceImp taxServiceImp;

	/**
	 * Transferências no mesmo dia do agendamento tem uma taxa de $3 mais 3% do
	 * valor a ser transferido;
	 */

	@Test
	@DisplayName("Fee for same day transfers.")
	public void mustCalculate_theTaxforTheSameDay() throws CvcBankApiException, ParseException {

		Transfer transfer = new Transfer(RequestTransferVOCreator.createRequestTransferVoSameDay());

		BigDecimal taxExpected = taxServiceImp.calculateTax(transfer);
		BigDecimal taxForSameDay = new BigDecimal("0.03").multiply(transfer.getTransferAmount()).add(new BigDecimal(3));

		assertEquals(0, taxExpected.compareTo(taxForSameDay));

	}

	/**
	 * Transferências até 10 dias da data de agendamento possuem uma taxa de $12
	 * mulƟplicado pela quanƟdade de dias da data de agendamento até a data de
	 * transferência.
	 */

	@Test
	@DisplayName("Fee for transfers for up to 10 days.")
	public void mustCalculate_theTaxforUpTo10Days() throws CvcBankApiException, ParseException {

		Transfer transfer = new Transfer(RequestTransferVOCreator.createRequestTransferVoUpTenDays());

		long days = DataUtils.diffDays(transfer.getScheduleDate(), transfer.getTransferDate());

		BigDecimal taxExpected = taxServiceImp.calculateTax(transfer);
		BigDecimal taxForUpToTen = new BigDecimal("12").multiply(new BigDecimal(days));

		assertEquals(0, taxExpected.compareTo(taxForUpToTen));

	}

	/**
	 * Acima de 10 até 20 dias da data de agendamento 8%
	 * 
	 */
	@Test
	@DisplayName("Transfer fee for more than 10 to 20 days from the scheduled date.")
	public void mustCalculate_theTaxforMoreThan10To20Days() throws CvcBankApiException, ParseException {

		Transfer transfer = new Transfer(RequestTransferVOCreator.createRequestTransferVomoreThan10To20Days());

		BigDecimal taxExpected = taxServiceImp.calculateTax(transfer);
		BigDecimal taxMoreThan10To20 = new BigDecimal("0.08").multiply(transfer.getTransferAmount());

		assertEquals(0, taxExpected.compareTo(taxMoreThan10To20));

	}

	/**
	 * Acima de 20 até 30 dias da data de agendamento 6%
	 */

	@Test
	@DisplayName("Transfer fee for more than 20 to 30 days from the scheduled date.")
	public void mustCalculate_theTaxforMoreThan20To30Days() throws CvcBankApiException, ParseException {

		Transfer transfer = new Transfer(RequestTransferVOCreator.createRequestTransferVomoreThan20To30Days());

		BigDecimal taxExpected = taxServiceImp.calculateTax(transfer);
		BigDecimal taxMoreThan20To30 = new BigDecimal("0.06").multiply(transfer.getTransferAmount());

		assertEquals(0, taxExpected.compareTo(taxMoreThan20To30));

	}

	/**
	 * Acima de 30 até 40 dias da data de agendamento 4%
	 */

	@Test
	@DisplayName("Transfer fee for more than 30 to 40 days from the scheduled date.")
	public void mustCalculate_theTaxforMoreThan30To40Days() throws CvcBankApiException, ParseException {

		Transfer transfer = new Transfer(RequestTransferVOCreator.createRequestTransferVomoreThan30To40Days());

		BigDecimal taxExpected = taxServiceImp.calculateTax(transfer);
		BigDecimal taxMoreThan30To40 = new BigDecimal("0.04").multiply(transfer.getTransferAmount());

		assertEquals(0, taxExpected.compareTo(taxMoreThan30To40));

	}

	/**
	 * Acima de 40 dias da data de agendamento e valor superior a 100.000 2%
	 */

	@Test
	@DisplayName("Transfer fee for more than 30 to 40 days from the scheduled date and Amount greater 100000.")
	public void mustCalculate_theTaxforMoreThan30To40DaysAndGreaterAmount100000()
			throws CvcBankApiException, ParseException {

		Transfer transfer = new Transfer(
				RequestTransferVOCreator.createRequestTransferVomoreThan30To40DaysAndGreater100000());

		BigDecimal taxExpected = taxServiceImp.calculateTax(transfer);
		BigDecimal taxMoreThan30To40AndGreater100000 = new BigDecimal("0.02").multiply(transfer.getTransferAmount());

		assertEquals(0, taxExpected.compareTo(taxMoreThan30To40AndGreater100000));

	}

}
