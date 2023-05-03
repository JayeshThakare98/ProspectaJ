package com.prospecta.Service;

import static org.assertj.core.api.Assertions.entry;

import java.security.KeyStore.Entry;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospecta.EntityDTO.EntryInfoResponseDto;
import com.prospecta.EntityDTO.EntryRespoDto;
import com.prospecta.Exception.DetailsNotFoundException;
import com.prospecta.Repositry.EERepo;

import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
public class MEServiceImpl implements MEServices {

	@Autowired
	private EERepo eeRepo;

	@Override
	public ApiResponse saveEntry(Entry entries) throws DetailsNotFoundException {
		Entry findByApi = this.eeRepo.findByApi(entry.getApi())
				.orElseThrow(() -> new ResourceNotFoundException("Entry", "API", entry.getApi()));

		EntryInfoResponseDto entryDetailsResponseDto = this.toEntryDetailsResponseDto(entry);

		return new (LocalDateTime.now(), "Entry Saved Successfully !", true, entryDetailsResponseDto);
	}

	@Override
	public List<EntryRespoDto> getAllEntries() {
		List<Entry> list = this.findAll();

		return list.stream().map(eRepo -> this.toEntryResponseDto(eRepo)).collect(Collectors.toList());

	}

	private EntryInfoResponseDto toEntryDetailsResponseDto(Entry entry) {

		return this.map(entry, EntryInfoResponseDto.class);

	}

	private EntryRespoDto toEntryResponseDto(Entry entry) {

		return this.map(entry, EntryRespoDto.class);
	}
}
