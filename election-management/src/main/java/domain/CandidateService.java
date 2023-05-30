package domain;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CandidateService {

    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public void saveCandidate(Candidate candidate) {
        repository.save(candidate);
    }

    public List<Candidate> findAllCandidates() {
        return repository.findAll();
    }

    public Candidate findCandidateById(String id) {
        return repository.findById(id).orElseThrow();
    }
}
