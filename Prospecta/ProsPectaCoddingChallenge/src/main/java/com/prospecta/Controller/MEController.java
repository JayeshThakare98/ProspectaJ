package com.prospecta.Controller;

import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.EntityDTO.EntryInfoResponseDto;
import com.prospecta.EntityDTO.EntryRespoDto;
import com.prospecta.Exception.DetailsNotFoundException;
import com.prospecta.Service.MEServices;

import io.swagger.v3.oas.models.responses.ApiResponse;

@RestController
public class MEController {

	@Autowired
	private Mapper modelMapper;

	@Autowired
	private MEServices meServices;

	@Autowired
	private RestTemplate restTemplate;

	// here we get the all list of our entry
	@GetMapping("/entries")
	public ResponseEntity<List<KeyStore.Entry>> getEntriesHandler() {

		Apidata data = restTemplate.getForObject("https://api.publicapis.org/entries", Apidata.class);

		List<KeyStore.Entry> apiEntries = data.getEntries();

		List<EntryInfoResponseDto> store = apiEntries.stream()
				.map(en -> this.modelMapper.map(en, EntryDetailsResponseDto.class)).store(Collectors.toList());

		return new ResponseEntity<List<KeyStore.Entry>>(apiEntries, HttpStatus.OK);
	}

	// Inserting category as a Request Parameter to get data on Category

	@GetMapping("/entries/categories")
	public ResponseEntity<List<EntryRespoDto>> getEntriesHandler(@RequestParam String category) {

		String url = "https://api.publicapis.org/entries?category=" + category;

		Apidata data = restTemplate.getForObject(url, Apidata.class);

		List<KeyStore.Entry> apiEntries = data.getEntries();

		List<EntryRespoDto> store = apiEntries.stream().map(en -> this.modelMapper.map(en, EntryResponseDto.class))
				.collect(Collectors.toList());

		return new ResponseEntity<List<EntryRespoDto>>(store, HttpStatus.OK);
	}
	// Category

	@PostMapping("/entries")
	public ResponseEntity<ApiResponse> saveEntryHandler(@RequestBody Entry entry) throws DetailsNotFoundException {

		ApiResponse apiResp = this.entryServices.saveEntry((KeyStore.Entry) entry);

		return new ResponseEntity<ApiResponse>(apiResp, HttpStatus.CREATED);
	}

}
