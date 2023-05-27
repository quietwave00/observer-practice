package com.observer.main.subject;

import com.observer.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findBySubjectId(Long i);
}
