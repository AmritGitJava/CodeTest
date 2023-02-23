package com.telecomcode.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	@Column(name = "customerName")
	private String customerName;

	@OneToMany(fetch = FetchType.LAZY, targetEntity=Phone.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "customerId", referencedColumnName = "id")
	private List<Phone> phone = new ArrayList<>();

}
