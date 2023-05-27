package domain;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CandidateService {

    private final CandidateRepository repository;

    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public void saveCandidate(Candidate candidate) {
        repository.save(candidate);
    }

    public void findAll() {
        repository.findAllCandidates();
    }
}
