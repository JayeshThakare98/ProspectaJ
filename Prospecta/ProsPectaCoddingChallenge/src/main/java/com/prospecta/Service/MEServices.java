package com.prospecta.Service;

import java.security.KeyStore;
import java.util.List;

import com.prospecta.EntityDTO.EntryRespoDto;
import com.prospecta.Exception.DetailsNotFoundException;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface MEServices {
	  public ApiResponse saveEntry(KeyStore.Entry entries) throws DetailsNotFoundException;

	    List<EntryRespoDto> getAllEntries();
}
