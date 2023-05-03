package com.prospecta.EntityDTO;

import java.security.KeyStore;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiInfo {
	 private Integer count;

	    private List<KeyStore.Entry> entries;
}
