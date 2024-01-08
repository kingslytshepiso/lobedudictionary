package com.lobedudictionary.lobedudictionary.data;

import org.springframework.stereotype.Repository;
import com.lobedudictionary.lobedudictionary.models.*;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DefinitionUsageRepository extends JpaRepository<DefinitionUsage, Long> {

    Boolean existsByValue(String value);

}
