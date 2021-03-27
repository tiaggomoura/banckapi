package com.bankapi.vo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bankapi.entities.Transfer;
import com.bankapi.util.DataUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestTransferVO {

	private Long id;
	private BigDecimal tax = BigDecimal.ZERO;

	@Min(value = 5, message = "Operation denied: The minimum amount to transfer is 5")
	private BigDecimal transferAmount = BigDecimal.ZERO;

	private LocalDate scheduleDate = LocalDate.now();
	
	@NotEmpty(message = "Operation denied: Transfer Date is required.")
	private String transferDate;

	@NotNull(message = "Operation denied: Favored Accounts must be reported")
	private Long favored;

	@NotNull(message = "Operation denied: Origin Accounts must be reported")
	private Long oringin;

	public RequestTransferVO(Transfer transfer) {
		this.id = transfer.getId();
		this.tax = transfer.getTax();
		this.transferAmount = transfer.getTransferAmount();
		this.transferDate = DataUtils.getStringFromDate("yyyy-MM-dd", transfer.getTransferDate());
	}

	public Transfer toEntity() throws ParseException {
		return new Transfer(this);
	}

}
