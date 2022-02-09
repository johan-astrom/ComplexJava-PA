package se.astrom.complexjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.astrom.complexjava.entity.AzureGroup;

@Repository
public interface AzureGroupRepository extends CrudRepository<AzureGroup, String> {
}
