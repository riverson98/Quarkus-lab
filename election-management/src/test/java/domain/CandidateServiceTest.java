package domain;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.instancio.InstancioOfCollectionApi;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@QuarkusTest
class CandidateServiceTest {

    @Inject
    CandidateService service;

    @InjectMock
    CandidateRepository repository;

    @Test
    void saveCandidate_returnCandidate_WhenCandidateIsPersistInDataBase(){
        Candidate candidate = Instancio.create(Candidate.class);

        service.saveCandidate(candidate);

        verify(repository).save(candidate);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findAllCandidates_returnAllCandidates_WhenIsSuccessfully(){
        List<Candidate> candidates = Instancio.stream(Candidate.class).limit(10).toList();
        when(repository.findAll()).thenReturn(candidates);
        List<Candidate> result = service.findAllCandidates() ;
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
        assertEquals(candidates, result);
    }
    @Test
    void findCandidateById_returnCandidate_WhenCandidateIsFound(){
        Candidate candidate = Instancio.create(Candidate.class);
        when(repository.findById(candidate.id())).thenReturn(Optional.of(candidate));
        Candidate result = service.findCandidateById(candidate.id());
        verify(repository).findById(candidate.id());
        verifyNoMoreInteractions(repository);
        assertEquals(candidate, result);
    }
    @Test
    void findCandidateById_throwsException_WhenCandidateIsNotFound(){
        Candidate candidate = Instancio.create(Candidate.class);
        when(repository.findById(candidate.id())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> {
            service.findCandidateById(candidate.id());
        });
        verify(repository).findById(candidate.id());
        verifyNoMoreInteractions(repository);
    }
}
