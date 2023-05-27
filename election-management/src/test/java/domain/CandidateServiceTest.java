package domain;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import org.instancio.Instancio;
import org.instancio.InstancioOfCollectionApi;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


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
        service.findAll();
        verify(repository).findAllCandidates();
        verifyNoMoreInteractions(repository);
    }
}
