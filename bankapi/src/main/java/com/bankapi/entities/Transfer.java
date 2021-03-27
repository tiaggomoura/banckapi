package com.bankapi.entities;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.bankapi.util.DataUtils;
import com.bankapi.vo.RequestTransferVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private BigDecimal tax = BigDecimal.ZERO;
	private BigDecimal transferAmount = BigDecimal.ZERO;
	private BigDecimal totalTransferAmountWithTax = BigDecimal.ZERO;

	private LocalDate scheduleDate = LocalDate.now();
	private LocalDate transferDate;

	@ManyToOne
	@JoinColumn(name = "favored_id", nullable = false)
	private Account favored;

	@ManyToOne
	@JoinColumn(name = "origin_id", nullable = false)
	private Account oringin;

	public Transfer(RequestTransferVO vo) throws ParseException {
		this.id = vo.getId();
		this.tax = vo.getTax();
		this.transferAmount = vo.getTransferAmount();
		this.transferDate = DataUtils.getDateFromString("yyyy-MM-dd", vo.getTransferDate());
	}

	public Transfer origin(Account origin) {
		this.setOringin(origin);
		return this;
	}

	public Transfer favored(Account favored) {
		this.setFavored(favored);
		return this;
	}

	public Transfer calculateBalanceOrigin() {
		this.getOringin().setBalance(this.getOringin().getBalance().subtract(this.getTransferAmount()));
		return this;
	}

	public Transfer calculateBalanceFavored() {
		this.getFavored().setBalance(this.getFavored().getBalance().add(this.getTransferAmount()));
		return this;
	}

	@PrePersist
	public void prePersist() {
		this.setScheduleDate(LocalDate.now());
		this.setTotalTransferAmountWithTax(this.getTransferAmount().add(getTax()));
	}

}