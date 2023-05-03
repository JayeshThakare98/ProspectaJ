package com.prospecta.Repositry;

import java.security.KeyStore;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EERepo extends JpaRepository<KeyStore.Entry, Integer> {

    Optional<KeyStore.Entry> findByApi(String Api);
}
