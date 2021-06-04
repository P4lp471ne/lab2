package com.oop.backend.repositories;

import com.oop.backend.models.user.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository  extends JpaRepository<Page, Long> {
}
