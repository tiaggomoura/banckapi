package com.bankapi.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.bankapi.vo.RequestTransferVO;

public class RequestTransferVOCreator {

	public static RequestTransferVO createRequestTransferVoSameDay() {
		return new RequestTransferVO().builder()
				.id(1L)
				.oringin(1L)
				.favored(2L)
				.scheduleDate(LocalDate.now())
				.transferDate(DataUtils.getStringFromDate("yyyy-MM-dd", LocalDate.now()))
				.tax(BigDecimal.ZERO)
				.transferAmount(new BigDecimal(50))
				.build();
	}
	
	public static RequestTransferVO createRequestTransferVoUpTenDays() {
		return new RequestTransferVO().builder()
				.id(1L)
				.oringin(1L)
				.favored(2L)
				.scheduleDate(LocalDate.now())
				.transferDate(DataUtils.getStringFromDate("yyyy-MM-dd", LocalDate.now().plusDays(8)))
				.tax(BigDecimal.ZERO)
				.transferAmount(new BigDecimal(50))
				.build();	
	
	}
	
	public static RequestTransferVO createRequestTransferVomoreThan10To20Days() {
		return new RequestTransferVO().builder()
				.id(1L)
				.oringin(1L)
				.favored(2L)
				.scheduleDate(LocalDate.now())
				.transferDate(DataUtils.getStringFromDate("yyyy-MM-dd", LocalDate.now().plusDays(12)))
				.tax(BigDecimal.ZERO)
				.transferAmount(new BigDecimal(50))
				.build();	
		
	}
	
	public static RequestTransferVO createRequestTransferVomoreThan20To30Days() {
		return new RequestTransferVO().builder()
				.id(1L)
				.oringin(1L)
				.favored(2L)
				.scheduleDate(LocalDate.now())
				.transferDate(DataUtils.getStringFromDate("yyyy-MM-dd", LocalDate.now().plusDays(22)))
				.tax(BigDecimal.ZERO)
				.transferAmount(new BigDecimal(50))
				.build();	
		
	}
	
	public static RequestTransferVO createRequestTransferVomoreThan30To40Days() {
		return new RequestTransferVO().builder()
				.id(1L)
				.oringin(1L)
				.favored(2L)
				.scheduleDate(LocalDate.now())
				.transferDate(DataUtils.getStringFromDate("yyyy-MM-dd", LocalDate.now().plusDays(38)))
				.tax(BigDecimal.ZERO)
				.transferAmount(new BigDecimal(50))
				.build();	
		
	}
	
	public static RequestTransferVO createRequestTransferVomoreThan30To40DaysAndGreater100000() {
		return new RequestTransferVO().builder()
				.id(1L)
				.oringin(1L)
				.favored(2L)
				.scheduleDate(LocalDate.now())
				.transferDate(DataUtils.getStringFromDate("yyyy-MM-dd", LocalDate.now().plusDays(46)))
				.tax(BigDecimal.ZERO)
				.transferAmount(new BigDecimal(100001))
				.build();	
		
	}

}
