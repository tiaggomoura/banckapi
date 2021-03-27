package com.bankapi.entities;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;

import com.bankapi.vo.RequestAccountVO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements GenericEntity<Account, RequestAccountVO> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Long id;

	private String number;

	private String name;

	private BigDecimal balance = BigDecimal.ZERO;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@JsonIgnore
	@OneToMany(orphanRemoval = true)
	@JoinColumn(name = "transfer_id")
	private Set<Transfer> tranfers = new HashSet<>();

	public Account(RequestAccountVO requestAccountVO) {
		this.number = requestAccountVO.getNumber();
		this.name = requestAccountVO.getName();
		this.balance = requestAccountVO.getBalance();
	}

	public Account setData(RequestAccountVO requestAccountVO) {
		this.number = requestAccountVO.getNumber();
		this.name = requestAccountVO.getName();
		this.balance = requestAccountVO.getBalance();
		return this;
	}

	public boolean isHavePositiveBalance() {
		return this.getBalance().compareTo(BigDecimal.ZERO) == 1;
	}

	public Account setOwner(Customer c) {
		this.setCustomer(c);
		return this;
	}

	@PrePersist
	public void prePersist() {
		this.setNumber(RandomStringUtils.randomNumeric(6));
	}

	@Override
	public void update(RequestAccountVO source) {
		this.name = source.getName();
		this.balance = source.getBalance();
	}

	@Override
	public Account createNewInstance(Account account, RequestAccountVO source) {
		return account.setData(source);
	}
}